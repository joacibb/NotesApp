package ar.com.noteschallenge.service.impl;

import ar.com.noteschallenge.DTO.CategoryDTO;
import ar.com.noteschallenge.model.Category;
import ar.com.noteschallenge.repository.CategoryRepository;
import ar.com.noteschallenge.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     *  This method searches for the requested category, by ID
     * @param idCat
     * @return "Category" if found - "ObjectNotFound" if not found.
     */
    public Category findById(Long idCat) {
        Optional<Category> obj = categoryRepository.findById(idCat);
        return obj.orElseThrow(
                () ->
                        new ObjectNotFoundException(
                                "Object not found - ID: " +
                                        idCat +
                                        " Type: " +
                                        Category.class.getName()
                        )
        );
    }

    /**
     * @return  returns a list of all existing categories
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * receives a CategoryDTO, and copies its data to the category containing the same ID(passed by parameter)
     * dev by JC.
     * @param idCat
     * @param catDTO
     * @return
     */
    public Category update(Long idCat, CategoryDTO catDTO) {
        Category obj = findById(idCat);
        obj.setName(catDTO.getName());
        obj.setId(catDTO.getId());
        return categoryRepository.save(obj);
    }

    /**
     * Create a new category
     * @param obj
     * @return
     */
    public Category create(Category obj) {
        obj.setId(null);
        return categoryRepository.save(obj);
    }

    /**
     * privated method: receives the name of a category and returns the ID of the first one it finds with that name
     * dev by JC.
     * @param categoryName
     * @return
     */
    private Long findID(String categoryName){
        Long toRet = null;
        List<Category> allIDs = categoryRepository.findAll();
        for (Category allID : allIDs) {
            if(allID.getName().equals(categoryName)){
                toRet=allID.getId();
                break;
            }
        }
        return toRet;
    }

    /**
     * public method that receives the name of a category and returns the category ID.
     * @param categoryName
     * @return
     */
    public Long findByName(String categoryName){
        return findID(categoryName);
    }

    /**
     * saves changes to a category
     * @param category
     */
    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    /**
     * Receives a category and returns a new category of the same type.
     * @param category
     * @return new Category
     */
    public Category clone(Category category){
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        return newCategory;
    }

    /**
     * receives the category name and a list. See if that name is already stored in the list.
     * @param name
     * @param list
     * @return true if already stored, false if not
     */
    private boolean alreadySave(String name, List<Category> list){
        boolean esta = false;
        for (Category category : list) {
            if(category.getName().equals(name)){
                esta=true;
                break;
            }
        }
        return esta;
    }

    /**
     * receives a list with categories having the same name but different IDs,
     * and returns a new list containing a single category of each type
     * dev by JC
     * @param notFilterList
     * @return  list containing a single category of each type
     */
    public List<Category> listFilter(List<Category> notFilterList){
        List<Category> filtered = new ArrayList<>();

        for (Category category : notFilterList) {
            if(!alreadySave(category.getName(),filtered)){
                filtered.add(category);
            }
        }

        return filtered;
    }
}
