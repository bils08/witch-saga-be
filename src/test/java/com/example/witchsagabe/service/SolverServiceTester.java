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

    @InjectMocks
    Person personMockA;

    @InjectMocks
    Person personMockB;

    @InjectMocks
    ResponseData responseDataMock;

    @Before
    public void setUp() {

    }

    @Test
    public void testSuccess() throws Exception {
        //Tes jika semua input benar
        Person personA = new Person();
        Person personB = new Person();
        ResponseData responseData = new ResponseData();

        lenient().when(solverService.findAverageKilledPerson(personA, personB)).thenReturn(responseData);

        personMockA.setAgeOfDeath(10);
        personMockA.setYearOfDeath(12);

        personMockB.setAgeOfDeath(13);
        personMockB.setYearOfDeath(17);

        responseDataMock.setStatus(HttpStatus.OK);
        responseDataMock.setMessage("Success");
        responseDataMock.setResult((float)4.5);

        Assertions.assertEquals(responseDataMock, solverService.findAverageKilledPerson(personMockA, personMockB), "find result");
    }

    @Test
    public void testFailedMinusNumber() {
        //Tes jika input kurang dari 0
        Person personA = new Person();
        Person personB = new Person();
        ResponseData responseData = new ResponseData();

        lenient().when(solverService.findAverageKilledPerson(personA, personB)).thenReturn(responseData);

        personMockA.setAgeOfDeath(-10);
        personMockA.setYearOfDeath(12);

        personMockB.setAgeOfDeath(13);
        personMockB.setYearOfDeath(17);

        responseDataMock.setStatus(HttpStatus.BAD_REQUEST);
        responseDataMock.setMessage("Person A and Person B Year of Death and Age of Death must be positive number!");
        responseDataMock.setResult((float)-1);

        Assertions.assertEquals(responseDataMock, solverService.findAverageKilledPerson(personMockA, personMockB), "find result");

    }

    @Test
    public void testFailedGreaterThan() {
        //Tes jika input age of death lebih besar dari year of death
        Person personA = new Person();
        Person personB = new Person();
        ResponseData responseData = new ResponseData();

        when(solverService.findAverageKilledPerson(personA, personB)).thenReturn(responseData);

        personMockA.setAgeOfDeath(12);
        personMockA.setYearOfDeath(10);

        personMockB.setAgeOfDeath(17);
        personMockB.setYearOfDeath(13);

        responseDataMock.setStatus(HttpStatus.BAD_REQUEST);
        responseDataMock.setMessage("Person A or Person B Year of Death must be greater than Age of Death!");
        responseDataMock.setResult((float)-1);

        Assertions.assertEquals(responseDataMock, solverService.findAverageKilledPerson(personMockA, personMockB), "find result");
    }
}
