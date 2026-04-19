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

        String rua = trimToNull(req.getParameter("rua"));
        String bairro = trimToNull(req.getParameter("bairro"));
        String cep = trimToNull(req.getParameter("cep"));
        String numero = trimToNull(req.getParameter("numero"));
        // complemento é opcional — não validar vazio

        if (rua == null || bairro == null || cep == null || numero == null) {
            req.getSession().setAttribute(
                    SessionKeys.CARRINHO_ERRO,
                    "Preencha rua, bairro, CEP e número da casa para confirmar a compra."
            );
            resp.sendRedirect(req.getContextPath() + "/carrinho");
            return;
        }

        CartHelper.clearCart(req.getSession());
        req.getSession().setAttribute(SessionKeys.CHECKOUT_MESSAGE, "Compra realizada com sucesso");
        resp.sendRedirect(req.getContextPath() + "/sucesso.jsp");
    }

    private static String trimToNull(String raw) {
        if (raw == null) {
            return null;
        }
        String t = raw.trim();
        return t.isEmpty() ? null : t;
    }
}
