package com.workIndia.notesservice.entries;


import lombok.Data;

import java.util.List;

@Data
public class UserEntry {

    private Integer id;
    private String userName;
    private String password;
    private List<NoteEntry> noteEntryList;

}
