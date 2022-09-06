package ar.com.noteschallenge.DTO;

import ar.com.noteschallenge.model.Category;
import ar.com.noteschallenge.model.Note;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoteDTO implements Serializable {

    private Long id;

    private String title;

    private String content;

    private boolean archived;


    private List<Category> categories = new ArrayList<>();
    public NoteDTO(Long id, String title, String content, boolean archived, List<Category> categories){
        this.id = id;
        this.title = title;
        this.content = content;
        this.archived = archived;
        this.categories = categories;
    }

    public NoteDTO(){}

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean getArchived(){
        return archived;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isArchived() {
        return archived;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(String category){
        Category c1 = new Category(category);
        categories.add(c1);
    }

}
