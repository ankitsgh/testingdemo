package com.workIndia.notesservice.controllers.impl;

import com.workIndia.notesservice.controllers.UserController;
import com.workIndia.notesservice.entries.NoteEntry;
import com.workIndia.notesservice.entries.UserEntry;
import com.workIndia.notesservice.manager.UserManager;
import com.workIndia.notesservice.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data

public class UserControllerImpl implements UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    @Override
    public String test() throws Exception {
        userRepository.findAll();
        return "Hello";
    }

    public String register(UserEntry userEntry) throws Exception{
        userManager.register(userEntry);
        return "Done";
    }

    public String login(UserEntry userEntry) throws Exception{
        return  userManager.login(userEntry);
    }

    public String addNewNote(@RequestParam(name = "userId") Integer userId , @RequestBody NoteEntry noteEntry) throws Exception{
        userManager.addNewNote(userId, noteEntry);
        return "Done";
    }

    public UserEntry getAllNotes(@RequestParam(name = "userId") Integer userId) throws Exception{
        return userManager.getAllNotes(userId);
    }


}
