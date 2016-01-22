package com.example.brianmote.mileagelogger.Helpers;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Brian Mote on 1/21/2016.
 */

@ParseClassName("Appointment")
public class Appointment extends ParseObject {
    private String name;

    public Appointment(){
        //Required for Parse
    }
}
