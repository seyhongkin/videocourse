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
import com.jomnam.videocourse.api.category.SubCategory;
import com.jomnam.videocourse.api.category.SubCategoryRepository;
import com.jomnam.videocourse.api.category.SubCategoryService;
import com.jomnam.videocourse.api.category.SubCategoryServiceImpl;
import com.jomnam.videocourse.api.category.web.SubCategoryDTO;
import com.jomnam.videocourse.exception.ResourceNotFoundExceptionHandler;

@ExtendWith(MockitoExtension.class)
public class SubCategoryServiceTest {
	@Mock
	private SubCategoryRepository subCategoryRepository;

	private SubCategoryService subCategoryService;

	@BeforeEach
	private void setUpService() {
		subCategoryService = new SubCategoryServiceImpl(subCategoryRepository);
	}

	@Captor
	private ArgumentCaptor<SubCategory> argumentCaptor;

	@Test
	public void testCreate() {
		// given
		SubCategory subCategory = SubCategory.builder()
				.id(1L).title("Cloud Api").description("").build();

		// when
		subCategoryService.create(subCategory);

		// then
		verify(subCategoryRepository, times(1)).save(subCategory);
	}
	
	@Test
	public void testGetByIdSuccess() {
		//given
		SubCategory subCategory = SubCategory.builder()
				.id(1L)
				.title("AI")
				.description("")
				.build();
		
		//when
		when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));
		SubCategory returnSubCategory = subCategoryService.getById(1L);
		
		//then
		verify(subCategoryRepository, times(1)).findById(1L);
		assertEquals(subCategory.getId(), returnSubCategory.getId());
		assertEquals(subCategory.getTitle(), returnSubCategory.getTitle());
		assertEquals(subCategory.getDescription(), returnSubCategory.getDescription());
	}
	
	@Test
	public void testGetByIdThrow() {
		//given
		
		//when
		when(subCategoryRepository.findById(1L)).thenReturn(Optional.empty());
		
		//then
		assertThatThrownBy(() -> subCategoryService.getById(1L))
			.isInstanceOf(ResourceNotFoundExceptionHandler.class)
			.hasMessageEndingWith("not found");
	}
	
	@Test
	public void testRemove() {
		//given
		SubCategory subCategory = SubCategory.builder()
				.id(1L)
				.title("Network Engineering")
				.description("")
				.build();
		
		//when
		when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));
		subCategoryService.remove(1L);
		
		//then
		verify(subCategoryRepository, times(1)).delete(subCategory);
	}
	
	@Test
	public void testUpdate() {
		//given
		SubCategory givenSubCategory = SubCategory.builder()
				.id(1L)
				.title("AWS Cloud")
				.description("")
				.build();
		
		SubCategory subCategoryUpdateTo = SubCategory.builder()
				.id(1L)
				.title("Cloud Api")
				.description("")
				.build();
		
		SubCategoryDTO subCategoryUpdateToDTO = new SubCategoryDTO();
		subCategoryUpdateToDTO.setTitle(subCategoryUpdateTo.getTitle());
		subCategoryUpdateToDTO.setDescription(subCategoryUpdateTo.getDescription());
		
		//when
		when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(givenSubCategory));
		subCategoryService.update(1L, subCategoryUpdateToDTO);
		
		//then
		verify(subCategoryRepository, times(1)).findById(1L);
		verify(subCategoryRepository, times(1)).save(argumentCaptor.capture());
		
		assertEquals(subCategoryUpdateTo.getId(), argumentCaptor.getValue().getId());
		assertEquals(subCategoryUpdateTo.getTitle(), argumentCaptor.getValue().getTitle());
		assertEquals(subCategoryUpdateTo.getDescription(), argumentCaptor.getValue().getDescription());
	}
	
	@Test
	public void testGetByTitle() {
		//given
		SubCategory subCate1 = SubCategory.builder()
				.id(1L)
				.title("IT")
				.description("Media")
				.build();
		SubCategory subCate2 = SubCategory.builder()
				.id(2L)
				.title("Social Media")
				.build();
		
		//when
		when(subCategoryRepository.findByTitleContainingIgnoreCase("i")).thenReturn(List.of(subCate1, subCate2));
		subCategoryService.getByTitle("i");
		
		//then
		verify(subCategoryRepository, times(1)).findByTitleContainingIgnoreCase("i");
	}
}
