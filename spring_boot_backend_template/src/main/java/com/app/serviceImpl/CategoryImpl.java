package com.app.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.app.DTO.CategoryDto;
import com.app.ResourceNotFoundException.ResourceNotFoundException;
import com.app.entities.Admin;
import com.app.entities.Categories;
import com.app.entities.Quiz;
import com.app.repository.AdminRepository;
import com.app.repository.CategoryRepository;
import com.app.repository.QuizRepository;
import com.app.services.CategoryService;
@Service
@Transactional
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepository  categoryRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Categories update(Categories category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public List<Categories> getAll() {
		
		return categoryRepository.findAll();
	}



	@Override
	public void delete(Long catId) {
	    categoryRepository.deleteById(catId);
	}



	@Override
	public Categories getById(Long catId) {
		Optional<Categories> o=categoryRepository.findById(catId);
		return o.orElseThrow(()->new ResourceNotFoundException("id not found"));
	}

	@Override
	public CategoryDto addNew(CategoryDto categoryDto) {
	    // Find the Admin by ID, throw an exception if not found
	    Admin admin = adminRepository.findById(categoryDto.getAdminId())
	            .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + categoryDto.getAdminId()));

	    // Find the Quizzes by their IDs, throw an exception if any quiz is not found
	    List<Quiz> quizzes = categoryDto.getQuizIds().stream()
	            .map(quizId -> quizRepository.findById(quizId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId)))
	            .collect(Collectors.toList());

	    // Create a new Category entity and set its properties
	    Categories cat = new Categories();
	    cat.setAdmin(admin);
	    cat.setDescription(categoryDto.getDescription());
	    cat.setTitle(categoryDto.getTitle());
	    cat.setQuizzes(quizzes);

	    // Save the Category entity
	    Categories savedCategory = categoryRepository.save(cat);

	    // Prepare and return the DTO for the saved Category
	    CategoryDto savedCategoryDto = new CategoryDto();
	    savedCategoryDto.setCatId(savedCategory.getCatId());
	    savedCategoryDto.setTitle(savedCategory.getTitle());
	    savedCategoryDto.setDescription(savedCategory.getDescription());
	    savedCategoryDto.setAdminId(admin.getAdminId());
	    savedCategoryDto.setQuizIds(quizzes.stream().map(Quiz::getQuizId).collect(Collectors.toList()));

	    return savedCategoryDto;
	}

}
