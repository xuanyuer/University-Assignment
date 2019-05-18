package com.csci213.a1.repository;

import com.csci213.a1.dao.AdminDAO;

import java.util.Collections;
import java.util.List;

public class AdminRepositoryImpl implements Repository <AdminDAO, String>
{
    private AdminDAO adminDAO;

    public AdminRepositoryImpl (final AdminDAO adminDAO)
    {
        this.adminDAO = adminDAO;
    }

    @Override
    public AdminDAO save (final AdminDAO adminDAO)
    {
        this.adminDAO = adminDAO;
        return this.adminDAO;
    }

    @Override
    public AdminDAO delete (final AdminDAO adminDAO)
    {
        if (this.adminDAO.equals (adminDAO)) this.adminDAO = null;
        return adminDAO;
    }

    @Override
    public AdminDAO findById (final String id)
    {
        return this.adminDAO;
    }

    @Override
    public List <AdminDAO> findAll ()
    {
        return Collections.singletonList (adminDAO);
    }
}
