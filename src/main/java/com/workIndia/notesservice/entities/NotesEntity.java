package com.workIndia.notesservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="notes")
public class NotesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "userid")
    private UserEntity userEntity;

    @Column(name = "notes", nullable = false, unique = false)
    private String notes;
}
