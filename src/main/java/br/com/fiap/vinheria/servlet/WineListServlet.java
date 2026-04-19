package br.com.fiap.vinheria.servlet;

import br.com.fiap.vinheria.catalog.WineCatalog;
import br.com.fiap.vinheria.catalog.WineFilters;
import br.com.fiap.vinheria.model.Wine;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class WineListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Catálogo de vinhos");

        List<Wine> all = WineCatalog.all();
        List<Wine> filtered = WineFilters.apply(all, req);

        req.setAttribute("wines", filtered);
        req.setAttribute("totalResultados", filtered.size());
        req.setAttribute("paises", WineFilters.distinctSorted(all, Wine::getCountry));
        req.setAttribute("uvas", WineFilters.distinctSorted(all, Wine::getGrape));
        req.setAttribute("safras", WineFilters.distinctSorted(all, Wine::getVintage));
        req.setAttribute("classificacoes", WineFilters.distinctSorted(all, Wine::getClassification));

        req.getRequestDispatcher("/vinhos.jsp").forward(req, resp);
    }
}
