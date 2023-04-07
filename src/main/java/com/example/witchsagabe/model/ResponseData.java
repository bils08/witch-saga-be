package com.example.witchsagabe.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

//Model ResponseData sebagai template untuk hasil result json ke client
@Data
public class ResponseData {

    private HttpStatus status;
    private String message;
    private float result = 0;
}
