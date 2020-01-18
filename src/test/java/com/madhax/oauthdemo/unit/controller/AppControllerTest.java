package com.madhax.oauthdemo.unit.controller;

import com.madhax.oauthdemo.constants.AppConstants;
import com.madhax.oauthdemo.controller.AppController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AppControllerTest {

    @Autowired
    AppController appController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();

    }

    @Test
    public void shouldGetResponseFromReadCheckEndpoint() throws Exception {

        MvcResult result = mockMvc.perform(
                get(AppConstants.READ_CHECK_URI)
        )
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result
                .getResponse()
                .getContentAsString();

        assertEquals(AppController.SUCCESSFULLY_ACCESSED_READ_ENDPOINT, resultString);
    }

    @Test
    public void shouldGetResponseFromWriteCheckEndpoint() throws Exception {

        MvcResult result = mockMvc.perform(
                get(AppConstants.WRITE_CHECK_URI)
        )
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();

        assertEquals(AppController.SUCCESSFULLY_ACCESSED_WRITE_ENDPOINT, resultString);

    }

    @Test
    public void shouldGetResponseFromDeleteCheckEndpoint() throws Exception {

        MvcResult result = mockMvc.perform(
                get(AppConstants.DELETE_CHECK_URI)
        )
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();

        assertEquals(AppController.SUCCESSFULLY_ACCESSED_DELETE_ENDPOINT, resultString);
    }
}
