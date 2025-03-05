package it.finalproject.auto_collection.repo;


import it.finalproject.auto_collection.model.Nazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NazioneRepository extends JpaRepository<Nazione, Long> {
}
