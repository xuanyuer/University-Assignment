package com.csci213.a1.service;

import com.csci213.a1.dao.AdminDAO;
import com.csci213.a1.dao.PlayerDAO;
import com.csci213.a1.exception.LogoutException;
import com.csci213.a1.exception.PlayerExistsException;
import com.csci213.a1.exception.PlayerExportException;
import com.csci213.a1.exception.PlayerNotFoundException;

import java.io.File;

public interface Service
{
    AdminDAO getAdmin ();
    AdminDAO saveAdmin (final AdminDAO adminDAO);

    PlayerDAO createPlayer (final PlayerDAO playerDAO) throws PlayerExistsException;
    PlayerDAO deletePlayer (final String login) throws PlayerNotFoundException;
    PlayerDAO updatePlayer (final PlayerDAO playerDAO) throws PlayerNotFoundException;
    File exportPlayers () throws PlayerExportException;
    PlayerDAO findByLogin (final String login) throws PlayerNotFoundException;

    boolean login (final String password);
    void logout () throws LogoutException;
}