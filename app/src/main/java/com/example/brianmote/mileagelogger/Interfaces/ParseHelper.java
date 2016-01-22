package com.example.brianmote.mileagelogger.Interfaces;

import java.util.List;

/**
 * Created by Brian Mote on 1/21/2016.
 */
public interface ParseHelper<T> {
    List<T> getById(String id);
    void create(T t);
    void update(T t);
    void delete(T t);
}
