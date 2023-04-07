package com.example.witchsagabe.service;

import com.example.witchsagabe.model.Person;
import com.example.witchsagabe.model.ResponseData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import javax.xml.ws.Response;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolverServiceTester {

    @Spy
    @InjectMocks
    SolverService solverService = new SolverService();

    @Before
    public void setUp() {

    }

    @Test
    void create() throws Exception {
        Person personA = new Person();
        Person personB = new Person();

        personA.setAgeOfDeath(10);
        personA.setYearOfDeath(12);

        personB.setAgeOfDeath(13);
        personB.setYearOfDeath(17);

        Assertions.assertEquals(4.5, solverService.findAverageKilledPerson(personA, personB).getResult(), "find result");

        ResponseData responseData = new ResponseData();
//        responseData.setStatus(HttpStatus.OK);
//        responseData.setMessage("failed");
//        responseData.setResult((float)4.8);

        lenient().when(solverService.findAverageKilledPerson(personA, personB)).thenReturn(responseData);

    }
}
