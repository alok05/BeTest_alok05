package com.example.creditlimit.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonInfo {

    private String name;

    private String address;

    private String postCode;

    private String phoneNumber;

    private String creditLimit;

    private String birthDay;
}
