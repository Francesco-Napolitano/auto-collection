package it.finalproject.auto_collection.repo;


import it.finalproject.auto_collection.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    List<Auto> findByBrandId(Long brandId); // Metodo per ottenere automobili per marca>
    List<Auto> findByNazioneId(Long nazioneId); // Metodo per ottenere automobili per nazione>
    List<Auto> findByAlimentazione(String alimentazione);
    List<Auto> findByModello(String modello);
    List<Auto> findByAnno(Integer anno);
    List<Auto> findByPrezzo(BigDecimal prezzo);
    List<Auto> findByCarrozzeria (String carrozzeria);
    List<Auto> findByUnitaVendute (String unitaVendute);
}
