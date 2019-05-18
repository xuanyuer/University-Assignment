package com.csci213.a1.factory;

import com.csci213.a1.dao.AdminDAO;
import com.csci213.a1.dao.PlayerDAO;
import com.csci213.a1.common.Utility;
import com.csci213.a1.controller.Controller;
import com.csci213.a1.repository.AdminRepositoryImpl;
import com.csci213.a1.exception.BeanCreationException;
import com.csci213.a1.repository.Repository;
import com.csci213.a1.repository.PlayerRepositoryImpl;
import com.csci213.a1.service.Service;
import com.csci213.a1.service.ServiceImpl;
import com.csci213.a1.view.View;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BeanFactory
{
    public static AdminDAO getAdminDao (final String fileName) throws BeanCreationException
    {
        try
        {
            String password = Utility.getHash ("password");

            File file = new File (fileName);

            if (file.exists ())
            {
                Scanner iFile = new Scanner (file);
                password = iFile.nextLine ();
            }
            else
            {
                PrintWriter writer = new PrintWriter (fileName);
                writer.println (password);
                writer.close ();
            }

            return new AdminDAO (password);
        }
        catch (IOException ioe)
        {
            throw new BeanCreationException (AdminDAO.class.getName ());
        }
    }

    public static Map <String, PlayerDAO> getPlayerMap (final String fileName) throws BeanCreationException
    {
        Map <String, PlayerDAO> playerMap = new HashMap <String, PlayerDAO> ();

        try
        {
            File file = new File (fileName);

            if (file.exists ())
            {
                Scanner iFile = new Scanner (file);

                while (iFile.hasNextLine ())
                {
                    String line = iFile.nextLine ();
                    String[] data = line.split ("\\|");

                    PlayerDAO playerDAO = new PlayerDAO ();

                    playerDAO.setLogin (data[0]);
                    playerDAO.setPassword (data[1]);
                    playerDAO.setChips (Long.parseLong (data[2]));
                    playerDAO.setName (data[3]);
                    playerDAO.setEmail (data[4]);
                    playerDAO.setBirthdDate (LocalDate.parse (data[5]));

                    playerMap.put (data[0], playerDAO);
                }
            }

            return playerMap;
        }
        catch (IOException ioe)
        {
            throw new BeanCreationException (playerMap.getClass ().getName ());
        }
    }

    public static Repository getAdminRepository (final AdminDAO adminDAO)
    {
        return new AdminRepositoryImpl (adminDAO);
    }

    public static Repository getPlayerRepository (final Map <String, PlayerDAO> playerMap)
    {
        return new PlayerRepositoryImpl (playerMap);
    }

    public static Service getService (final Repository adminRepository, final Repository playerRepository)
    {
        return new ServiceImpl (adminRepository, playerRepository);
    }

    public static View getView ()
    {
        return new View ();
    }

    public static Controller getController (final View view, final Service service)
    {
        return new Controller (view, service);
    }
}