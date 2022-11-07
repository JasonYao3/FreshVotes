package com.freshvotes.web;

import com.freshvotes.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products/{productId}/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("/") // this maps to -> /products/{productId}/features/
    public String createFeature(@PathVariable Long productId) {
        featureService.createFeature(productId);

        return "feature";
    }
}
