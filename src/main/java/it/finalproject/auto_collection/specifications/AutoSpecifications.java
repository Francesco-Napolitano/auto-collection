package it.finalproject.auto_collection.specifications;

import it.finalproject.auto_collection.model.Auto;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class AutoSpecifications {

    public static Specification<Auto> hasBrand(Long brandId){
        return (root,query,criteriaBuilder) -> brandId == null ? null : criteriaBuilder.equal(root.get("brand").get("id"),brandId);
    }

    public static Specification <Auto> hasNazione(Long nazioneId){
        return (root,query,criteriaBuilder) -> nazioneId == null ? null : criteriaBuilder.equal(root.get("nazione").get("id"),nazioneId);
    }

    public static Specification<Auto> hasAlimentazione(String alimentazione){
        return (root,query,criteriaBuilder)-> alimentazione==null ? null : criteriaBuilder.equal(root.get("alimentazione"),alimentazione);
    }

    public static Specification<Auto> hasModello(String modello){
        return (root,query,criteriaBuilder)-> modello==null ? null : criteriaBuilder.like(root.get("modello"),"%" + modello + "%");
    }

    public static Specification<Auto> hasAnno(Integer annoMin){
        return (root,query,criteriaBuilder)-> annoMin==null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("anno"),annoMin);
    }

    public static Specification<Auto> hasPrezzo(BigDecimal prezzoMin){
        return (root,query,criteriaBuilder)-> prezzoMin==null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("prezzo"),prezzoMin);
    }

    public static Specification<Auto> hasCarrozzeria(String carrozzeria){
        return (root,query,criteriaBuilder)-> carrozzeria==null ? null : criteriaBuilder.equal(root.get("carrozzeria"),carrozzeria);
    }

    public static Specification<Auto> hasUnitaVendute(String unitaVendute){
        return (root,query,criteriaBuilder)-> unitaVendute==null ? null : criteriaBuilder.equal(root.get("unitaVendute"),unitaVendute);
    }




}
