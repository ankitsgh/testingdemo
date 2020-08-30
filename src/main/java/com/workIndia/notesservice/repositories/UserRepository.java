package com.workIndia.notesservice.repositories;

import com.workIndia.notesservice.entities.NotesEntity;
import com.workIndia.notesservice.entities.UserEntity;
import com.workIndia.notesservice.entries.UserEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("Select f from UserEntity f where f.username= :username and f.password=:password")
    UserEntity login(@Param("username") String username, @Param("password") String password );
}
