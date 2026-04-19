package br.com.fiap.vinheria.store;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class UserStore {

    private static final UserStore INSTANCE = new UserStore();

    private final ConcurrentHashMap<String, String> emailToPassword = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> emailToDisplayName = new ConcurrentHashMap<>();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    public boolean exists(String email) {
        return emailToPassword.containsKey(normalizeEmail(email));
    }

    public void register(String email, String password, String displayName) {
        String key = normalizeEmail(email);
        if (key.isEmpty()) {
            throw new IllegalArgumentException("email inválido");
        }
        Objects.requireNonNull(password, "senha");
        Objects.requireNonNull(displayName, "nome");
        String previous = emailToPassword.putIfAbsent(key, password);
        if (previous != null) {
            throw new IllegalStateException("email já cadastrado");
        }
        emailToDisplayName.put(key, displayName.trim().isEmpty() ? key : displayName.trim());
    }

    public Optional<String> validateLogin(String email, String password) {
        String key = normalizeEmail(email);
        String stored = emailToPassword.get(key);
        if (stored == null || password == null || !stored.equals(password)) {
            return Optional.empty();
        }
        return Optional.ofNullable(emailToDisplayName.getOrDefault(key, key));
    }

    private static String normalizeEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }
}
