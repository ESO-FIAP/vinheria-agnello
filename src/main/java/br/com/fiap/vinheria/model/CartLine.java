package br.com.fiap.vinheria.model;

import java.math.BigDecimal;

public final class CartLine {

    private final Wine wine;
    private final int quantity;

    public CartLine(Wine wine, int quantity) {
        this.wine = wine;
        this.quantity = quantity;
    }

    public Wine getWine() {
        return wine;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return wine.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
