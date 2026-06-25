package com.dormitory.merit.domain.category.repository;

import com.dormitory.merit.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(int Id);
    Optional<Category> findByTitle(String title);
    Optional<Category> findAllBy();
}
