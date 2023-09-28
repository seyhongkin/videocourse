package com.jomnam.videocourse.category;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jomnam.videocourse.api.category.Category;
import com.jomnam.videocourse.api.category.CategoryRepository;
import com.jomnam.videocourse.api.category.CategoryService;
import com.jomnam.videocourse.api.category.CategoryServiceImpl;
import com.jomnam.videocourse.api.category.SubCategory;
import com.jomnam.videocourse.api.category.web.CategoryDTO;
import com.jomnam.videocourse.exception.ResourceNotFoundExceptionHandler;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	@Mock
	private CategoryRepository categoryRepository;
	
	private CategoryService categoryService;
	
	@BeforeEach
	private void setUpService() {
		categoryService = new CategoryServiceImpl(categoryRepository);
	}
	
	@Captor
	private ArgumentCaptor<Category> argumentCaptor;
	
	@Test
	public void testCreate() {
		//given 
		Category category = new Category();
		category.setTitle("IT");
		category.setDescription("Media Design");
		
		//when
		categoryService.create(category);
		
		//then
		verify(categoryRepository, times(1)).save(category);
	}
	
	@Test
	public void testGetByIdFound() {
		//given
		Category category = new Category();
		category.setId(1L);
		category.setTitle("IT");
		category.setDescription("Media Design");
		
		//when
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		Category returnCategory = categoryService.getById(1L);
		
		//then
		assertEquals(category.getId(), returnCategory.getId());
		assertEquals(category.getTitle(), returnCategory.getTitle());
		assertEquals(category.getDescription(), returnCategory.getDescription());
	}
	
	@Test
	public void testGetByIdNotFount() {
		//given
		
		//when
		when(categoryRepository.findById(2L)).thenReturn(Optional.empty());
		
		//then
		assertThatThrownBy(() -> categoryService.getById(2L))
			.isInstanceOf(ResourceNotFoundExceptionHandler.class)
			.hasMessageEndingWith("not found");
	}
	
	@Test
	public void testGetAll() {
		//given
		Category category1 = Category.builder()
				.id(1L)
				.title("IT")
				.description("Media")
				.subCategories(null)
				.build();
		Category category2 = Category.builder()
				.id(2L)
				.title("Social Media")
				.subCategories(null)
				.build();
		//when
		when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));
		List<Category> categories = categoryService.getAll();
		
		//then
		verify(categoryRepository, times(1)).findAll();
		assertEquals(2, categories.size());
		assertEquals(category1, categories.get(0));
		assertEquals(category1.getId(), categories.get(0).getId());
		assertEquals(category1.getTitle(), categories.get(0).getTitle());
		assertEquals(category1.getDescription(), categories.get(0).getDescription());
		
		assertEquals(category2, categories.get(1));
		assertEquals(category2.getId(), categories.get(1).getId());
		assertEquals(category2.getTitle(), categories.get(1).getTitle());
		assertEquals(category2.getDescription(), categories.get(1).getDescription());
	}
	
	@Test
	public void testGetSubCategoriesSuccess() {
		//given
		SubCategory subCategory1 = SubCategory.builder()
				.id(1L)
				.title("Social Media")
				.description("")
				.build();
		
		SubCategory subCategory2 = SubCategory.builder()
				.id(2L)
				.title("Social & Communication")
				.description("")
				.build();
		
		Category category = Category.builder()
				.id(1L)
				.title("Media")
				.description("")
				.subCategories(List.of(subCategory1, subCategory2))
				.build();
		
		//when
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		List<SubCategory> subCategories = categoryService.getSubCategories(1L);
		
		//then
		verify(categoryRepository, times(1)).findById(1L);
		assertEquals(2, subCategories.size());
		assertEquals(1L, subCategories.get(0).getId());
		assertEquals(2L, subCategories.get(1).getId());
	}
	
	@Test
	public void testGetSubCategoriesThrow() {
		//given
		
		//when
		when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
		
		//then
		assertThatThrownBy(() -> categoryService.getSubCategories(1L))
			.isInstanceOf(ResourceNotFoundExceptionHandler.class)
			.hasMessageEndingWith("not found");
	}
	
	@Test
	public void testRemove() {
		//given
		Category category = Category.builder()
				.id(1L)
				.title("Media")
				.description("")
				.build();
		
		//when
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		categoryService.remove(1L);
		
		//then
		verify(categoryRepository, times(1)).findById(1L);
		verify(categoryRepository, times(1)).delete(category);
	}
	
	@Test
	public void testUpdate() {
		//given
		Category givenCategory = Category.builder()
				.id(1L)
				.title("IT")
				.description("Media")
				.build();
		
		Category updateTo = Category.builder()
				.id(1L)
				.title("Information Technology")
				.description("Media")
				.build();
		
		CategoryDTO updateToDTO = new CategoryDTO();
		updateToDTO.setTitle(updateTo.getTitle());
		updateToDTO.setDescription(updateTo.getDescription());
		
		//when
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(givenCategory));
		categoryService.update(1L, updateToDTO);
		
		//then
		verify(categoryRepository, times(1)).findById(1L);
		verify(categoryRepository, times(1)).save(argumentCaptor.capture());
		
		assertEquals(updateTo.getId(), argumentCaptor.getValue().getId());
		assertEquals(updateTo.getTitle(), argumentCaptor.getValue().getTitle());
		assertEquals(updateTo.getDescription(), argumentCaptor.getValue().getDescription());
	}
	
	@Test
	public void testGetByTitle() {
		//given
		Category category1 = Category.builder()
				.id(1L)
				.title("IT")
				.description("Media")
				.subCategories(null)
				.build();
		Category category2 = Category.builder()
				.id(2L)
				.title("Social Media")
				.subCategories(null)
				.build();
		
		//when
		when(categoryRepository.findByTitleContainingIgnoreCase("i")).thenReturn(List.of(category1, category2));
		categoryService.getByTitle("i");
		
		//then
		verify(categoryRepository, times(1)).findByTitleContainingIgnoreCase("i");
	}
}
