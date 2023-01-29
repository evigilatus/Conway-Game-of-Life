package com.conway.gameoflife.service;

import org.springframework.stereotype.Service;

import com.conway.gameoflife.util.ConwayGridHandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * The Conway Game of Life shows the development of a matrix of dead and alive cells according to the following rules:
 * <p> 1. Any live cell with fewer than two live neighbors dies, as if by underpopulation.
 * <p> 2. Any live cell with two or three live neighbors lives on to the next generation.
 * <p> 3. Any live cell with more than three live neighbors dies, as if by overpopulation.
 * <p> 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConwayGameSimulator {

    private ConwayGridHandler conwayGridHandler;

    /**
     * Simulates the Conway Game of Life.
     *
     * @param generations number of generations for which to simulate the development
     * @param grid        initial state of the matrix
     * @return state of the matrix after the given number of generations
     */
    public int[][] simulateConwayGame(final int generations, final int[][] grid) {
        int[][] gridAfterGeneration = grid;

        for (int i = 0; i < generations; i++) {
            gridAfterGeneration = conwayGridHandler.nextGeneration(gridAfterGeneration);
        }

        return gridAfterGeneration;
    }
}
