package com.csci213.a1.view;

import com.csci213.a1.dao.PlayerDAO;
import com.csci213.a1.common.Keyboard;

import java.time.LocalDate;

public class View
{
    private static final String [] MENU_MAIN =
    {
        "Create Player",
        "Delete a Player",
        "Edit Player Information",
        "Export Players",
        "Change Admin Password"
    };

    private static final String [] MENU_EDIT = {"Login", "Password", "Chips", "Name", "Email", "Birth Date"};

    private static final String TITLE_MAIN = "CSCI 213 Admin Module";
    private static final String TITLE_EDIT = "Edit Player";

    public int mainMenu ()
    {
        return Keyboard.menuInput (TITLE_MAIN, MENU_MAIN);
    }

    public int editMenu ()
    {
        return Keyboard.menuInput (TITLE_EDIT, MENU_EDIT);
    }

    public void display (final String message)
    {
        System.out.println ();
        System.out.println (message);
        System.out.println ();
    }

    public void displayError (final String message)
    {
        System.out.println ();
        System.err.println ("*** " + message + " ***");
        System.out.println ();
    }

    public void displayPlayer (final PlayerDAO playerDAO)
    {
        System.out.println ();

        System.out.println (playerDAO.getLogin ());
        Keyboard.printLine ('-', 50);
        System.out.println ("Balance Chips : " + playerDAO.getChips ());
        System.out.println ("Name : " + playerDAO.getName ());
        System.out.println ("Email : " + playerDAO.getEmail ());
        System.out.println ("Birth Date : " + playerDAO.getBirthDate ());

        System.out.println ();
    }

    public String readString (final String prompt)
    {
        return Keyboard.readString (prompt);
    }

    public long readLong (final String prompt)
    {
        return Keyboard.readLong (prompt);
    }

    public LocalDate readDate (final String prompt)
    {
        return Keyboard.readDate (prompt);
    }

    public String readEmail (final String prompt)
    {
        return Keyboard.readEmail (prompt);
    }
}