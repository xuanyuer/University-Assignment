package com.csci213.a1.exception;

public class BeanCreationException extends Exception
{
    public BeanCreationException (final String bean)
    {
        super ("Error creating bean " + bean);
    }
}
