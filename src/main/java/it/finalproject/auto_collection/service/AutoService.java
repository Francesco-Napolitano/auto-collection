package it.finalproject.auto_collection.service;

import it.finalproject.auto_collection.DTO.AutoDTO;
import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.model.Brand;
import it.finalproject.auto_collection.model.ImmagineAutomobile;
import it.finalproject.auto_collection.model.Nazione;
import it.finalproject.auto_collection.repo.AutoRepository;
import it.finalproject.auto_collection.repo.BrandRepository;
import it.finalproject.auto_collection.repo.ImmagineAutomobileRepository;
import it.finalproject.auto_collection.repo.NazioneRepository;
import it.finalproject.auto_collection.specifications.AutoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private NazioneRepository nazioneRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ImmagineAutomobileRepository immagineAutomobileRepository;


    //get per riavere tutte le auto
    public List<Auto> getAllAuto() {
        return autoRepository.findAll();
    }

    //get per riavere un'auto specifica per id
    public Optional<Auto> getAutoById(Long id) {
        return autoRepository.findById(id);
    }

    //il bello di questo metodo è che risulta tutto DINAMICO e viene tutto aggiunto al path semplicemente grazie al .and() che è appunto in grado di aggiungere la query alla Specification sempre all'interno del path
    public List<Auto> getFilteredAutos(Long brandId, Long nazioneId, String alimentazione, String modello, Integer annoMin, BigDecimal prezzoMin, String carrozzeria, String unitaVendute) {

        Specification<Auto> specification = Specification.where(null);

        if (brandId != null) specification = specification.and(AutoSpecifications.hasBrand(brandId));
        if (nazioneId != null) specification = specification.and(AutoSpecifications.hasNazione(nazioneId));
        if (alimentazione != null)
            specification = specification.and(AutoSpecifications.hasAlimentazione(alimentazione));
        if (modello != null) specification = specification.and(AutoSpecifications.hasModello(modello));
        if (annoMin != null) specification = specification.and(AutoSpecifications.hasAnno(annoMin));
        if (prezzoMin != null) specification = specification.and(AutoSpecifications.hasPrezzo(prezzoMin));
        if (carrozzeria != null) specification = specification.and(AutoSpecifications.hasCarrozzeria(carrozzeria));
        if (unitaVendute != null) specification = specification.and(AutoSpecifications.hasUnitaVendute(unitaVendute));

        return autoRepository.findAll(specification);
    }

    //Salva per poter salvare un'auto nuova
    public Auto saveAuto(AutoDTO autoDTO) {
        Auto auto = new Auto();
        auto.setNome(autoDTO.getNome());
        auto.setModello(autoDTO.getModello());
        auto.setAnno(autoDTO.getAnno());
        auto.setMotore(autoDTO.getMotore());
        auto.setPotenza(autoDTO.getPotenza());
        auto.setCoppia(autoDTO.getCoppia());
        auto.setVelocitaMax(autoDTO.getVelocitaMax());
        auto.setPrezzo(autoDTO.getPrezzo());
        auto.setDescrizione(autoDTO.getDescrizione());
        auto.setAlimentazione(autoDTO.getAlimentazione());
        auto.setLength(autoDTO.getLength());
        auto.setWidth(autoDTO.getWidth());
        auto.setHeight(autoDTO.getHeight());
        auto.setPeso(autoDTO.getPeso());
        auto.setCilindrata(autoDTO.getCilindrata());
        auto.setStrutturaMotore(autoDTO.getStrutturaMotore());
        auto.setTrazione(autoDTO.getTrazione());
        auto.setPosizioneMotore(autoDTO.getPosizioneMotore());
        auto.setCarrozzeria(autoDTO.getCarrozzeria());
        auto.setUnitaVendute(autoDTO.getUnitaVendute());

        // Associazione del Brand
        Brand brand = brandRepository.findById(autoDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand non trovato"));
        auto.setBrand(brand);

        // Associazione della Nazione
        Nazione nazione = nazioneRepository.findById(autoDTO.getNazioneId())
                .orElseThrow(() -> new RuntimeException("Nazione non trovata"));
        auto.setNazione(nazione);

        // Associazione delle immagini
        if (autoDTO.getImmaginiIds() != null) {
            List<ImmagineAutomobile> immagini = autoDTO.getImmaginiIds().stream()
                    .map(id -> immagineAutomobileRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Immagine non trovata")))
                    .collect(Collectors.toList());
            auto.setImmagini(immagini);
        }

        return autoRepository.save(auto);

    }

    //modifica un auto esistente con update
    public Auto updateAuto(Long id, AutoDTO autoDTO) {
        return autoRepository.findById(id).map(auto -> {
            auto.setModello(autoDTO.getModello());
            auto.setAnno(autoDTO.getAnno());
            auto.setPrezzo(autoDTO.getPrezzo());
            auto.setCarrozzeria(autoDTO.getCarrozzeria());
            auto.setUnitaVendute(autoDTO.getUnitaVendute());
            auto.setAlimentazione(autoDTO.getAlimentazione());
            auto.setCilindrata(autoDTO.getCilindrata());
            auto.setCoppia(autoDTO.getCoppia());
            auto.setDescrizione(autoDTO.getDescrizione());
            auto.setPotenza(autoDTO.getPotenza());
            auto.setPeso(autoDTO.getPeso());
            auto.setHeight(autoDTO.getHeight());
            auto.setLength(autoDTO.getLength());
            auto.setMotore(autoDTO.getMotore());
            auto.setPosizioneMotore(autoDTO.getPosizioneMotore());
            auto.setStrutturaMotore(autoDTO.getStrutturaMotore());
            auto.setTrazione(autoDTO.getTrazione());
            auto.setVelocitaMax(autoDTO.getVelocitaMax());
            auto.setWidth(autoDTO.getWidth());

            // Assegna il Brand (tramite l'id) se esiste, altrimenti non fa nulla
            if (autoDTO.getBrandId() != null) {
                Brand brand = brandRepository.findById(autoDTO.getBrandId()).orElse(null);
                auto.setBrand(brand);
            }
            // Assegna la Nazione (tramite l'id) se esiste, altrimenti non fa nulla
            if (autoDTO.getNazioneId() != null) {
                Nazione nazione = nazioneRepository.findById(autoDTO.getNazioneId()).orElse(null);
                auto.setNazione(nazione);
            }

            // Dopo tutte le modifiche  l'auto viene salvata
            return autoRepository.save(auto);
        }).orElse(null);

    }


    //Delete per poter eliminare un'auto
    public void deleteAuto(Long id) {
        autoRepository.deleteById(id);
    }
}
