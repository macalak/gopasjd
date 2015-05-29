package ite.librarymaster.service;

import javax.ejb.ApplicationException;

/**
 * Application exception definition.
 * 
 * @author macalak@itexperts.sk
 *
 */
@SuppressWarnings("serial")
// Denotes this exception as application one
// It can be configured to rollback a transaction (rollback=true)
// @ApplicationException(rollback=true)
public class LibraryException extends Exception {

    public LibraryException(String message) {
        super(message);
    }

}
