package com.mettle.featureflag.service;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.model.Feature;
import com.mettle.featureflag.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FeatureServiceImpl implements FeatureService {

    private FeatureRepository featureRepository;

    @Autowired
    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    /**
     * Lists all features
     * @return list of features
     */
    @Override
    public List<FeatureDto> listFeatures() {
        return featureRepository.findAll().stream().map(FeatureDto::new).collect(Collectors.toList());
    }

    /**
     * Gets a feature for a given id
     * @param id feature identifier
     * @return the feature with the given id
     * @throws NoSuchElementException if Feature is not found.
     */
    @Override
    public FeatureDto getFeatureById(long id){
        return new FeatureDto(verifyFeature(id));
    }


    /**
     * Creates a feature for a given FeatureDto
     *
     * @param feature to be created
     * @return the created feature's dto
     */
    @Override
    public FeatureDto createFeature(FeatureDto feature){
        if(featureRepository.existsById(feature.getId())) throw new IllegalArgumentException("Feature already exist with id:" + feature.getId());
        featureRepository.save(new Feature(feature.getId(),feature.getName()));
        return feature;
    }

    /**
     * Verify the Feature exists and returns it given an id.
     *
     * @param id Feature identifier
     * @return the found Feature
     * @throws NoSuchElementException if Feature is not found.
     */
    private Feature verifyFeature(long id) throws NoSuchElementException {
        return featureRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Feature does not exist " + id));
    }
}
