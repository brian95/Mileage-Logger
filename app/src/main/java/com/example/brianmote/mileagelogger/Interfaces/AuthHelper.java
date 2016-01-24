package com.example.brianmote.mileagelogger.Interfaces;

import com.example.brianmote.mileagelogger.Helpers.ParseAuthHelper;
import com.example.brianmote.mileagelogger.Listeners.AuthListener;

/**
 * Created by Brian Mote on 1/22/2016.
 */
public interface AuthHelper<T> {
    void register(AuthListener listener);
    void login(String username, String password, AuthListener listener);
    void logout(AuthListener listener);
}
