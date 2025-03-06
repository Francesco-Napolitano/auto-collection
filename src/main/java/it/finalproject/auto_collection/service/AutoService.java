package it.finalproject.auto_collection.service;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.repo.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.metadata.IIOInvalidTreeException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    //get per riavere tutte le auto
    public List<Auto> getAllAuto(){
        return autoRepository.findAll();
    }

    //get per riavere un'auto specifica per id
    public Optional<Auto> getAutoById(Long id) {
        return autoRepository.findById(id);
    }

    public List<Auto> getFilteredAutos(Long brandID, Long nazioneId, String alimentazione, String modello, Integer anno, BigDecimal prezzo, String carrozzeria, String unitaVendute) {
        if (brandID!= null && nazioneId!= null) {
            return autoRepository.findByBrandIdAndNazioneId(brandID, nazioneId);
        } else if (brandID != null) {
            return autoRepository.findByBrandId(brandID);
        } else if(nazioneId != null) {
            return autoRepository.findByNazioneId(nazioneId);
        } else if (alimentazione != null) {
            return autoRepository.findByAlimentazione(alimentazione);
        } else if (modello != null) {
            return autoRepository.findByModello(modello);
        } else if (anno != null) {
            return autoRepository.findByAnno(anno);
        } else if (prezzo != null) {
            return autoRepository.findByPrezzo(prezzo);
        } else if (carrozzeria != null) {
            return autoRepository.findByCarrozzeria(carrozzeria);
        } else if (unitaVendute != null) {
            return autoRepository.findByUnitaVendute(unitaVendute);
        } else {
            return autoRepository.findAll();
        }
    }

    //Salva per poter salvare un'auto nuova
    public Auto saveAuto(Auto auto){
        return autoRepository.save(auto);
    }

    //modifica un auto esistente con update
    public Optional<Auto> updateAuto(Long id, Auto autoUpdated){
        return autoRepository.findById(id).map(auto -> {
            auto.setModello(autoUpdated.getModello());
            auto.setAnno(autoUpdated.getAnno());
            auto.setPrezzo(autoUpdated.getPrezzo());
            auto.setCarrozzeria(autoUpdated.getCarrozzeria());
            auto.setUnitaVendute(autoUpdated.getUnitaVendute());
            auto.setAlimentazione(autoUpdated.getAlimentazione());
            return autoRepository.save(auto);
        });
    }

    //Delete per poter eliminare un'auto
    public void deleteAuto(Long id){
        autoRepository.deleteById(id);
    }
}
