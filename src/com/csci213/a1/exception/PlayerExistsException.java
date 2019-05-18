package com.csci213.a1.exception;

import com.csci213.a1.dao.PlayerDAO;

public class PlayerExistsException extends Exception
{
    public PlayerExistsException (final PlayerDAO playerDAO)
    {
        super ("Player already exists! : " + playerDAO.getLogin ());
    }
}