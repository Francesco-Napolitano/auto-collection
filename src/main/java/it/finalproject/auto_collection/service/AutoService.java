package it.finalproject.auto_collection.service;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.repo.AutoRepository;
import it.finalproject.auto_collection.specifications.AutoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

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
    public Auto saveAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    //modifica un auto esistente con update
    public Optional<Auto> updateAuto(Long id, Auto autoUpdated) {
        return autoRepository.findById(id).map(auto -> {
            auto.setModello(autoUpdated.getModello());
            auto.setAnno(autoUpdated.getAnno());
            auto.setPrezzo(autoUpdated.getPrezzo());
            auto.setCarrozzeria(autoUpdated.getCarrozzeria());
            auto.setUnitaVendute(autoUpdated.getUnitaVendute());
            auto.setAlimentazione(autoUpdated.getAlimentazione());
            auto.setBrand(autoUpdated.getBrand());
            auto.setCilindrata(autoUpdated.getCilindrata());
            auto.setCoppia(autoUpdated.getCoppia());
            auto.setDescrizione(autoUpdated.getDescrizione());
            auto.setPotenza(autoUpdated.getPotenza());
            auto.setPeso(autoUpdated.getPeso());
            auto.setHeight(autoUpdated.getHeight());
            auto.setLength(autoUpdated.getLength());
            auto.setMotore(autoUpdated.getMotore());
            auto.setPosizioneMotore(autoUpdated.getPosizioneMotore());
            auto.setStrutturaMotore(autoUpdated.getStrutturaMotore());
            auto.setTrazione(autoUpdated.getTrazione());
            auto.setVelocitaMax(autoUpdated.getVelocitaMax());
            auto.setWidth(autoUpdated.getWidth());
            return autoRepository.save(auto);
        });
    }


    //Delete per poter eliminare un'auto
    public void deleteAuto(Long id) {
        autoRepository.deleteById(id);
    }
}
