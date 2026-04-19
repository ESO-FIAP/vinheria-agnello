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
        list.add(Wine.builder()
                .id(1)
                .name("Reserva Malbec")
                .region("Mendoza")
                .country("Argentina")
                .grape("Malbec")
                .price(new BigDecimal("89.90"))
                .description("Notas de ameixa madura, chocolate amargo e taninos sedosos. Estrutura generosa com "
                        + "final persistente — texto fictício para o catálogo acadêmico Vinheria.")
                .imageUrl("static/12726-em-reserva-malbec.png")
                .vintage("2022")
                .classification("Seco")
                .servingTemperature("16 °C a 18 °C")
                .abv("14,0%")
                .volumeMl(750)
                .maturationNotes("Estágio em barricas de carvalho francês e americano por cerca de 12 meses, "
                        + "contribuindo para notas especiadas e integração dos taninos. Conteúdo ilustrativo.")
                .addPairing("Carnes vermelhas grelhadas")
                .addPairing("Cordeiro com ervas")
                .addPairing("Queijos curados e semi-curados")
                .build());
        list.add(Wine.builder()
                .id(2)
                .name("Gran Corte Tinto")
                .region("Vale Central")
                .country("Chile")
                .grape("Carménère / Cabernet Sauvignon")
                .price(new BigDecimal("129.00"))
                .description("Blend encorpado com camadas de fruta negra, pimentão doce e especiarias. Final longo "
                        + "e textura aveludada — dados apenas para demonstração.")
                .imageUrl("static/21601-indomita-varietal-carmenere-375.png")
                .vintage("2021")
                .classification("Seco")
                .servingTemperature("15 °C a 17 °C")
                .abv("14,5%")
                .volumeMl(750)
                .maturationNotes("Passagem moderada em carvalho para preservar frescor da fruta e somar complexidade "
                        + "aromática. Amadurecimento descrito de forma genérica para o protótipo.")
                .addPairing("Carnes vermelhas ao molho")
                .addPairing("Risoto de cogumelos")
                .addPairing("Queijos maturados")
                .build());
        list.add(Wine.builder()
                .id(3)
                .name("Chardonnay Barricado")
                .region("Serra Gaúcha")
                .country("Brasil")
                .grape("Chardonnay")
                .price(new BigDecimal("74.50"))
                .description("Cremoso, com notas de baunilha, frutas brancas e leve tostado. Acidez equilibrada — "
                        + "descrição ilustrativa sem vínculo com produto real.")
                .imageUrl("static/evel-reserva-tinto-site.jpg")
                .vintage("2023")
                .classification("Seco")
                .servingTemperature("10 °C a 12 °C")
                .abv("13,0%")
                .volumeMl(750)
                .maturationNotes("Fermentação e estágio parcial em barrica nova, buscando volume na boca e "
                        + "frescor cítrico no retrogosto. Texto dummy para a ficha técnica.")
                .addPairing("Carnes brancas assadas")
                .addPairing("Peixes com manteiga e ervas")
                .addPairing("Massas ao molho branco")
                .build());
        list.add(Wine.builder()
                .id(4)
                .name("Pinot Noir Première")
                .region("Casablanca")
                .country("Chile")
                .grape("Pinot Noir")
                .price(new BigDecimal("159.90"))
                .description("Elegante, acidez vibrante e notas de morango silvestre e cereja. Taninos finos — "
                        + "exemplo de copy para testes de interface.")
                .imageUrl("static/Indomita-Gran.Reserva-PN-imgsite.png")
                .vintage("2022")
                .classification("Seco")
                .servingTemperature("14 °C a 16 °C")
                .abv("13,5%")
                .volumeMl(750)
                .maturationNotes("Breve passagem em carvalho usado para não mascarar a delicadeza varietal; "
                        + "prioriza fruta e mineralidade percebida. Conteúdo fictício.")
                .addPairing("Patê e entradas leves")
                .addPairing("Carnes brancas com cogumelos")
                .addPairing("Queijos de média cura")
                .build());
        list.add(Wine.builder()
                .id(5)
                .name("Espumante Brut Nature")
                .region("Serra Gaúcha")
                .country("Brasil")
                .grape("Chardonnay / Pinot Noir")
                .price(new BigDecimal("199.00"))
                .description("Bolhas finas, perlage persistente e notas de brioche e frutas cítricas. Seco e "
                        + "vertical — rótulo e região são placeholders do projeto.")
                .imageUrl("static/porca-murca-reserva-tinto.png")
                .vintage("2020")
                .classification("Brut Nature")
                .servingTemperature("6 °C a 8 °C")
                .abv("12,0%")
                .volumeMl(750)
                .maturationNotes("Período prolongado sobre as borras em inox e leve estágio antes do degorgement "
                        + "simulado na narrativa; texto apenas educativo.")
                .addPairing("Frutos do mar")
                .addPairing("Canapés salgados")
                .addPairing("Queijos frescos e cremosos")
                .build());
        list.add(Wine.builder()
                .id(6)
                .name("Tinto Suave Casa")
                .region("Sudeste")
                .country("Brasil")
                .grape("Bordô / Isabel")
                .price(new BigDecimal("39.90"))
                .description("Leve, frutado e fácil de harmonizar no dia a dia. Doçura perceptível suave — "
                        + "imagem e copy gerados para testes de interface.")
                .imageUrl("static/21601-indomita-varietal-carmenere-375.png")
                .vintage("2024")
                .classification("Suave")
                .servingTemperature("12 °C a 14 °C")
                .abv("10,5%")
                .volumeMl(750)
                .maturationNotes("Amadurecimento predominantemente em tanques de inox, preservando aromas "
                        + "frutados e consumo jovem. Descrição genérica para o demo.")
                .addPairing("Churrasco leve e linguiças")
                .addPairing("Massas ao sugo")
                .addPairing("Pizzas")
                .build());
        return Collections.unmodifiableList(list);
    }

    public static List<Wine> all() {
        return WINES;
    }

    public static Optional<Wine> findById(int id) {
        return WINES.stream().filter(w -> w.getId() == id).findFirst();
    }
}
