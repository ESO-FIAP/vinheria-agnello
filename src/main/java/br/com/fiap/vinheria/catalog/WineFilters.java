package br.com.fiap.vinheria.catalog;

import br.com.fiap.vinheria.model.Wine;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Stream;

public final class WineFilters {

    private WineFilters() {
    }

    public static List<Wine> apply(List<Wine> source, HttpServletRequest req) {
        String nome = trimToEmpty(req.getParameter("nome"));
        BigDecimal rawMin = parseBigDecimal(req.getParameter("precoMin"));
        BigDecimal rawMax = parseBigDecimal(req.getParameter("precoMax"));
        final BigDecimal precoMin;
        final BigDecimal precoMax;
        if (rawMin != null && rawMax != null && rawMin.compareTo(rawMax) > 0) {
            precoMin = rawMax;
            precoMax = rawMin;
        } else {
            precoMin = rawMin;
            precoMax = rawMax;
        }
        String pais = trimToNull(req.getParameter("pais"));
        String uva = trimToEmpty(req.getParameter("uva"));
        String safra = trimToNull(req.getParameter("safra"));
        String classificacao = trimToNull(req.getParameter("classificacao"));

        Stream<Wine> stream = source.stream();
        if (!nome.isEmpty()) {
            String needle = nome.toLowerCase(Locale.ROOT);
            stream = stream.filter(w -> w.getName().toLowerCase(Locale.ROOT).contains(needle));
        }
        if (precoMin != null) {
            stream = stream.filter(w -> w.getPrice().compareTo(precoMin) >= 0);
        }
        if (precoMax != null) {
            stream = stream.filter(w -> w.getPrice().compareTo(precoMax) <= 0);
        }
        if (pais != null) {
            stream = stream.filter(w -> w.getCountry().equalsIgnoreCase(pais));
        }
        if (!uva.isEmpty()) {
            String uvaNeedle = uva.toLowerCase(Locale.ROOT);
            stream = stream.filter(w -> w.getGrape().toLowerCase(Locale.ROOT).contains(uvaNeedle));
        }
        if (safra != null) {
            stream = stream.filter(w -> w.getVintage().equalsIgnoreCase(safra));
        }
        if (classificacao != null) {
            stream = stream.filter(w -> w.getClassification().equalsIgnoreCase(classificacao));
        }
        return stream.toList();
    }

    public static List<String> distinctSorted(List<Wine> wines, Function<Wine, String> extractor) {
        return wines.stream()
                .map(extractor)
                .distinct()
                .sorted(Comparator.comparing(String::toLowerCase, Comparator.naturalOrder()))
                .toList();
    }

    private static String trimToNull(String raw) {
        if (raw == null) {
            return null;
        }
        String t = raw.trim();
        return t.isEmpty() ? null : t;
    }

    private static String trimToEmpty(String raw) {
        return raw == null ? "" : raw.trim();
    }

    private static BigDecimal parseBigDecimal(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        try {
            return new BigDecimal(raw.trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
