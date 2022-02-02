package com.mettle.featureflag.service;

import com.mettle.featureflag.dto.FeatureDto;
import com.mettle.featureflag.model.Feature;
import com.mettle.featureflag.model.User;
import com.mettle.featureflag.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final long ID=1;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository);

    @Mock
    private User user;

    @Mock
    private Feature feature;

    /**
     * verify return values
     */
    @Test
    public void listUserFeatures() {
        when(userRepository.getById(ID)).thenReturn(user);
        when(user.getFeatures()).thenReturn(List.of(feature));
        assertEquals(List.of(new FeatureDto(feature)),userService.listUserFeatures(ID));

    }
}
