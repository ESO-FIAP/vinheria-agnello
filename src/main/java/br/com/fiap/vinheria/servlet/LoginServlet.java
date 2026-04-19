package br.com.fiap.vinheria.servlet;

import br.com.fiap.vinheria.model.UsuarioSessao;
import br.com.fiap.vinheria.store.UserStore;
import br.com.fiap.vinheria.web.SessionKeys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Entrar");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<String> display = UserStore.getInstance().validateLogin(email, password);
        if (display.isPresent()) {
            String normalized = email == null ? "" : email.trim().toLowerCase();
            req.getSession().setAttribute(SessionKeys.USER, new UsuarioSessao(normalized, display.get()));
            resp.sendRedirect(req.getContextPath() + "/vinhos");
            return;
        }
        req.setAttribute("pageTitle", "Entrar");
        req.setAttribute("erro", "E-mail ou senha inválidos.");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
