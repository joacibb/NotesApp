package ar.com.noteschallenge.controller;

import ar.com.noteschallenge.DTO.CategoryDTO;
import ar.com.noteschallenge.model.Category;
import ar.com.noteschallenge.model.Note;
import ar.com.noteschallenge.service.impl.CategoryService;
import ar.com.noteschallenge.service.impl.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping(value ="/api")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @Autowired
    private NoteService noteService;

    //**********************| GETs |********************************
    @GetMapping(value="/notes/categories")
    public List<CategoryDTO> findAll() {
        List<Category> list = service.findAll();
        list = service.listFilter(list);
        List<CategoryDTO> listDTO = list
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());

        return listDTO;
    }

    @GetMapping(value="/{name}")
    public List<Note> filterCategories(@PathVariable("name") String nameCat) {
        return noteService.filterByCat(nameCat);
    }

    //**********************| DELETE |********************************
    @DeleteMapping(value = "/notes/{id}/{name}")

    public void delete(@PathVariable("id") Long idNota, @PathVariable("name") String catName) {
        noteService.deleteCategory(idNota,catName);
    }
}
