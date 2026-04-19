package br.com.fiap.vinheria.model;

import java.util.Objects;

public final class CartItem {

    private final int wineId;
    private int quantity;

    public CartItem(int wineId, int quantity) {
        if (wineId <= 0) {
            throw new IllegalArgumentException("wineId inválido");
        }
        this.wineId = wineId;
        setQuantity(quantity);
    }

    public int getWineId() {
        return wineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("quantidade inválida");
        }
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItem other)) {
            return false;
        }
        return wineId == other.wineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wineId);
    }
}
