package com.mettle.featureflag.controller;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Restful Controller for managing features available for users
 */
@Slf4j
@RestController
@RequestMapping(path = "/features")
public class FeatureController {

    private FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }


    /**
     * Lists all features
     * @return list of features
     */
    @GetMapping({"/",""})
    public List<FeatureDto> listFeatures() {
        return featureService.listFeatures();
    }

    /**
     * Gets a feature for a given id
     * @param id feature identifier
     * @return the feature with the given id
     */
    @GetMapping("/{id}")
    public FeatureDto getFeature(@PathVariable("id") long id) {
        return featureService.getFeatureById(id);
    }

    /**
     * Creates a feature for a given FeatureDto
     * @param feature
     * @return the created feature
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping({"/",""})
    public FeatureDto createFeature(@Validated @RequestBody FeatureDto feature) {
        return featureService.createFeature(feature);
    }


    /**
     * Exception handler if IllegalArgumentException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalArgumentException.class)
    public String return409(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
