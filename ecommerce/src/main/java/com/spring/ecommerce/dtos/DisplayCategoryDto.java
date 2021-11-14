package com.spring.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class DisplayCategoryDto {

    private long id;
    private String categoryName;
    private Map<String,String> metadataFieldsAndValues;
    private String parentChain;
}
