package it.finalproject.auto_collection.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data

public class AutoDTO {
    private String nome;
    private String modello;
    private Integer anno;
    private String motore;
    private Integer potenza;
    private Integer coppia;
    private Integer velocitaMax;
    private BigDecimal prezzo;
    private String descrizione;
    private String alimentazione;
    private Double length;
    private Double width;
    private Double height;
    private Double peso;
    private Integer cilindrata;
    private String strutturaMotore;
    private String trazione;
    private String posizioneMotore;
    private String carrozzeria;
    private String unitaVendute;
    private Long brandId;
    private Long nazioneId;
    private List<Long> immaginiIds;

}
