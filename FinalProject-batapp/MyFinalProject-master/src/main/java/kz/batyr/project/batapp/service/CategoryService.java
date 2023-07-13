package kz.batyr.project.batapp.service;

import kz.batyr.project.batapp.model.Category;
import kz.batyr.project.batapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public List<Category> getCategories(){
    return categoryRepository.findAll();
  }

  public Category addCategory(Category category){
    return categoryRepository.save(category);
  }

  public void deleteCategory(Long id){
    categoryRepository.deleteById(id);
  }
}
