package com.csci213.a1.service;

import com.csci213.a1.dao.AdminDAO;
import com.csci213.a1.dao.PlayerDAO;
import com.csci213.a1.common.Utility;
import com.csci213.a1.exception.LogoutException;
import com.csci213.a1.exception.PlayerExistsException;
import com.csci213.a1.exception.PlayerExportException;
import com.csci213.a1.exception.PlayerNotFoundException;
import com.csci213.a1.repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServiceImpl implements Service
{
    private Repository adminRepository;
    private Repository playerRepository;

    public ServiceImpl (final Repository adminRepository, final Repository playerRepository)
    {
        this.adminRepository = adminRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public AdminDAO getAdmin ()
    {
        return (AdminDAO) adminRepository.findById (null);
    }

    @Override
    public AdminDAO saveAdmin (final AdminDAO adminDAO)
    {
        return (AdminDAO) adminRepository.save (adminDAO);
    }

    @Override
    public PlayerDAO createPlayer (final PlayerDAO playerDAO) throws PlayerExistsException
    {
        try
        {
            findByLogin (playerDAO.getLogin ());
        }
        catch (PlayerNotFoundException pnfe)
        {
            return (PlayerDAO) playerRepository.save (playerDAO);
        }

        throw new PlayerExistsException (playerDAO);
    }

    @Override
    public PlayerDAO deletePlayer (final String login) throws PlayerNotFoundException
    {
        final PlayerDAO playerDAO = findByLogin (login);
        return (PlayerDAO) playerRepository.delete (playerDAO);
    }

    @Override
    public PlayerDAO updatePlayer (final PlayerDAO playerDAO) throws PlayerNotFoundException
    {
        findByLogin (playerDAO.getLogin ());
        return (PlayerDAO) playerRepository.save (playerDAO);
    }

    @Override
    public File exportPlayers () throws PlayerExportException
    {
        try
        {
            final File file = new File ("playersData.dat");
            final PrintWriter writer = new PrintWriter (file);

            List <PlayerDAO> playerList = playerRepository.findAll ();

            int count = 1;
            for (final PlayerDAO playerDAO : playerList)
            {
                writer.println ("Player " + count);
                writer.println ("Name          : " + playerDAO.getName ());
                writer.println ("Email         : " + playerDAO.getEmail ());
                writer.println ("Birth Date    : " + playerDAO.getBirthDate ());
                writer.println ("Login ID      : " + playerDAO.getLogin ());
                writer.println ("Balance Chips : " + playerDAO.getChips ());
                writer.println ();
                ++ count;
            }

            writer.close ();
            return file;
        }
        catch (FileNotFoundException fnfe)
        {
            throw new PlayerExportException (fnfe.getMessage ());
        }
    }

    @Override
    public PlayerDAO findByLogin (final String login) throws PlayerNotFoundException
    {
        PlayerDAO playerDAO = (PlayerDAO) playerRepository.findById (login);
        if (playerDAO == null) throw new PlayerNotFoundException (login);
        return playerDAO;
    }

    @Override
    public boolean login (final String password)
    {
        final String hashedPassword = Utility.getHash (password);
        return getAdmin ().getPassword ().equals (hashedPassword);
    }

    @Override
    public void logout () throws LogoutException
    {
        try
        {
            final File playersFile = new File ("players.dat");
            final PrintWriter playersWriter = new PrintWriter (playersFile);

            final List <PlayerDAO> playerDAOList = playerRepository.findAll ();
            playerDAOList.forEach (playersWriter::println);

            playersWriter.close ();

            final File adminFile = new File ("admin.dat");
            final PrintWriter adminWriter = new PrintWriter (adminFile);

            final AdminDAO adminDAO = getAdmin ();
            adminWriter.println (adminDAO);

            adminWriter.close ();
        }
        catch (IOException ioe)
        {
            throw new LogoutException (ioe.getMessage ());
        }
    }
}