package com.moaaz.lost.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String message;
    private String url;
    private String date;



}
