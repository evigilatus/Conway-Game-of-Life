package com.conway.gameoflife.service;

import static com.conway.gameoflife.ConwayGameTestUtil.EXPECTED_MATRIX_AFTER_N_GEN;
import static com.conway.gameoflife.ConwayGameTestUtil.TEST_MATRIX;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.conway.gameoflife.ConwayGameTestUtil;

@SpringBootTest
public class ConwayGameSimulatorTest {

    @Autowired
    private ConwayGameSimulator simulator;

    @Test
    public void simulateConwayGame() {
        final var result = simulator.simulateConwayGame(1500, TEST_MATRIX);
        final var resultStr = ConwayGameTestUtil.matrixToString(result);
        final var expectedStr = ConwayGameTestUtil.matrixToString(EXPECTED_MATRIX_AFTER_N_GEN);

        assertEquals(expectedStr, resultStr);
    }

}

