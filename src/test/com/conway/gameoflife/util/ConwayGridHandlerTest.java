package com.conway.gameoflife.util;

import static com.conway.gameoflife.ConwayGameTestUtil.EXPECTED_MATRIX_AFTER_1_GEN;
import static com.conway.gameoflife.ConwayGameTestUtil.TEST_MATRIX;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.conway.gameoflife.ConwayGameTestUtil;

@SpringBootTest
public class ConwayGridHandlerTest {

    @Autowired
    private ConwayGridHandler gridHandler;

    @Test
    public void countLiveNeighborsForCell() {
        final var result = gridHandler.countLiveNeighborsForCell(TEST_MATRIX, 0, 0);
        assertEquals(1, result);
    }

    @Test
    public void countLiveNeighborsForCell_2() {
        final var result = gridHandler.countLiveNeighborsForCell(TEST_MATRIX, 4, 1);
        assertEquals(2, result);
    }

    @Test
    public void countLiveNeighborsForCell_3() {
        final var result = gridHandler.countLiveNeighborsForCell(TEST_MATRIX, 8, 4);
        assertEquals(3, result);
    }

    @Test
    public void nextGeneration() {
        final var result = gridHandler.nextGeneration(TEST_MATRIX);
        final var resultStr = ConwayGameTestUtil.matrixToString(result);
        final var expectedStr = ConwayGameTestUtil.matrixToString(EXPECTED_MATRIX_AFTER_1_GEN);

        System.out.println(resultStr);
        assertEquals(expectedStr, resultStr);
    }
}
