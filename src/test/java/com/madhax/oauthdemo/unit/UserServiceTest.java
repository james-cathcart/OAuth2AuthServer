package com.madhax.oauthdemo.unit;

import com.madhax.oauthdemo.entity.User;
import com.madhax.oauthdemo.repository.UserRepository;
import com.madhax.oauthdemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    public static final long USER_1_ID = 1L;
    public static final String USER_1_EMAIL = "user1@fakemail.com";
    public static final String USER_1_LAST_NAME = "user1_last_name";
    public static final long USER_2_ID = 2L;
    public static final String USER_2_EMAIL = "user2@fakemail.com";
    public static final String USER_2_LAST_NAME = "user2_last_name";
    public static final long USER_3_ID = 3L;
    public static final String USER_3_EMAIL = "user3@fakemail.com";
    public static final String USER_3_LAST_NAME = "user3_last_name";
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user1;
    User user2;
    User user3;

    List<User> userList;

    @BeforeEach
    public void setUp() {

        user1 = new User();
        user1.setId(USER_1_ID);
        user1.setEmail(USER_1_EMAIL);
        user1.setLastName(USER_1_LAST_NAME);

        user2 = new User();
        user2.setId(USER_2_ID);
        user2.setEmail(USER_2_EMAIL);
        user2.setLastName(USER_2_LAST_NAME);

        user3 = new User();
        user3.setId(USER_3_ID);
        user3.setEmail(USER_3_EMAIL);
        user3.setLastName(USER_3_LAST_NAME);

        userList = Arrays.asList(
                user1,
                user2,
                user3
        );

    }

    @Test
    public void shouldGetallUsers() {

        when(userRepository.findAll()).thenReturn(userList);

        List<User> returnedList = userService.getAll();

        assertEquals(3, returnedList.size());
        assertEquals(USER_1_ID, returnedList.get(0).getId().longValue());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldGetUserById() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user1));

        User returnedUser = userService.getById(1L);

        assertEquals(USER_1_ID, user1.getId().longValue());
        assertEquals(USER_1_EMAIL, user1.getEmail());

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldGetUserByEmail() {

        when(userRepository.findByEmail(anyString())).thenReturn(user1);

        User returnedUser = userService.getByEmail(USER_1_EMAIL);

        assertEquals(USER_1_ID, returnedUser.getId().longValue());
        assertEquals(USER_1_EMAIL, returnedUser.getEmail());

        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void shouldSaveUser() {

        when(userRepository.save(any(User.class))).thenReturn(user1);

        User savedUser = userService.save(user1);

        assertEquals(USER_1_ID, savedUser.getId().longValue());
        assertEquals(USER_1_EMAIL, savedUser.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void shouldDeleteUserById() {

        userService.deleteById(1L);

        verify(userRepository, times(1)).deleteById(anyLong());
    }
}
