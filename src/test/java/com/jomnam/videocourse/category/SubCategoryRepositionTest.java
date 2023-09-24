package com.jomnam.videocourse.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jomnam.videocourse.api.category.Category;
import com.jomnam.videocourse.api.category.CategoryRepository;
import com.jomnam.videocourse.api.category.SubCategory;
import com.jomnam.videocourse.api.category.SubCategoryRepository;

@DataJpaTest
public class SubCategoryRepositionTest {
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Test
	public void testFindByTitleContainingIgnoreCase() {
		//given
		
		//when
		List<SubCategory> categories = subCategoryRepository.findByTitleContainingIgnoreCase("e");
		
		//then
		assertThat(categories).isNotEmpty();
	}
	
}
