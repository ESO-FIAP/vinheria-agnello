package br.com.fiap.vinheria.servlet;

import br.com.fiap.vinheria.catalog.WineCatalog;
import br.com.fiap.vinheria.model.Wine;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class WineDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawId = req.getParameter("id");
        if (rawId == null || rawId.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/vinhos");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(rawId.trim());
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath() + "/vinhos");
            return;
        }
        Optional<Wine> wine = WineCatalog.findById(id);
        if (wine.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute("pageTitle", wine.get().getName());
        req.setAttribute("wine", wine.get());
        req.getRequestDispatcher("/vinho.jsp").forward(req, resp);
    }
}
