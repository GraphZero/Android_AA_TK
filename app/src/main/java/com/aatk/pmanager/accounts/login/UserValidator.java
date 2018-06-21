package com.aatk.pmanager.accounts.login;

import android.content.Context;

/**
 * Used to validate user
 */

public interface UserValidator {
    void validateUser(String username, String password);
}
