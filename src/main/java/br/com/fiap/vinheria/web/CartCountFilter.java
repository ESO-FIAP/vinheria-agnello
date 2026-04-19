package br.com.fiap.vinheria.web;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Expõe {@code cartItemCount} no request para o menu (badge do carrinho).
 */
public class CartCountFilter implements Filter {

    public static final String CART_ITEM_COUNT = "cartItemCount";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest hreq) {
            int n = CartHelper.totalQuantity(hreq.getSession(false));
            hreq.setAttribute(CART_ITEM_COUNT, Integer.valueOf(n));
        }
        chain.doFilter(request, response);
    }
}
