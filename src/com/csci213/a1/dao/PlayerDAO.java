package com.csci213.a1.dao;

import java.time.LocalDate;
import java.util.Objects;

public class PlayerDAO
{
    private String login;
    private String password;
    private long chips;
    private String name;
    private String email;
    private LocalDate birthDate;

    public PlayerDAO ()
    {
        this.login = "";
        this.password = "";
        this.chips = 0;
        this.name = "";
        this.email = "";
        this.birthDate = null;
    }

    public PlayerDAO (String login, String password, long chips, String name, String email, LocalDate birthDate)
    {
        this.login = login;
        this.password = password;
        this.chips = chips;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getLogin ()
    {
        return login;
    }

    public void setLogin (String login)
    {
        this.login = login;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public long getChips ()
    {
        return chips;
    }

    public void setChips (long chips)
    {
        this.chips = chips;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public LocalDate getBirthDate ()
    {
        return birthDate;
    }

    public void setBirthdDate (LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    @Override
    public String toString ()
    {
        return login + "|" + password + "|" + chips + "|" + name + "|" + email + "|" + birthDate;
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        PlayerDAO playerDAO = (PlayerDAO) o;
        return login.equals (playerDAO.login);
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash (login);
    }
}
