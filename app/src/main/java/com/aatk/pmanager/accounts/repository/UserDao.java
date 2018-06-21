package com.aatk.pmanager.accounts.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aatk.pmanager.accounts.domain.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> findAll();

    @Query("SELECT * FROM user WHERE username LIKE :name LIMIT 1")
    User findByUsername(String name);

    @Insert
    void insert(User user);

    @Insert
    void insertAll(List<User> children);

    @Update
    void update(User childRoom);

    @Delete
    void delete(User childRoom);

}
