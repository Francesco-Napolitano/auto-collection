package it.finalproject.auto_collection.controller;

import it.finalproject.auto_collection.model.Auto;
import it.finalproject.auto_collection.model.Brand;
import it.finalproject.auto_collection.repo.AutoRepository;
import it.finalproject.auto_collection.repo.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin(origins = "http://localhost:5173")

public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping
    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    @GetMapping("/{brandId}/auto")
    @PreAuthorize("isAuthenticated()")
    public List<Auto> getAutoByBrand(@PathVariable Long brandId) {
        return autoRepository.findByBrandId(brandId);
    }
}
