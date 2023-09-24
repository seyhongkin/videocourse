package com.jomnam.videocourse.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jomnam.videocourse.api.category.Category;
import com.jomnam.videocourse.api.category.CategoryRepository;

@DataJpaTest
public class CategoryRepositionTest {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testFindByTitleContainingIgnoreCase() {
		//given
		
		//when
		List<Category> categories = categoryRepository.findByTitleContainingIgnoreCase("i");
		
		//then
		assertThat(categories).isNotEmpty();
	}
	
}
