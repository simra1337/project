package com.spring.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DisplayProductForCustomerDto {
    private long id;
    private String name;
    private String description;
    private boolean isCancellable;
    private String brand;
    private boolean isReturnable;
    private boolean isActive;
    private long categoryId;
    private String categoryName;
    private List<DisplayProductVariationForCustomerDto> variations;
}
