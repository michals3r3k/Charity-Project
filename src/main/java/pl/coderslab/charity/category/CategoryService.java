package pl.coderslab.charity.category;


import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void delete(Category category);
}
