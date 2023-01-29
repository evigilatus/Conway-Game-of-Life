package com.conway.gameoflife.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.conway.gameoflife.model.ConwayGameSpecification;
import com.conway.gameoflife.service.ConwayGameSimulator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConwayGameController {

    private final ConwayGameSimulator simulatorService;

    @PostMapping("/conway/simulations")
    public ResponseEntity<ConwayGameSpecification> simulateConwayGame(
            @RequestBody ConwayGameSpecification conwayGameSimulatorInput) {
        final var conwayGameOutput = simulatorService.simulateConwayGame(
                conwayGameSimulatorInput.getGenerations(),
                conwayGameSimulatorInput.getStateGrid());
        final var conwayGameSpecification =
                ConwayGameSpecification.builder()
                        .generations(conwayGameSimulatorInput.getGenerations())
                        .stateGrid(conwayGameOutput)
                        .build();

        return new ResponseEntity<>(conwayGameSpecification, HttpStatus.OK);
    }

}
