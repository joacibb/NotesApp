package ar.com.noteschallenge.DTO;

import ar.com.noteschallenge.model.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Long id;
    private String name;

    public CategoryDTO(Category obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
