package com.tictactoe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PlaceAlreadyOccupiedException extends RuntimeException
{

    static final long serialVersionUID = 4744023849526803914L;


    public PlaceAlreadyOccupiedException(String message)
    {
        super(message);
    }

}
