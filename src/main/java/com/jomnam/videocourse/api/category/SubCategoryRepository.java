package com.jomnam.videocourse.api.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	List<SubCategory> findByTitleContainingIgnoreCase(String title);
}
