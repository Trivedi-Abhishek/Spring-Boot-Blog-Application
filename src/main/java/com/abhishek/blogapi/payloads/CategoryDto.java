package com.abhishek.blogapi.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class CategoryDto {
    
    private Integer categoryId;
    @NotBlank
    @Size(min=4,message = "Title should be of atleast 4 characters")
    private String categoryTitle;
    @NotBlank
    @Size(min=10,message = "Description should be of atleast 4 characters")
    private String categoryDescription;
}
