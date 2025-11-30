package com.ecommerce.sb_ecom.controller;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
   private CategoryService categoryService;
//===================================================GET===========================================
    @GetMapping("api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
//===================================================POST==================================================
    @PostMapping("api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody  Category category)
    {
       categoryService.createCategory(category);
       return new ResponseEntity<>("category successfully created", HttpStatus.CREATED);
    }
//==================================================DELETE===================================================
    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
       try{
           String status = categoryService.deleteCategory(categoryId);
           return ResponseEntity.status(HttpStatus.OK).body(status);
       }catch(ResponseStatusException e){
           return new ResponseEntity<>(e.getReason(),e.getStatusCode());
       }
    }

    //===================================================UPDATE=================================================

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable Long categoryId){

        try{
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category with id:"+ category.getCategoryId(), HttpStatus.OK);
        }
        catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }

    }

}
