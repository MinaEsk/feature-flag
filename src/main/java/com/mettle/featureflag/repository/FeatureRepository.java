package com.mettle.featureflag.repository;

import com.mettle.featureflag.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
