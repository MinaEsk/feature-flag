package com.mettle.featureflag.service;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.model.Feature;

import java.util.List;

public interface FeatureService {

    FeatureDto createFeature(FeatureDto feature);
    FeatureDto getFeatureById(long id);
    List<FeatureDto> listFeatures();
}
