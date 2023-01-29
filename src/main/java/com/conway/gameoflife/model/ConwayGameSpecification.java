package com.conway.gameoflife.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ConwayGameSpecification {

    private int generations;

    private int[][] stateGrid;


}
