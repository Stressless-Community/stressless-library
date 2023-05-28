package com.stresslesslibrary.bookservice.dtos;

import com.stresslesslibrary.bookservice.entities.Language;
import com.stresslesslibrary.bookservice.entities.PrintKind;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.mapping.List;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class BookDTO {

	@NotEmpty
	@Min(13)
	private String isbn;

	@NotEmpty
	private String title;
	private String subtitle;
	@NotNull @NotNull
	private MultipartFile cover;
	private String description;
	private String language;
	private int pageCount;
	private String publishedDate;
	private String kind;
	private Boolean pdfAvailble;
	private Boolean epubAvailble;
	private Boolean isReference;
	private String publisher;
	private String branchId;

	private int[] authors;
}
