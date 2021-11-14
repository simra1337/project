package com.spring.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AllCategoryInfoDto {

    private long id;
    private String categoryName;
    private String parentChain;
    private List<DisplayCategoryDto> immediateChildList;
}
