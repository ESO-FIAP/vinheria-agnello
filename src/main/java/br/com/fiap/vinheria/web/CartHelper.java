package br.com.fiap.vinheria.web;

import br.com.fiap.vinheria.model.CartItem;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public final class CartHelper {

    private CartHelper() {
    }

    @SuppressWarnings("unchecked")
    public static List<CartItem> getOrCreateCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(SessionKeys.CART);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(SessionKeys.CART, cart);
        }
        return cart;
    }

    public static void clearCart(HttpSession session) {
        session.removeAttribute(SessionKeys.CART);
    }
}
