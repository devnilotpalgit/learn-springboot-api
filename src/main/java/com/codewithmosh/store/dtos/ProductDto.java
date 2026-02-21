package com.codewithmosh.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    @JsonIgnore
    private Byte category_id;
}
