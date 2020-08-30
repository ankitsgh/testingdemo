package com.workIndia.notesservice.repositories;

import com.workIndia.notesservice.entities.NotesEntity;
import com.workIndia.notesservice.entities.UserEntity;
import com.workIndia.notesservice.entries.NoteEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository  extends CrudRepository<NotesEntity, Integer> {

    @Query("Select f from NotesEntity f where lower(f.userEntity.id)= :userid")
    List<NotesEntity> getNotes(@Param("userid") Integer userid);
}
