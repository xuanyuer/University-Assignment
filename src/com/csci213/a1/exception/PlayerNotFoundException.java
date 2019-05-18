package com.csci213.a1.exception;

public class PlayerNotFoundException extends Exception
{
    public PlayerNotFoundException (String login)
    {
        super ("Player not found! : " + login);
    }
}
