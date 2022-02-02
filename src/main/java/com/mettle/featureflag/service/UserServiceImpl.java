package com.mettle.featureflag.service;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.model.Feature;
import com.mettle.featureflag.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Lists all user-enabled features.
     *
     * @param id User Identifier
     * @return  list of features enabled for user
     * @throws NoSuchElementException if User is not found.
     */
    @Override
    public List<FeatureDto> listUserFeatures(long id) {
        List<Feature> features;
        try{
            features = userRepository.getById(id).getFeatures();
        }catch (EntityNotFoundException e){
            throw new NoSuchElementException("User does not exist with id: " + id);
        }
        return features.stream().map(FeatureDto::new).collect(Collectors.toList());
    }

}