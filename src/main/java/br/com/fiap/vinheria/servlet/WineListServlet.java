package br.com.fiap.vinheria.servlet;

import br.com.fiap.vinheria.catalog.WineCatalog;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WineListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Catálogo de vinhos");
        req.setAttribute("wines", WineCatalog.all());
        req.getRequestDispatcher("/vinhos.jsp").forward(req, resp);
    }
}
