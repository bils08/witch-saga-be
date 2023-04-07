package com.example.witchsagabe.service;

import com.example.witchsagabe.model.ResponseData;
import com.example.witchsagabe.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SolverService {

    public ResponseData findAverageKilledPerson(Person personA, Person personB) {
        ResponseData responseData = new ResponseData();
        if (personA.getAgeOfDeath() < 0 || personA.getYearOfDeath() < 0 || personB.getAgeOfDeath() < 0
                || personB.getYearOfDeath() < 0) {
            responseData.setStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Person A and Person B Year of Death and Age of Death must be positive number!");
            responseData.setResult(-1);
            return responseData;
        } else if (personA.getAgeOfDeath() > personA.getYearOfDeath() ||
                personB.getAgeOfDeath() > personB.getYearOfDeath()) {
            responseData.setStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Person A or Person B Year of Death must be greater than Age of Death!");
            responseData.setResult(-1);
            return responseData;
        } else {
            float result = calculateAverageKilledPerson(personA.getYearOfDeath(), personB.getYearOfDeath(),
                    personA.getAgeOfDeath(), personB.getAgeOfDeath());
            responseData.setStatus(HttpStatus.OK);
            responseData.setMessage("Success");
            responseData.setResult(result);
            return responseData;
        }
    }

    public float calculateAverageKilledPerson(int yearDeathA, int yearDeathB, int ageDeathA, int ageDeathB) {
        int yearA = yearDeathA - ageDeathA;
        int yearB = yearDeathB - ageDeathB;
        float peopleKilledYearA = findPersonKilledByYear(yearA);
        float peopleKilledYearB = findPersonKilledByYear(yearB);
        return (float) ((peopleKilledYearA + peopleKilledYearB) / 2);
    }

    public int findPersonKilledByYear(int year) {
        if (year <= 1) {
            return year;
        } else {
            return findPersonKilledByYear(year - 1) + findPersonKilledByYear(year - 2) + 1;
        }
    }
}
