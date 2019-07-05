package com.wizerdshins.wiznotes.controller;

import com.wizerdshins.wiznotes.entity.Note;
import com.wizerdshins.wiznotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {

    private NoteService noteService;
    private String sortDateMethod = "ASC";

    @Autowired
    public void setNoteService(NoteService service) {
        this.noteService = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        List<Note> notebook = filterAndSort();
        model.addAttribute("notes", notebook);
        model.addAttribute("sort", sortDateMethod);
        return "index";
    }

    @GetMapping("/sort/{sortDate}")
    public String sortChoose(@PathVariable String sortDate) {
        sortDateMethod = sortDate;
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Note note = noteService.findById(id);
        model.addAttribute("note", note);
        return "operations/edit";
    }

    @PostMapping("/update")
    public String saveNote(@RequestParam Integer id, @RequestParam String message,
                           @RequestParam(value = "done", required = false) boolean done) {
        noteService.update(id, message, done);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newNote() {
        return "operations/new";
    }

    @PostMapping("/save")
    public String updateNote(@RequestParam String message) {
        noteService.save(new Note(message));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        noteService.delete(id);
        return "redirect:/";
    }

    private List<Note> filterAndSort() {
        List<Note> notebook = null;
        switch (sortDateMethod) {
            case "ASC":
                notebook = noteService.findAllByOrderByDateAsc();
                break;
            case "DESC":
                notebook = noteService.findAllByOrderByDateDesc();
                break;
        }
        return notebook;
    }

}