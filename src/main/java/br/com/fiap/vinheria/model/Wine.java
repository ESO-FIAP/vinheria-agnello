package br.com.fiap.vinheria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Wine {

    private final int id;
    private final String name;
    private final String region;
    private final String country;
    private final String grape;
    private final BigDecimal price;
    private final String description;
    private final String imageUrl;
    private final String vintage;
    private final String classification;
    private final String servingTemperature;
    private final String abv;
    private final int volumeMl;
    private final String maturationNotes;
    private final List<String> pairingItems;

    private Wine(Builder b) {
        this.id = b.id;
        this.name = Objects.requireNonNull(b.name, "name");
        this.region = Objects.requireNonNull(b.region, "region");
        this.country = Objects.requireNonNull(b.country, "country");
        this.grape = Objects.requireNonNull(b.grape, "grape");
        this.price = Objects.requireNonNull(b.price, "price");
        this.description = Objects.requireNonNull(b.description, "description");
        this.imageUrl = Objects.requireNonNull(b.imageUrl, "imageUrl");
        this.vintage = Objects.requireNonNull(b.vintage, "vintage");
        this.classification = Objects.requireNonNull(b.classification, "classification");
        this.servingTemperature = Objects.requireNonNull(b.servingTemperature, "servingTemperature");
        this.abv = Objects.requireNonNull(b.abv, "abv");
        this.volumeMl = b.volumeMl;
        this.maturationNotes = Objects.requireNonNull(b.maturationNotes, "maturationNotes");
        this.pairingItems = List.copyOf(b.pairingItems);
    }

    public static Builder builder() {
        return new Builder();
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

    public String getCountry() {
        return country;
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

    public String getVintage() {
        return vintage;
    }

    public String getClassification() {
        return classification;
    }

    public String getServingTemperature() {
        return servingTemperature;
    }

    public String getAbv() {
        return abv;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public String getMaturationNotes() {
        return maturationNotes;
    }

    public List<String> getPairingItems() {
        return pairingItems;
    }

    public static final class Builder {
        private int id;
        private String name;
        private String region;
        private String country;
        private String grape;
        private BigDecimal price;
        private String description;
        private String imageUrl;
        private String vintage;
        private String classification;
        private String servingTemperature;
        private String abv;
        private int volumeMl = 750;
        private String maturationNotes;
        private final List<String> pairingItems = new ArrayList<>();

        private Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder grape(String grape) {
            this.grape = grape;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder vintage(String vintage) {
            this.vintage = vintage;
            return this;
        }

        public Builder classification(String classification) {
            this.classification = classification;
            return this;
        }

        public Builder servingTemperature(String servingTemperature) {
            this.servingTemperature = servingTemperature;
            return this;
        }

        public Builder abv(String abv) {
            this.abv = abv;
            return this;
        }

        public Builder volumeMl(int volumeMl) {
            this.volumeMl = volumeMl;
            return this;
        }

        public Builder maturationNotes(String maturationNotes) {
            this.maturationNotes = maturationNotes;
            return this;
        }

        public Builder addPairing(String item) {
            this.pairingItems.add(item);
            return this;
        }

        public Wine build() {
            return new Wine(this);
        }
    }
}
