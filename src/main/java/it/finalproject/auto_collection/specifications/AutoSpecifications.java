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

    public static Specification<Auto> hasModello(String modello){
        return (root,query,criteriaBuilder)-> modello==null ? null : criteriaBuilder.like(root.get("modello"),"%" + modello + "%");
    }

    public static Specification<Auto> hasAnno(Integer annoMin){
        return (root,query,criteriaBuilder)-> annoMin==null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("anno"),annoMin);
    }

    public static Specification<Auto> hasPrezzoMin(BigDecimal prezzoMin){
        return (root,query,criteriaBuilder)-> prezzoMin==null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("prezzo"),prezzoMin);
    }

    public static Specification<Auto> hasPrezzoMax(BigDecimal prezzoMax){
        return (root,query,criteriaBuilder)-> prezzoMax==null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("prezzo"),prezzoMax);
    }


}
