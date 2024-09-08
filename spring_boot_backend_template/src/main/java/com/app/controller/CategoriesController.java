package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.DTO.CategoryDto;
import com.app.entities.Categories;
import com.app.services.CategoryService;
@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("insert")
    public ResponseEntity<?> addNew(@RequestBody CategoryDto categoryDto) {
        try {
            // Validate the incoming DTO (optional)
            if (categoryDto.getTitle() == null || categoryDto.getTitle().isEmpty()) {
                return ResponseEntity.badRequest().body("Category title is required.");
            }

            CategoryDto savedCategoryDto = categoryService.addNew(categoryDto);

            return ResponseEntity.ok(savedCategoryDto);
        } catch (Exception e) {
            // Log the error to server logs for debugging
            e.printStackTrace();
            // Return a detailed error response
            return ResponseEntity.status(500).body("Failed to add category: " + e.getMessage());
        }
    }



    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Categories categories) {
        categories.setCatId(id);  
        Categories c = categoryService.update(categories);
        return ResponseEntity.ok("Category updated: " + c);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Categories>> getAll() {
        List<Categories> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("getCategoryById/{catId}")
    public Categories getCategoryById(@PathVariable Long catId) {
    	return categoryService.getById(catId);
    }
    
}
