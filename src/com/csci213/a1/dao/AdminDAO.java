package com.csci213.a1.dao;

public class AdminDAO
{
    private String password;

    public AdminDAO (String password)
    {
        this.password = password;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString ()
    {
        return this.password;
    }
}