package com.madhax.oauthdemo.integ;

import com.madhax.oauthdemo.entity.Authority;
import com.madhax.oauthdemo.entity.User;
import com.madhax.oauthdemo.pojo.OauthTokenResponse;
import com.madhax.oauthdemo.utils.JsonExpert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AppControllerPermissionsTest {

    public static final long READ_USER_ID = 1L;
    public static final String READ_USER_EMAIL = "user1@fakemail.com";
    public static final String READ_USER_LAST_NAME = "user1_last_name";
    public static final long WRITE_USER_ID = 2L;
    public static final String WRITE_USER_EMAIL = "user2@fakemail.com";
    public static final String WRITE_USER_LAST_NAME = "user2_last_name";
    public static final long USER_3_ID = 3L;
    public static final String USER_3_EMAIL = "user3@fakemail.com";
    public static final String USER_3_LAST_NAME = "user3_last_name";
    public static final String READ_AUTHORITY_NAME = "READ";

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    Authority readAuthority;
    Authority writeAuthority;
    Authority deleteAuthority;

    User readUser;
    User writeUser;
    User deleteUser;

    List<User> userList;

    @BeforeEach
    void setUp() {

        readAuthority = new Authority();
        readAuthority.setId(1L);
        readAuthority.setName(READ_AUTHORITY_NAME);

        writeAuthority = new Authority();


        deleteAuthority = new Authority();

        readUser = new User();
        readUser.setId(READ_USER_ID);
        readUser.setEmail(READ_USER_EMAIL);
        readUser.setLastName(READ_USER_LAST_NAME);

        writeUser = new User();
        writeUser.setId(WRITE_USER_ID);
        writeUser.setEmail(WRITE_USER_EMAIL);
        writeUser.setLastName(WRITE_USER_LAST_NAME);

        deleteUser = new User();
        deleteUser.setId(USER_3_ID);
        deleteUser.setEmail(USER_3_EMAIL);
        deleteUser.setLastName(USER_3_LAST_NAME);

        userList = Arrays.asList(
                readUser,
                writeUser,
                deleteUser
        );

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

    }

    @Test
    void readCheck() throws Exception {

        String token = generateTokenForUser(readUser);

//        mockMvc.perform(
//                get(AppConstants.READ_CHECK_URI)
//                .params()
//        )
//                .andExpect(status().isOk());
        fail();
    }

    @Test
    void writeCheck() {
        fail();
    }

    @Test
    void deleteCheck() {
        fail();
    }

    private String generateTokenForUser(User user) throws Exception {

        MultiValueMap<String, String> loginForm = new LinkedMultiValueMap<>();

        loginForm.add("grant_type", "password");
        loginForm.add("scope", "webclient");
        loginForm.add("username", user.getEmail());
        loginForm.add("password", user.getPassword());

        ResultActions result = mockMvc.perform(
                post("http://localhost:8901/oauth/token")
                        .params(loginForm)
                        .with(httpBasic("defaultApp","defaultAppPassword"))
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        OauthTokenResponse oauthTokenResponse = JsonExpert.deserialize(resultString, OauthTokenResponse.class);
        String accessToken = oauthTokenResponse.getAccessToken();

        assertNotNull(accessToken);
        assertFalse(accessToken.isEmpty());

        return accessToken;
    }
}
