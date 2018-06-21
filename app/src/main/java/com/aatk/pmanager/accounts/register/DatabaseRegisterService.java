package com.aatk.pmanager.accounts.register;

import android.arch.persistence.room.Room;
import android.util.Log;

import com.aatk.pmanager.MainActivity;
import com.aatk.pmanager.accounts.domain.Role;
import com.aatk.pmanager.accounts.domain.User;
import com.aatk.pmanager.accounts.repository.UserDao;
import com.aatk.pmanager.accounts.repository.UserDatabase;

class DatabaseRegisterService implements RegisterService {
    private UserDao userDao;

    public DatabaseRegisterService() {
        userDao = MainActivity.userDatabase.userDao();
    }

    @Override
    public User registerUser(String username, String password, Role role) {
        User user = new User(username, password, role);
        userDao.insert(user);
        return user;
    }

}
