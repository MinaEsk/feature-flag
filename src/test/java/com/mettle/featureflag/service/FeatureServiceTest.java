package com.mettle.featureflag.service;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.model.Feature;
import com.mettle.featureflag.repository.FeatureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FeatureServiceTest {

    private static final long ID = 1;
    private static final String NAME="Feature One";
    @Mock
    private FeatureRepository featureRepositoryMock;

    @InjectMocks
    private FeatureService service = new FeatureServiceImpl(featureRepositoryMock);

    @Mock
    private Feature feature;

    /**
     * verify return value
     */
    @Test
    public void getFeatureById(){
        when(featureRepositoryMock.findById(ID)).thenReturn(Optional.of(feature));
        assertEquals(new FeatureDto(feature),service.getFeatureById(ID));
    }

    /**
     * verify return value
     */
    @Test
    public void listFeatures(){
        when(featureRepositoryMock.findAll()).thenReturn(List.of(feature));
        assertEquals(List.of(new FeatureDto(feature)),service.listFeatures());
    }

    /**
     * Verify the invocation of dependencies
     * Capture parameter values.
     * Verify the parameters.
     * verify return value
     */
    @Test
    public void createFeature(){
        when(featureRepositoryMock.existsById(ID)).thenReturn(false);
        when(feature.getId()).thenReturn(ID);
        when(feature.getName()).thenReturn(NAME);
        ArgumentCaptor<Feature> featureArgumentCaptor = ArgumentCaptor.forClass(Feature.class);

        assertEquals(new FeatureDto(feature),service.createFeature(new FeatureDto(feature)));
        verify(featureRepositoryMock).save(featureArgumentCaptor.capture());
        assertEquals(ID,featureArgumentCaptor.getValue().getId());
        assertEquals(NAME,featureArgumentCaptor.getValue().getName());
    }
}
