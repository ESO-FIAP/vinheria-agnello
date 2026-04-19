package br.com.fiap.vinheria.web;

public final class SessionKeys {

    public static final String USER = "usuario";
    public static final String CART = "carrinho";
    public static final String CHECKOUT_MESSAGE = "checkoutMensagem";
    /** Flash: erro de validação no checkout (endereço). */
    public static final String CARRINHO_ERRO = "carrinhoErro";

    private SessionKeys() {
    }
}
