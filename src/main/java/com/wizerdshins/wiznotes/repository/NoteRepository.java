package com.wizerdshins.wiznotes.repository;

import com.wizerdshins.wiznotes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();

    List<Note> findAllByDoneIsTrue();

    void deleteAllByDoneIsTrue(List<Note> completedNotes);
}
