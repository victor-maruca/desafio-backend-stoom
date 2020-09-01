package com.example.desafio.Helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Mandatory fields are missing")
public class BadRequestException extends Exception { }
