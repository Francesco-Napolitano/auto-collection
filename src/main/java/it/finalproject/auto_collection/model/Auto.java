package it.finalproject.auto_collection.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "automobili")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonManagedReference
    private Brand brand;
    // ho aggiunto il @JsonManagedReference perché mi serve l'id del brand quando utilizzo la sezione filtri

    @ManyToOne (fetch = FetchType.EAGER) //questo tipo di Fetch permette di caricare prima la nazione ed è utile per l'inserimento
    // dei dati all'interno dei filtri
    @JoinColumn(name = "nazione_id")
    @JsonManagedReference
    private Nazione nazione;

    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Permette di vedere la lista di immagini nel JSON
    private List<ImmagineAutomobile> immagini;



}
