package com.gojek.drivers.api;

/**
 * Created by kraghunathan on 12/19/16.
 */



public class InvalidDriverException extends Exception {
    public InvalidDriverException(String message) {
        super(message);
    }
}
