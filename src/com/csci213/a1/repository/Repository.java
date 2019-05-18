package com.csci213.a1.repository;

import java.util.List;

public interface Repository <T, U>
{
    T save (final T t);
    T delete (final T t);
    T findById (final U u);
    List <T>  findAll ();
}
