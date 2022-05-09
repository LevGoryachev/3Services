package ru.goryachev.ui.exception;
/**
 * MultiChiefObjectNotFoundException is a custom MultiChief application exception
 * (informs about DTO/entity type and id/property name of object having problems)
 * @author Lev Goryachev
 * @version 1.1
 */
public class MicroServiceNotAnswerException extends RuntimeException {

    public MicroServiceNotAnswerException(String microserviceName) {
        super(String.format("No response from %s", microserviceName));
    }
}
