package ar.com.noteschallenge.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="notes")
public class Note implements Serializable {
    public Note(){};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    String title;
    @Column(name = "content")
    @NonNull
    String content;
    @Column(name = "archived")
    boolean archived;

    @Column(name = "categories")
    @OneToMany( cascade = {CascadeType.ALL})
    List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategory(Category category) {
        categories.add(category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isArchived() {
        return archived;
    }

    public void changeArchived() {
        archived = !archived;
    }

    public void setArchived(boolean archived){
        this.archived=archived;
    }

    public void deletCat(String nameCategory){
        for (Category category1 : categories) {
            if(category1!=null){
                if(category1.getName().equals(nameCategory)){
                    categories.remove(category1);
                    break;
                }
            }
        }
    }

    public boolean findCategoryClass(String category) {
        boolean toReturn = false;
        for (Category category1 : categories) {
            if(category1.getName().equals(category)){
                toReturn=true;
                break;
            }
        }
        return toReturn;
    }

    public Long findCategoryID(Long categoryID) {
        Long toReturn = null;
        for (Category category1 : categories) {
            if(category1.getId().equals(categoryID)){
                toReturn=category1.getId();
                break;
            }
        }
        return toReturn;
    }

    public boolean getArchived() {
        return archived;
    }

}