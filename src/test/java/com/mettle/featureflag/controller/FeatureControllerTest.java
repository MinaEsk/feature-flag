package com.mettle.featureflag.controller;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.service.FeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FeatureControllerTest {

    private static final String URL="/features";
    private static final long ID=1;
    private static final String NAME="Feature One";

    @MockBean
    private FeatureService featureService;

    @Autowired
    private TestRestTemplate restTemplate;

    private final FeatureDto FEATURE_DTO = new FeatureDto(ID,NAME);

    /**
     * HTTP GET /features
     */
    @Test
    public void listFeatures(){
        when(featureService.listFeatures()).thenReturn(List.of(FEATURE_DTO, FEATURE_DTO, FEATURE_DTO, FEATURE_DTO));
        ResponseEntity<List<FeatureDto>> response = restTemplate.exchange(URL, HttpMethod.GET,null,
                new ParameterizedTypeReference<>() {});
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(4,response.getBody().size());
    }

    /**
     * HTTP GET /features/{id}
     */
    @Test
    public void getFeature(){

        when(featureService.getFeatureById(ID)).thenReturn(FEATURE_DTO);
        ResponseEntity<FeatureDto> response =
                restTemplate.getForEntity(URL + "/" + ID, FeatureDto.class);

        verify(featureService).getFeatureById(ID);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());

    }

    /**
     * HTTP POST /features
     */
    @Test
    public void createFeature(){
        when(featureService.createFeature(FEATURE_DTO)).thenReturn(FEATURE_DTO);
        ResponseEntity<FeatureDto> response =
                restTemplate.postForEntity(URL, FEATURE_DTO,FeatureDto.class);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());

    }
}
