package com.aatk.pmanager.accounts.register;

import com.aatk.pmanager.accounts.domain.Role;
import com.aatk.pmanager.accounts.domain.User;

public interface RegisterService {
    User registerUser(String username, String password, Role role);
}
