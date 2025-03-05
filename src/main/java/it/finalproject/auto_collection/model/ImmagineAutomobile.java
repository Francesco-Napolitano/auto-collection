package it.finalproject.auto_collection.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "immagini_automobili")
public class ImmagineAutomobile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String immagineUrl;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Auto auto;


}
