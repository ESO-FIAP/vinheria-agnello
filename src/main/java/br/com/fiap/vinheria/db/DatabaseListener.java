package br.com.fiap.vinheria.db;

import br.com.fiap.vinheria.store.UserStore;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(DatabaseListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSourceHolder.close();
        UserStore.destroy();

        String url = System.getenv("JDBC_URL");
        String user = System.getenv("JDBC_USER");
        String password = System.getenv("JDBC_PASSWORD");
        if (url == null || url.isBlank() || user == null || user.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Defina as variáveis de ambiente JDBC_URL, JDBC_USER e JDBC_PASSWORD.");
        }

        HikariConfig config = new HikariConfig();
        // Tomcat: forçar o driver no classloader da WAR (evita "No suitable driver").
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(url.trim());
        config.setUsername(user.trim());
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setPoolName("vinheria-pool");

        HikariDataSource ds = new HikariDataSource(config);
        DataSourceHolder.set(ds);
        UserStore.init(ds);
        LOG.info("Pool JDBC inicializado.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        UserStore.destroy();
        DataSourceHolder.close();
        LOG.log(Level.INFO, "Pool JDBC encerrado.");
    }
}
