package com.workIndia.notesservice.manager;

import com.workIndia.notesservice.entries.NoteEntry;
import com.workIndia.notesservice.entries.UserEntry;

public interface UserManager {



    public void register(UserEntry userEntry) throws Exception;

    public String login(UserEntry userEntry) throws Exception;


    public void addNewNote(Integer userId, NoteEntry noteEntry) throws Exception;

    public UserEntry getAllNotes(Integer userId) throws Exception;





}
