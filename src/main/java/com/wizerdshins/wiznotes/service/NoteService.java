package com.wizerdshins.wiznotes.service;

import com.wizerdshins.wiznotes.entity.Note;

import java.util.List;

public interface NoteService {

    Note findById(Integer id);
    void save(Note note);
    void update(Integer id, String message, boolean done);
    void delete(Integer id);

    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();
}
