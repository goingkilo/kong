package com.gojek.drivers.api;

/**
 * Created by kraghunathan on 12/19/16.
 */
public class InvalidLocationException extends Exception {
    public InvalidLocationException(String message) {
        super(message);
    }
}
