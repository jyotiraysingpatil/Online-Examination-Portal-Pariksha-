package com.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Categories;
@Repository
public interface CategoryRepository  extends JpaRepository<Categories, Long> {

}
