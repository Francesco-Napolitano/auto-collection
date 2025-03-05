package it.finalproject.auto_collection.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nazione")
public class Nazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String imageNation;

    @OneToMany(mappedBy = "nazione", cascade = CascadeType.ALL)
    private List<Auto> automobili;


}
