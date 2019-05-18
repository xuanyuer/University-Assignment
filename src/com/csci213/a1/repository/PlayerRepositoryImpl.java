package com.csci213.a1.repository;

import com.csci213.a1.dao.PlayerDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerRepositoryImpl implements Repository <PlayerDAO, String>
{
    private Map <String, PlayerDAO> playerMap;

    public PlayerRepositoryImpl (final Map <String, PlayerDAO> playerMap)
    {
        this.playerMap = playerMap;
    }

    public PlayerDAO save (final PlayerDAO playerDAO)
    {
        return playerMap.put (playerDAO.getLogin (), playerDAO);
    }

    public PlayerDAO delete (final PlayerDAO playerDAO)
    {
        return playerMap.remove (playerDAO.getLogin ());
    }

    public PlayerDAO findById (final String loginName)
    {
        return playerMap.get (loginName);
    }

    public List <PlayerDAO> findAll ()
    {
        return new ArrayList <PlayerDAO> (playerMap.values ());
    }
}