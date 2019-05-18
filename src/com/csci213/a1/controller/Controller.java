package com.csci213.a1.controller;

import com.csci213.a1.dao.AdminDAO;
import com.csci213.a1.dao.PlayerDAO;
import com.csci213.a1.common.Utility;
import com.csci213.a1.exception.LogoutException;
import com.csci213.a1.exception.PlayerExistsException;
import com.csci213.a1.exception.PlayerExportException;
import com.csci213.a1.exception.PlayerNotFoundException;
import com.csci213.a1.service.Service;
import com.csci213.a1.view.View;

import java.io.File;
import java.time.LocalDate;

public class Controller
{
    private Service service;
    private View view;

    public Controller (final View view, final Service service)
    {
        this.view = view;
        this.service = service;
    }

    public void run ()
    {
        if (!login ())
        {
            view.displayError ("Login failed!");
            return;
        }

        int choice = -1;

        while (choice != 0)
        {
            choice = view.mainMenu ();

            switch (choice)
            {
                case 1: createPlayer ();        break;
                case 2: deletePlayer ();        break;
                case 3: editPlayer ();          break;
                case 4: exportPlayers ();       break;
                case 5: changeAdminPassword ();
            }
        }

        logout ();
    }

    private void createPlayer ()
    {
        final String login = view.readString ("Enter login          : ");
        String password = view.readString ("Enter password       : ");
        final String password2 = view.readString ("Enter password again : ");

        if (!password.equals (password2))
        {
            view.displayError ("Passwords do not match!");
            return;
        }

        password = Utility.getHash (password);
        final long chips = view.readLong ("Enter chips          : ");
        final String name = view.readString ("Enter name           : ");
        final String email = view.readEmail ("Enter email          : ");
        final LocalDate birthDate = view.readDate ("Enter birthDate      : ");

        PlayerDAO playerDAO = new PlayerDAO (login, password, chips, name, email, birthDate);

        try
        {
            playerDAO = service.createPlayer (playerDAO);
            view.display ("*** \"" + playerDAO.getLogin () + "\" created successfully! ***");
        }
        catch (PlayerExistsException pee)
        {
            view.displayError (pee.getMessage ());
        }
    }

    private void deletePlayer ()
    {
        final String login = view.readString ("Enter login : ");

        try
        {
            PlayerDAO playerDAO = service.deletePlayer (login);
            view.displayPlayer (playerDAO);
            view.display ("*** Player deleted! ***");
        }
        catch (PlayerNotFoundException pnfe)
        {
            view.displayError (pnfe.getMessage ());
        }
    }

    private void editPlayer ()
    {
        final String login = view.readString ("Enter login : ");

        try
        {
            final PlayerDAO playerDAO = service.findByLogin (login);

            int choice = -1;
            while (choice != 0)
            {
                view.displayPlayer (playerDAO);
                choice = view.editMenu ();

                switch (choice)
                {
                    case 1:
                    {
                        final String newLogin = view.readString ("Enter new login : ");
                        playerDAO.setLogin (newLogin);
                        break;
                    }

                    case 2:
                    {
                        final String password = view.readString ("Enter new password : ");
                        playerDAO.setPassword (Utility.getHash (password));
                        break;
                    }

                    case 3:
                    {
                        final long chips = view.readLong ("Enter new chips : ");
                        playerDAO.setChips (chips);
                        break;
                    }

                    case 4:
                    {
                        final String name = view.readString ("Enter new name : ");
                        playerDAO.setName (name);
                        break;
                    }

                    case 5:
                    {
                        final String email = view.readEmail ("Enter new email : ");
                        playerDAO.setEmail (email);
                        break;
                    }

                    case 6:
                    {
                        final LocalDate birthDate = view.readDate ("Enter new birth date : ");
                        playerDAO.setBirthdDate (birthDate);
                    }
                }
            }

            service.updatePlayer (playerDAO);
            view.display ("*** Returning to main menu ***");
        }
        catch (PlayerNotFoundException pnfe)
        {
            view.displayError (pnfe.getMessage ());
        }
    }

    private void exportPlayers ()
    {
        try
        {
            final File file = service.exportPlayers ();
            view.display ("*** \"" + file.getName () + "\" created successfully ***");
        }
        catch (PlayerExportException pee)
        {
            view.displayError (pee.getMessage ());
        }
    }

    private void changeAdminPassword ()
    {
        final String password = view.readString ("Enter new password : ");
        final AdminDAO adminDAO = service.getAdmin ();
        adminDAO.setPassword (Utility.getHash (password));
        service.saveAdmin (adminDAO);

        view.display ("*** Password updated! ***");
    }

    private boolean login ()
    {
        final String password = view.readString ("Password : ");
        return service.login (password);
    }

    private void logout ()
    {
        try
        {
            service.logout ();
        }
        catch (LogoutException le)
        {
            view.displayError (le.getMessage ());
        }
    }
}
