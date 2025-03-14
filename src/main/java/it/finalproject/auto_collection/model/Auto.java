package it.finalproject.auto_collection.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "automobili")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String modello;
    private Integer anno;
    private String motore;
    private Integer potenza;
    private Integer coppia;
    private Integer velocitaMax;
    private BigDecimal prezzo;
    @Column(columnDefinition = "TEXT")
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

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "nazione_id")
    @JsonBackReference
    private Nazione nazione;



}
