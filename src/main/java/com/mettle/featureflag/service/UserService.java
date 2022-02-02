package com.mettle.featureflag.service;

import com.mettle.featureflag.dto.FeatureDto;

import java.util.List;

public interface UserService {

    List<FeatureDto> listUserFeatures(long userId);
}
