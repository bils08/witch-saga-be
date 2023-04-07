package com.example.witchsagabe.controller;

import com.example.witchsagabe.model.ResponseData;
import com.example.witchsagabe.service.SolverService;
import com.example.witchsagabe.model.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/witch")
@RestController
public class api {

    @Autowired
    private final SolverService solverService;

    public api(SolverService solverService) {
        this.solverService = solverService;
    }

    @GetMapping(path = "/solve")
    public ResponseData solve(@RequestBody RequestData request) {
        return solverService.findAverageKilledPerson(request.getPersonA(), request.getPersonB());
    }

}
