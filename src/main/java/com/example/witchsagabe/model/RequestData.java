package com.example.witchsagabe.model;

import lombok.Data;

//Model RequestData sebagai template data json untuk mengirim data dari client
@Data
public class RequestData {

    private Person personA;
    private Person personB;
}
