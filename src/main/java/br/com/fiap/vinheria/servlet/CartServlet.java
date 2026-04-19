package br.com.fiap.vinheria.servlet;

import br.com.fiap.vinheria.catalog.WineCatalog;
import br.com.fiap.vinheria.model.CartItem;
import br.com.fiap.vinheria.model.CartLine;
import br.com.fiap.vinheria.model.Wine;
import br.com.fiap.vinheria.web.CartHelper;
import br.com.fiap.vinheria.web.SessionKeys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Carrinho");
        List<CartLine> lines = buildLines(req);
        req.setAttribute("cartLines", lines);
        BigDecimal total = BigDecimal.ZERO;
        for (CartLine line : lines) {
            total = total.add(line.getSubtotal());
        }
        req.setAttribute("cartTotal", total);
        Object flashErro = req.getSession().getAttribute(SessionKeys.CARRINHO_ERRO);
        if (flashErro instanceof String s && !s.isBlank()) {
            req.setAttribute("erroCheckout", s);
            req.getSession().removeAttribute(SessionKeys.CARRINHO_ERRO);
        }
        req.getRequestDispatcher("/carrinho.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/carrinho");
            return;
        }

        List<CartItem> cart = CartHelper.getOrCreateCart(req.getSession());

        switch (action) {
            case "add" -> handleAdd(req, cart);
            case "update" -> handleUpdate(req, cart);
            case "remove" -> handleRemove(req, cart);
            default -> {
            }
        }

        String next = req.getParameter("next");
        if (next != null && !next.isBlank() && next.startsWith("/") && !next.startsWith("//")) {
            resp.sendRedirect(req.getContextPath() + next);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/carrinho");
    }

    private static void handleAdd(HttpServletRequest req, List<CartItem> cart) {
        int wineId = parsePositiveInt(req.getParameter("wineId"), 0);
        int qty = parsePositiveInt(req.getParameter("quantity"), 1);
        if (wineId <= 0 || WineCatalog.findById(wineId).isEmpty()) {
            return;
        }
        Optional<CartItem> existing = cart.stream().filter(c -> c.getWineId() == wineId).findFirst();
        if (existing.isPresent()) {
            int sum = existing.get().getQuantity() + qty;
            existing.get().setQuantity(sum);
        } else {
            cart.add(new CartItem(wineId, qty));
        }
    }

    private static void handleUpdate(HttpServletRequest req, List<CartItem> cart) {
        int wineId = parsePositiveInt(req.getParameter("wineId"), 0);
        int qty = parsePositiveInt(req.getParameter("quantity"), 1);
        if (wineId <= 0) {
            return;
        }
        for (CartItem item : cart) {
            if (item.getWineId() == wineId) {
                item.setQuantity(qty);
                return;
            }
        }
    }

    private static void handleRemove(HttpServletRequest req, List<CartItem> cart) {
        int wineId = parsePositiveInt(req.getParameter("wineId"), 0);
        cart.removeIf(c -> c.getWineId() == wineId);
    }

    private static int parsePositiveInt(String raw, int defaultValue) {
        if (raw == null || raw.isBlank()) {
            return defaultValue;
        }
        try {
            int v = Integer.parseInt(raw.trim());
            return v > 0 ? v : defaultValue;
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    static List<CartLine> buildLines(HttpServletRequest req) {
        List<CartItem> cart = CartHelper.getOrCreateCart(req.getSession());
        List<CartLine> lines = new ArrayList<>();
        for (CartItem item : cart) {
            Optional<Wine> w = WineCatalog.findById(item.getWineId());
            w.ifPresent(wine -> lines.add(new CartLine(wine, item.getQuantity())));
        }
        return lines;
    }
}
