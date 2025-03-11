package it.finalproject.auto_collection.service;

import it.finalproject.auto_collection.model.ImmagineAutomobile;
import it.finalproject.auto_collection.repo.ImmagineAutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmagineAutomobileService {

    @Autowired
    private ImmagineAutomobileRepository repository;

    public List<ImmagineAutomobile> getImmaginiByAutoId(Long autoId){
        return repository.findByAutoId(autoId);
    }
}
