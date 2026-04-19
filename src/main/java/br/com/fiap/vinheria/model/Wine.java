package br.com.fiap.vinheria.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Wine {

    private final int id;
    private final String name;
    private final String region;
    private final String grape;
    private final BigDecimal price;
    private final String description;
    private final String imageUrl;

    public Wine(int id, String name, String region, String grape, BigDecimal price, String description,
                String imageUrl) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.region = Objects.requireNonNull(region);
        this.grape = Objects.requireNonNull(grape);
        this.price = Objects.requireNonNull(price);
        this.description = Objects.requireNonNull(description);
        this.imageUrl = Objects.requireNonNull(imageUrl);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getGrape() {
        return grape;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
