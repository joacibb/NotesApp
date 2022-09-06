package ar.com.noteschallenge.controller;

import ar.com.noteschallenge.DTO.NoteDTO;
import ar.com.noteschallenge.model.Note;
import ar.com.noteschallenge.service.impl.CategoryService;
import ar.com.noteschallenge.service.impl.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(value = "/api")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private CategoryService catService;

    //**********************| GETs |********************************

    @GetMapping(value = "/notes/{id}")
    public NoteDTO findByID(@PathVariable("id") Long idNote){
        Note note = noteService.findById(idNote);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(note, NoteDTO.class);
    }

    @GetMapping(value="/notes")
    public List<Note> findAll() {
        return noteService.findAll();
    }

    @GetMapping(value="/notes/archived")
    public List<Note> findAllArchived() {
        List<Note> list = noteService.findAll();

        List<Note> archived = new ArrayList<>();

        for (Note note : list) {
            if(note!=null) {
                if (note.getArchived())
                    archived.add(note);
            }
        }

        return archived;
    }

    @GetMapping(value="/notes/available")
    public List<Note> available() {
        List<Note> list = noteService.findAll();

        List<Note> archived = new ArrayList<>();

        for (Note note : list) {
            if(note!=null) {
                if (!note.getArchived())
                    archived.add(note);
            }
        }
        return archived;
    }

    //**********************| POSTs |********************************

    @PostMapping(value="/notes")
    public void create(@RequestBody Note note) {
        noteService.create(note);
    }


    //**********************| PUTs |********************************

    @PutMapping(value="/notes/{id}")
    public void update( @PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        ModelMapper modelMapper = new ModelMapper();
        NoteDTO note = noteDTO;
        noteService.updateNote(modelMapper.map(note,Note.class),id);
    }

    @PutMapping(value="/notes/{id}/{category}")
    public void updateCategory(@PathVariable     Long id,@PathVariable String category) {
        noteService.updateCategory(noteService.findById(id),category);
    }


    @PutMapping(value = "/notes/status/{idNote}")
    public void changeStatus(@PathVariable Long idNote){
        noteService.changeStatus(idNote);
    }


    //**********************| DELETE |********************************
    @DeleteMapping(value = "/notes/{id}")
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}
