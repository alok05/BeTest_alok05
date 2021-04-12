package com.example.creditlimit.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponse<T> {

    private String status;

    private T data;
}
