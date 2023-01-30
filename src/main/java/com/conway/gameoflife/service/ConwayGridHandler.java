package com.conway.gameoflife.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles the transformations required to simulate the Conway Game of Life.
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConwayGridHandler {

    /**
     * Calculates the next generation grid based of the neighbours for each cell.
     *
     * @param grid the initial state of the board
     * @return grid after 1 generation has passed
     */
    public int[][] nextGeneration(int[][] grid) {
        final var gridHeight = grid.length;
        final var gridWidth = grid[0].length;
        final var nextGenerationGrid = new int[gridHeight][gridWidth];

        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                final var aliveNeighbours = countLiveNeighborsForCell(grid, x, y);

                if (grid[y][x] == 1 && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                    // Alive cell with less than 2 or more than 3 neighbours dies.
                    log.debug("Cell '{}:{}' dies.", x, y);
                    nextGenerationGrid[y][x] = 0;
                } else if (grid[y][x] == 0 && aliveNeighbours == 3) {
                    // Dead cell with exactly 3 neighbours becomes alive.
                    log.debug("Cell '{}:{}' is resurrected.", x, y);
                    nextGenerationGrid[y][x] = 1;
                } else {
                    // The cell stays the same.
                    log.debug("Cell '{}:{}' stays the same.", x, y);
                    nextGenerationGrid[y][x] = grid[y][x];
                }
            }
        }

        return nextGenerationGrid;
    }

    /**
     * @return neighbours count for a given cell with coordinates x:y in the board.
     */
    public int countLiveNeighborsForCell(int[][] grid, int x_coordinate, int y_coordinate) {
        final var gridHeight = grid.length;
        final var gridWidth = grid[0].length;

        var aliveNeighbours = 0;
        for (int y = Math.max(y_coordinate - 1, 0); y <= Math.min(y_coordinate + 1, gridHeight - 1); y++) {
            for (int x = Math.max(x_coordinate - 1, 0); x <= Math.min(x_coordinate + 1, gridWidth - 1); x++) {
                aliveNeighbours += grid[y][x];
            }
        }
        aliveNeighbours -= grid[y_coordinate][x_coordinate];
        log.debug("Cell '{}:{}' with value '{}' has '{}' alive neighbours", x_coordinate, y_coordinate,
                  grid[y_coordinate][x_coordinate], aliveNeighbours);
        return aliveNeighbours;
    }
}
