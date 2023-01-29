package com.conway.gameoflife.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.conway.gameoflife.ConwayGameTestUtil;
import com.conway.gameoflife.model.ConwayGameSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class ConwayGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void postApiForGameSimulation() {
        // given
        final var inputSpec =
                ConwayGameSpecification.builder()
                        .generations(10)
                        .stateGrid(ConwayGameTestUtil.TEST_MATRIX)
                        .build();
        // when
        final var resultResponse = mockMvc.perform(
                        post("/conway/simulations")
                                .content(objectMapper.writeValueAsString(inputSpec))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        final var resultResponseObj =
                objectMapper.readValue(resultResponse, ConwayGameSpecification.class);
        final var expectedResponseObj =
                ConwayGameSpecification.builder()
                        .generations(10)
                        .stateGrid(ConwayGameTestUtil.EXPECTED_MATRIX_AFTER_N_GEN)
                        .build();
        assertEquals(expectedResponseObj.getGenerations(), resultResponseObj.getGenerations());
        assertArrayEquals(expectedResponseObj.getStateGrid(), resultResponseObj.getStateGrid());

    }
}
