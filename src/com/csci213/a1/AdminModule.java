package com.csci213.a1;

import com.csci213.a1.dao.AdminDAO;
import com.csci213.a1.dao.PlayerDAO;
import com.csci213.a1.controller.Controller;
import com.csci213.a1.factory.BeanFactory;
import com.csci213.a1.exception.BeanCreationException;
import com.csci213.a1.repository.Repository;
import com.csci213.a1.service.Service;
import com.csci213.a1.view.View;

import java.util.Map;

public class AdminModule
{
    public static void main (String [] args)
    {
        final View view = BeanFactory.getView ();

        try
        {
            final AdminDAO adminDAO = BeanFactory.getAdminDao ("getAdminDao.dat");
            final Map <String, PlayerDAO> playerMap = BeanFactory.getPlayerMap ("players.dat");

            final Repository adminRepository = BeanFactory.getAdminRepository (adminDAO);
            final Repository playerRepository = BeanFactory.getPlayerRepository (playerMap);
            final Service service = BeanFactory.getService (adminRepository, playerRepository);
            final Controller controller = BeanFactory.getController (view, service);

            controller.run ();
        }
        catch (BeanCreationException bce)
        {
            view.displayError (bce.getMessage ());
        }
    }
}