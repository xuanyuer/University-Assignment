package com.csci213.a1.exception;

public class LogoutException extends Exception
{
    public LogoutException (String message)
    {
        super ("Error on logout : " + message);
    }
}