package br.com.fiap.vinheria.model;

import java.util.Objects;

public final class UsuarioSessao {

    private final String email;
    private final String displayName;

    public UsuarioSessao(String email, String displayName) {
        this.email = Objects.requireNonNull(email);
        this.displayName = Objects.requireNonNull(displayName);
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }
}
