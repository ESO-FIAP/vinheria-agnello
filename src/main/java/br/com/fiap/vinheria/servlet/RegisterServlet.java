package br.com.fiap.vinheria.servlet;

import br.com.fiap.vinheria.store.UserStore;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Criar conta");
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (nome == null || nome.isBlank() || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            req.setAttribute("pageTitle", "Criar conta");
            req.setAttribute("erro", "Preencha nome, e-mail e senha.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        try {
            UserStore.getInstance().register(email, password, nome);
        } catch (IllegalStateException ex) {
            req.setAttribute("pageTitle", "Criar conta");
            req.setAttribute("erro", "Este e-mail já está cadastrado.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
