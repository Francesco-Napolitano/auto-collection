package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.repo.AutoRepository;
import it.finalproject.auto_collection.service.AutoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public List<Auto> getALlAutos(){
        return autoService.getAllAuto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable Long id){
        return autoService.getAutoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
