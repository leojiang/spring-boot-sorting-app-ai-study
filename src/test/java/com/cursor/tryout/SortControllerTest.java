package com.cursor.tryout;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SortControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sort API is running!"));
    }

    @Test
    void testSortGetEndpoint() throws Exception {
        mockMvc.perform(get("/api/sort")
                .param("numbers", "64,34,25,12,22,11,90,88,76,45"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.original").isArray())
                .andExpect(jsonPath("$.sorted").isArray())
                .andExpect(jsonPath("$.sorted[0]").value(11))
                .andExpect(jsonPath("$.sorted[9]").value(90));
    }

    @Test
    void testSortPostEndpoint() throws Exception {
        String json = "{\"numbers\": [64, 34, 25, 12, 22, 11, 90, 88, 76, 45]}";
        mockMvc.perform(post("/api/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.original").isArray())
                .andExpect(jsonPath("$.sorted").isArray())
                .andExpect(jsonPath("$.sorted[0]").value(11))
                .andExpect(jsonPath("$.sorted[9]").value(90));
    }

    @Test
    void testSortGetEndpointWithInvalidInput() throws Exception {
        mockMvc.perform(get("/api/sort")
                .param("numbers", "a,b,c"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSortPostEndpointWithInvalidInput() throws Exception {
        String json = "{\"numbers\": [\"a\", \"b\", \"c\"]}";
        mockMvc.perform(post("/api/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}
