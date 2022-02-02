package com.mettle.featureflag.controller;


import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  Rest Controller for features enabled for a user
 */
@RestController
@RequestMapping(path = "/users/{id}/features")
public class UserFeatureController {

    private UserService userService;

    @Autowired
    public UserFeatureController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Lists the features enabled for a user with a given id
     * @param id identifier for user
     * @return list of features enabled for the user
     */
    @GetMapping({"/",""})
    public List<FeatureDto> listUserFeatures(@PathVariable("id") long id){
        return userService.listUserFeatures(id);
    }
}
