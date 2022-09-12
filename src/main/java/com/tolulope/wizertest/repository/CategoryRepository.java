package com.tolulope.wizertest.repository;

import com.tolulope.wizertest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsCategoryByName(String name);
}
