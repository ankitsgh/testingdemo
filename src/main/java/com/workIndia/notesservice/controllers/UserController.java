package com.workIndia.notesservice.controllers;


import com.workIndia.notesservice.entries.NoteEntry;
import com.workIndia.notesservice.entries.UserEntry;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserController {

    @GetMapping("/test")
    public String test() throws Exception;


    @PostMapping("/register")
    public String register(@RequestBody UserEntry userEntry) throws Exception;


    @PostMapping("/login")
    public String login(@RequestBody UserEntry userEntry) throws Exception;


    @PostMapping("/addNewNote")
    public String addNewNote(@RequestParam(name = "userId") Integer userId , @RequestBody NoteEntry noteEntry) throws Exception;

    @GetMapping("/getAllNotes")
    public UserEntry getAllNotes(@RequestParam(name = "userId") Integer userId) throws Exception;


}
