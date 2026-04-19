package br.com.fiap.vinheria.store;

import br.com.fiap.vinheria.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public final class UserStore {

    private static volatile UserStore instance;

    private final UserRepository repository;

    private UserStore(UserRepository repository) {
        this.repository = repository;
    }

    public static synchronized void init(DataSource dataSource) {
        instance = new UserStore(new UserRepository(dataSource));
    }

    public static synchronized void destroy() {
        instance = null;
    }

    public static UserStore getInstance() {
        UserStore i = instance;
        if (i == null) {
            throw new IllegalStateException("UserStore não inicializado (verifique JDBC_* e DatabaseListener).");
        }
        return i;
    }

    public boolean exists(String email) {
        String key = normalizeEmail(email);
        if (key.isEmpty()) {
            return false;
        }
        try {
            return repository.existsByEmail(key);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(String email, String password, String displayName) {
        String key = normalizeEmail(email);
        if (key.isEmpty()) {
            throw new IllegalArgumentException("email inválido");
        }
        Objects.requireNonNull(password, "senha");
        Objects.requireNonNull(displayName, "nome");
        String nome = displayName.trim().isEmpty() ? key : displayName.trim();
        try {
            repository.insert(nome, key, password);
        } catch (SQLException e) {
            if (isUniqueViolation(e)) {
                throw new IllegalStateException("email já cadastrado", e);
            }
            throw new RuntimeException("Erro ao cadastrar usuário.", e);
        }
    }

    public Optional<String> validateLogin(String email, String password) {
        String key = normalizeEmail(email);
        if (key.isEmpty() || password == null) {
            return Optional.empty();
        }
        try {
            Optional<String> nome = repository.findDisplayNameIfPasswordMatches(key, password);
            return nome.map(n -> n.trim().isEmpty() ? key : n.trim());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isUniqueViolation(SQLException e) {
        for (SQLException cur = e; cur != null; cur = cur.getNextException()) {
            if ("23505".equals(cur.getSQLState())) {
                return true;
            }
        }
        return false;
    }

    private static String normalizeEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }
}
