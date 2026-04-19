package br.com.fiap.vinheria.db;

import com.zaxxer.hikari.HikariDataSource;

public final class DataSourceHolder {

    private static volatile HikariDataSource dataSource;

    private DataSourceHolder() {
    }

    public static void set(HikariDataSource ds) {
        dataSource = ds;
    }

    public static void close() {
        HikariDataSource ds = dataSource;
        dataSource = null;
        if (ds != null && !ds.isClosed()) {
            ds.close();
        }
    }
}
