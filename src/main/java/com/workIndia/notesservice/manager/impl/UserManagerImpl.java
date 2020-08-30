package com.workIndia.notesservice.manager.impl;

import com.workIndia.notesservice.entities.NotesEntity;
import com.workIndia.notesservice.entities.UserEntity;
import com.workIndia.notesservice.entries.NoteEntry;
import com.workIndia.notesservice.entries.UserEntry;
import com.workIndia.notesservice.manager.UserManager;
import com.workIndia.notesservice.repositories.NoteRepository;
import com.workIndia.notesservice.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;

import java.util.List;


@Data
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void register(UserEntry userEntry) throws Exception {

        UserEntity userEntity = convertUserEntryToEntity(userEntry);
        userRepository.save(userEntity);

    }

    public String login(UserEntry userEntry) throws Exception {

        UserEntity userEntity = convertUserEntryToEntity(userEntry);
        UserEntity userEntity1 = userRepository.login(userEntry.getUserName(), userEntry.getPassword());
        if(userEntity1 != null && userEntity1.getId() != null){
            return "Success";
        }else{
            return "fail";
        }

    }


    public void addNewNote(Integer userId, NoteEntry noteEntry) throws Exception{

        UserEntity userEntity = findUserEntity(userId);
        NotesEntity notesEntity = noteRepository.save(convertNoteEntryToEntity(noteEntry,userEntity));
        List<NotesEntity> notesEntity1 = userEntity.getNotesEntityList();
        if(notesEntity1 == null){
            notesEntity1 = Lists.newArrayList(notesEntity);

        }else{
            notesEntity1.add(notesEntity);
        }
        userEntity.setNotesEntityList(notesEntity1);
        userRepository.save(userEntity);
    }

    private UserEntity findUserEntity(Integer userId){

        return userRepository.findById(userId).get();

    }



    private UserEntity convertUserEntryToEntity(UserEntry userEntry){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userEntry.getUserName());
        userEntity.setPassword(userEntry.getPassword());
        userEntity.setNotesEntityList(null);
        return userEntity;
    }

    private UserEntry convertUserEntityToEntry(UserEntity userEntity){
        UserEntry userEntry = new UserEntry();
        userEntry.setUserName(userEntry.getUserName());

        userEntry.setNoteEntryList(convertNoteEntityToEntry(userEntity.getNotesEntityList()));
        return userEntry;
    }
    private List<NoteEntry> convertNoteEntityToEntry(List<NotesEntity> notesEntity){
        List<NoteEntry> noteEntries = Lists.newArrayList();
        for(NotesEntity notesEntity1 : notesEntity) {
            NoteEntry noteEntry = new NoteEntry();
            noteEntry.setNotes(notesEntity1.getNotes());
            noteEntries.add(noteEntry);

        }
        return noteEntries;
    }


    private NotesEntity convertNoteEntryToEntity(NoteEntry noteEntry, UserEntity userEntity){
        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setNotes(noteEntry.getNotes());
        notesEntity.setUserEntity(userEntity);
        return notesEntity;
    }
    public UserEntry getAllNotes(Integer userId) throws Exception{
        List<NotesEntity> notesEntity = noteRepository.getNotes(userId);
        UserEntry userEntry = new UserEntry();
        if(notesEntity != null){
            userEntry.setNoteEntryList(convertNoteEntityToEntry(notesEntity));
        }
        return userEntry;
    }

}
