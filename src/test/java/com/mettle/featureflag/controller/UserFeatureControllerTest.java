package com.mettle.featureflag.controller;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.service.UserService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserFeatureControllerTest {

    private static final String URL="/users/{id}/features";
    private static final long ID=1;
    private static final String NAME="Feature One";

    @MockBean
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    private final FeatureDto FEATURE_DTO = new FeatureDto(ID,NAME);


    /**
     * HTTP GET /users/{id}/features
     */
    @Test
    public void listUserFeatures(){
        when(userService.listUserFeatures(ID)).thenReturn(List.of(FEATURE_DTO,FEATURE_DTO,FEATURE_DTO));
        ResponseEntity<List<FeatureDto>> response = restTemplate.exchange(URL.replaceAll("\\{id}","1"), HttpMethod.GET,null,
                new ParameterizedTypeReference<>() {});
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(3,response.getBody().size());
    }
}
