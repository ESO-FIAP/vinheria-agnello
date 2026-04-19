package br.com.fiap.vinheria.repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public final class UserRepository {

    private static final String INSERT = """
            INSERT INTO users (nome_user, email, senha, indicado_por)
            VALUES (?, ?, ?, NULL)
            """;
    private static final String EXISTS = "SELECT 1 FROM users WHERE lower(email) = ? LIMIT 1";
    private static final String LOGIN = """
            SELECT nome_user FROM users WHERE lower(email) = ? AND senha = ?
            """;

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean existsByEmail(String normalizedEmail) throws SQLException {
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(EXISTS)) {
            ps.setString(1, normalizedEmail);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insert(String nomeUser, String normalizedEmail, String senha) throws SQLException {
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(INSERT)) {
            ps.setString(1, nomeUser);
            ps.setString(2, normalizedEmail);
            ps.setString(3, senha);
            ps.executeUpdate();
        }
    }

    public Optional<String> findDisplayNameIfPasswordMatches(String normalizedEmail, String senha)
            throws SQLException {
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(LOGIN)) {
            ps.setString(1, normalizedEmail);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                String nome = rs.getString(1);
                return Optional.ofNullable(nome);
            }
        }
    }
}
