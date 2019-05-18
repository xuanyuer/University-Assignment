package com.csci213.a1.exception;

public class PlayerExportException extends Exception
{
    public PlayerExportException (String message)
    {
        super ("Error exporting players : " + message);
    }
}
