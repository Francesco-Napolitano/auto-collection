package it.finalproject.auto_collection.service;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.repo.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    //get per riavere un'auto specifica per marca
    public List<Auto> getAutoByBrand(Long brandId) {
        return autoRepository.findByBrandId(brandId);
    }

    //get per riavere un'auto per nazione specifica
    public List<Auto> getAutoByNazione(Long nazioneId){
        return autoRepository.findByNazioneId(nazioneId);
    }

    //get per riavere un'auto per marca e nazione specifica
    public List<Auto> getAutoByBrandAndNazione(Long brandId, Long nazioneId){
        return autoRepository.findByBrandIdAndNazioneId(brandId, nazioneId);
    }

    //get per riavere un'auto per alimentazione specifica
    public List<Auto> getAutoByAlimentazione (String alimentazione){
        return autoRepository.findByAlimentazione(alimentazione);
    }

    //get per riavere un'auto per modello specifico
    public List<Auto> getAutoByModello(String modello){
        return autoRepository.findByModello(modello);
    }

    //get per riavere un'auto per anno specifico
    public List<Auto> getAutoByAnno(Integer anno){
        return autoRepository.findByAnno(anno);
    }

    //get per riavere un'auto per prezzo specifico
    public List<Auto> getAutoByPrezzo(BigDecimal prezzo){
        return autoRepository.findByPrezzo(prezzo);
    }

    //get per riavere un'auto per carrozzeria specifica
    public List<Auto> getAutoByCarrozzeria(String carrozzeria){
        return autoRepository.findByCarrozzeria(carrozzeria);
    }

    //get per riavere un'auto per unita vendute specifica
    public List<Auto> getAutoByUnitaVendute(String unitaVendute){
        return autoRepository.findByUnitaVendute(unitaVendute);
    }

    //Salva per poter salvare un'auto nuova o modificarla
    public Auto saveAuto(Auto auto){
        return autoRepository.save(auto);
    }

    //Delete per poter eliminare un'auto
    public void deleteAuto(Long id){
        autoRepository.deleteById(id);
    }
}
