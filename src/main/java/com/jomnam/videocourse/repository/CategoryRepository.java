package com.jomnam.videocourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomnam.videocourse.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
