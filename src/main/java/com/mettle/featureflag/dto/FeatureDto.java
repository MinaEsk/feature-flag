package com.mettle.featureflag.dto;

import com.mettle.featureflag.model.Feature;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Feature entity
 */
@Data
@NoArgsConstructor
public class FeatureDto {
    private long id;
    private String name;

    public FeatureDto(Feature feature) {
        this.id = feature.getId();
        this.name = feature.getName();
    }

    public FeatureDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
