package com.spring.ecommerce.repos;

import com.spring.ecommerce.entities.products.categories.CategoryMetadataField;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMetadataFieldRepository extends CrudRepository<CategoryMetadataField, Long> {

    @Query("select name from CategoryMetadataField")
    List<String> findAllFieldNames();

    @Query("from CategoryMetadataField")
    List<CategoryMetadataField> allMetadataFields();

    CategoryMetadataField findById(long id);

    @Query("select id from CategoryMetadataField where name=:name")
    Long findIdByName(@Param("name") String fieldName);
}
