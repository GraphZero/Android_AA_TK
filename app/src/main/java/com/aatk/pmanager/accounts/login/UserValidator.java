package com.aatk.pmanager.accounts.login;


/**
 * Used to validate user
 */

public interface UserValidator {
    void validateUser(String username, String password);
}
