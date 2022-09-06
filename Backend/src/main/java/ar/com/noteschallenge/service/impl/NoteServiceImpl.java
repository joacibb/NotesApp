package ar.com.noteschallenge.service.impl;

import ar.com.noteschallenge.DTO.NoteDTO;
import ar.com.noteschallenge.exception.ObjectNotFoundException;
import ar.com.noteschallenge.model.Category;
import ar.com.noteschallenge.model.Note;
import ar.com.noteschallenge.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CategoryService categoryService;

    public void create(Long idCat, Note note) {
        note.setId(null);
        Category category = categoryService.findById(idCat);
        note.setCategory(category);
        noteRepository.save(note);
    }

    /**
     * Create a new note
     * @param note
     */
    public void create(Note note) {
        note.setId(null);
        noteRepository.save(note);
    }

    /**
     * looks for the note containing the ID passed by parameter
     * @param idNote
     * @return
     */
    public Note findById(Long idNote) {
        Optional<Note> obj = noteRepository.findById(idNote);
        return obj.orElseThrow(
                () ->
                        new ObjectNotFoundException(
                                "Note not found - ID: " +
                                        idNote +
                                        " Type: " +
                                        Note.class.getName()
                        )
        );
    }

    /**
     * Delete the note passed by parameters
     * @param idNote
     */
    public void delete(Long idNote) {
        findById(idNote);
        noteRepository.deleteById(idNote);
    }

    /**
     *List of all Notes
     * @return list with all notes
     */
    @Override
    @Transactional(readOnly = true)
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    /**
     * Save the note
     * @param note
     */
    @Transactional(readOnly = false)
    public void save(Note note) {
         noteRepository.save(note);
    }

    /**
     * You receive a note and an ID. Updates the note containing that id with the contents of the new note passed by parameter.
     * dev by JC.
     *
     * @param note
     * @param idNote
     */
    @Override
    public void updateNote(Note note, Long idNote) {
        try{
        Note existentNote = noteRepository.findById(idNote).orElse(null);
        assert existentNote != null;
        existentNote.setTitle(note.getTitle());
        existentNote.setArchived(note.getArchived());
        existentNote.setContent(note.getContent());
        save(existentNote);
        }
        catch (Exception e) {throw new ObjectNotFoundException(
                "Object not found Id: " +
                        idNote +
                        ", Type: " +
                        Note.class.getName()
        );
    }
    }

    /**
     *This method receives a note and a category name.
     *
     * It searches if a category with that name already exists in the repository, and if it does, it adds the tag to the note and saves it.
     * If it does not exist, it creates a new category, adds it to the repository and assigns it to the note.
     * Dev by JC.
     * @param note
     * @param category
     */
    public void updateCategory(Note note, String category) {
        Long idCategory = categoryService.findByName(category);

        if(idCategory!=null && !note.findCategoryClass(category)){
            Category cat = categoryService.findById(idCategory);
            note.setCategory(categoryService.clone(cat));
            save(note);
        }
        else{
            if(!note.findCategoryClass(category)){
            Category newCategory = new Category(category);
            categoryService.save(newCategory);
            note.setCategory(newCategory);}
        }
        save(note);
    }

    /**
     * Create a new list. It scrolls through all the notes and if any contains the requested category name
     * it adds it to the list.
     * Dev by JC.
     * @param categoryName
     * @return List with all Notes with that tag
     */
    public List<Note> filterByCat(String categoryName){

        List<Note> filtredList = new ArrayList<>(); //New list
        List<Note> List = noteRepository.findAll();

        for (Note note : List) {
                if(note.findCategoryClass(categoryName)){
                    filtredList.add(note);
                }
        }
        return filtredList;
    }

    /**
     * searches for the note by ID and if it exists asks to delete the category
     * @param idNota
     * @param category
     */
    public void deleteCategory(Long idNota, String category) {
        Note nota = findById(idNota);
        assert nota != null;
        nota.deletCat(category);
        save(nota);
    }

    /**
     * changes the status to the opposite of the one assigned to it
     * @param idNota
     */
    public void changeStatus(Long idNota){
        Note note = findById(idNota);
        if(note!=null){
        note.changeArchived();
        save(note);}
    }
}
