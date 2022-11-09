package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.service.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

    Logger log = LoggerFactory.getLogger(FeatureController.class);
    @Autowired
    private FeatureService featureService;

    @PostMapping("") // this maps to -> '/products/{productId}/features'
    public String createFeature(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        Feature feature = featureService.createFeature(productId, user);

        return "redirect:/products/" + productId + "/features/" + feature.getId();

    }

    @GetMapping("{featureId}")
    public String getFeature(ModelMap model,
                             @PathVariable Long productId, @PathVariable Long featureId) {
        Optional<Feature> featureOpt = featureService.findById(featureId);
        if (featureOpt.isPresent()) {
            model.put("feature", featureOpt.get());

        }
        // TODO: handle the situation where we can't find a feature by the featureId

        return "feature";
    }

    @PostMapping("{featureId}")
    public String updateFeature(@AuthenticationPrincipal User user,Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
        feature.setUser(user);
        feature = featureService.save(feature);
        String encodedProductName;

        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("Unable to encode the URL string: " + feature.getProduct().getName() + ", redirecting to dashboard instead of the intended product user view page.");
            return "redirect:/dashboard";
        }

        return "redirect:/p/" + encodedProductName;
    }
}