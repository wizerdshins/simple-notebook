package com.wizerdshins.wiznotes.service;

import com.wizerdshins.wiznotes.entity.Note;
import com.wizerdshins.wiznotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note findById(Integer id) {
        return noteRepository.findOne(id);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void update(Integer id, String message, boolean done) {
        Note updatedNote = noteRepository.findOne(id);
        updatedNote.setDone(done);
        updatedNote.setMessage(message);
        noteRepository.save(updatedNote);
    }

    @Override
    public void delete(Integer id) {
        noteRepository.delete(id);
    }

    @Override
    public List<Note> findAllByOrderByDateAsc() {
        return noteRepository.findAllByOrderByDateAsc();
    }

    @Override
    public List<Note> findAllByOrderByDateDesc() {
        return noteRepository.findAllByOrderByDateDesc();
    }
}
