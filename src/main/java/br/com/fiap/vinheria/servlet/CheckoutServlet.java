package br.com.fiap.vinheria.servlet;

import br.com.fiap.vinheria.model.CartItem;
import br.com.fiap.vinheria.web.CartHelper;
import br.com.fiap.vinheria.web.SessionKeys;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<CartItem> cart = CartHelper.getOrCreateCart(req.getSession());
        if (cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/carrinho");
            return;
        }
        CartHelper.clearCart(req.getSession());
        req.getSession().setAttribute(SessionKeys.CHECKOUT_MESSAGE, "Compra realizada com sucesso");
        resp.sendRedirect(req.getContextPath() + "/sucesso.jsp");
    }
}
