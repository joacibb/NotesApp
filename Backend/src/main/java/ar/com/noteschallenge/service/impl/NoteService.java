package ar.com.noteschallenge.service.impl;

import ar.com.noteschallenge.DTO.NoteDTO;
import ar.com.noteschallenge.model.Category;
import ar.com.noteschallenge.model.Note;
import java.util.List;

public interface NoteService {
    void create(Long idCat, Note note);

    Note findById(Long idNote);

    void delete(Long idNote);

    List<Note> findAll();

    void updateNote(Note note, Long idNote);
    void create(Note note);

    void save(Note note);
    void updateCategory(Note note, String category);
    List<Note> filterByCat(String categoryName);

    void deleteCategory(Long idNota, String category);

    void changeStatus(Long idNota);

}
