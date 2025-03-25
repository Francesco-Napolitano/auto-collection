package it.finalproject.auto_collection.DTO;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.model.ImmagineAutomobile;
import it.finalproject.auto_collection.repo.BrandRepository;
import it.finalproject.auto_collection.repo.ImmagineAutomobileRepository;
import it.finalproject.auto_collection.repo.NazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AutoMapper {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private NazioneRepository nazioneRepository;

    @Autowired
    private ImmagineAutomobileRepository immagineAutomobileRepository;

    public Auto toEntity(AutoDTO dto) {
        Auto auto = new Auto();
        auto.setId(dto.getId()); // Aggiunto ID
        auto.setNome(dto.getNome());
        auto.setModello(dto.getModello());
        auto.setAnno(dto.getAnno());
        auto.setMotore(dto.getMotore());
        auto.setPotenza(dto.getPotenza());
        auto.setCoppia(dto.getCoppia());
        auto.setVelocitaMax(dto.getVelocitaMax());
        auto.setPrezzo(dto.getPrezzo());
        auto.setDescrizione(dto.getDescrizione());
        auto.setAlimentazione(dto.getAlimentazione());
        auto.setLength(dto.getLength());
        auto.setWidth(dto.getWidth());
        auto.setHeight(dto.getHeight());
        auto.setPeso(dto.getPeso());
        auto.setCilindrata(dto.getCilindrata());
        auto.setStrutturaMotore(dto.getStrutturaMotore());
        auto.setTrazione(dto.getTrazione());
        auto.setPosizioneMotore(dto.getPosizioneMotore());
        auto.setCarrozzeria(dto.getCarrozzeria());
        auto.setUnitaVendute(dto.getUnitaVendute());

        // Converti brandId in Brand
        if (dto.getBrandId() != null) {
            auto.setBrand(brandRepository.findById(dto.getBrandId()).orElse(null));
        }

        // Converti nazioneId in Nazione
        if (dto.getNazioneId() != null) {
            auto.setNazione(nazioneRepository.findById(dto.getNazioneId()).orElse(null));
        }

        // Converti lista di immaginiIds in List<ImmagineAutomobile>
        if (dto.getImmaginiIds() != null) {
            List<ImmagineAutomobile> immagini = immagineAutomobileRepository.findAllById(dto.getImmaginiIds());
            auto.setImmagini(immagini);
        }

        return auto;
    }

    public AutoDTO toDTO(Auto auto) {
        AutoDTO dto = new AutoDTO();
        dto.setId(auto.getId());
        dto.setNome(auto.getNome());
        dto.setModello(auto.getModello());
        dto.setAnno(auto.getAnno());
        dto.setMotore(auto.getMotore());
        dto.setPotenza(auto.getPotenza());
        dto.setCoppia(auto.getCoppia());
        dto.setVelocitaMax(auto.getVelocitaMax());
        dto.setPrezzo(auto.getPrezzo());
        dto.setDescrizione(auto.getDescrizione());
        dto.setAlimentazione(auto.getAlimentazione());
        dto.setLength(auto.getLength());
        dto.setWidth(auto.getWidth());
        dto.setHeight(auto.getHeight());
        dto.setPeso(auto.getPeso());
        dto.setCilindrata(auto.getCilindrata());
        dto.setStrutturaMotore(auto.getStrutturaMotore());
        dto.setTrazione(auto.getTrazione());
        dto.setPosizioneMotore(auto.getPosizioneMotore());
        dto.setCarrozzeria(auto.getCarrozzeria());
        dto.setUnitaVendute(auto.getUnitaVendute());

        // Converti Brand in brandId
        if (auto.getBrand() != null) {
            dto.setBrandId(auto.getBrand().getId());
        }

        // Converti Nazione in nazioneId
        if (auto.getNazione() != null) {
            dto.setNazioneId(auto.getNazione().getId());
        }

        // Converti List<ImmagineAutomobile> in lista di ID
        if (auto.getImmagini() != null) {
            dto.setImmaginiIds(auto.getImmagini().stream().map(ImmagineAutomobile::getId).collect(Collectors.toList()));
        }

        return dto;
    }
}

