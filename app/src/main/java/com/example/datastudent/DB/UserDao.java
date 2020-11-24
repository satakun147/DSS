package com.example.datastudent.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.datastudent.model.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    User[] selectAllData();
    @Query("SELECT * FROM users where :id = id")
    User getDataById(int id);
    @Insert
    void insertData(User... users);
    @Query("UPDATE users SET status = :status  WHERE name = :name  AND last_name = :lastName")
    void updateUsers(String name , String lastName ,String status);
}
