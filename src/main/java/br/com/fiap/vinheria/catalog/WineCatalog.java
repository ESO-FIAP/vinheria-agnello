package br.com.fiap.vinheria.catalog;

import br.com.fiap.vinheria.model.Wine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class WineCatalog {

    private static final List<Wine> WINES = build();

    private WineCatalog() {
    }

    private static List<Wine> build() {
        List<Wine> list = new ArrayList<>();
        list.add(new Wine(
                1,
                "Reserva Malbec",
                "Mendoza, Argentina",
                "Malbec",
                new BigDecimal("89.90"),
                "Notas de ameixa, chocolate amargo e taninos sedosos. Conteúdo fictício para demonstração.",
                "https://picsum.photos/seed/vinho-malbec/600/400"
        ));
        list.add(new Wine(
                2,
                "Gran Corte Tinto",
                "Vale Central, Chile",
                "Carménère / Cabernet",
                new BigDecimal("129.00"),
                "Blend encorpado com final longo e especiarias. Texto dummy apenas para o catálogo.",
                "https://picsum.photos/seed/vinho-carmenere/600/400"
        ));
        list.add(new Wine(
                3,
                "Chardonnay Barricado",
                "Serra Gaúcha, Brasil",
                "Chardonnay",
                new BigDecimal("74.50"),
                "Cremoso, com notas de baunilha e frutas brancas. Descrição ilustrativa sem vínculo com produto real.",
                "https://picsum.photos/seed/vinho-chardonnay/600/400"
        ));
        list.add(new Wine(
                4,
                "Pinot Noir Première",
                "Casablanca, Chile",
                "Pinot Noir",
                new BigDecimal("159.90"),
                "Elegante, acidez vibrante e morango silvestre. Dados de exemplo para a loja demo.",
                "https://picsum.photos/seed/vinho-pinot/600/400"
        ));
        list.add(new Wine(
                5,
                "Espumante Brut Nature",
                "Champagne (inspirado), dummy",
                "Chardonnay / Pinot Noir",
                new BigDecimal("199.00"),
                "Bolhas finas e perlage persistente. Rótulo e região são placeholders do projeto acadêmico.",
                "https://picsum.photos/seed/vinho-espumante/600/400"
        ));
        list.add(new Wine(
                6,
                "Tinto Suave Casa",
                "Sudeste, Brasil",
                "Bordô / Isabel",
                new BigDecimal("39.90"),
                "Leve, frutado e fácil de harmonizar. Imagem e copy gerados para testes de interface.",
                "https://picsum.photos/seed/vinho-suave/600/400"
        ));
        return Collections.unmodifiableList(list);
    }

    public static List<Wine> all() {
        return WINES;
    }

    public static Optional<Wine> findById(int id) {
        return WINES.stream().filter(w -> w.getId() == id).findFirst();
    }
}
