package it.finalproject.auto_collection.repo;


import it.finalproject.auto_collection.model.ImmagineAutomobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmagineAutomobileRepository extends JpaRepository<ImmagineAutomobile, Long> {
}
