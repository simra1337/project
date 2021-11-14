package com.spring.ecommerce.repos;

import com.spring.ecommerce.entities.products.categories.CategoryMetadataFieldValues;
import com.spring.ecommerce.entities.products.categories.CategoryMetadataFieldValuesId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryMetadataFieldValuesRepository extends CrudRepository<CategoryMetadataFieldValues, CategoryMetadataFieldValuesId> {

    @Query("from CategoryMetadataFieldValues where id.categoryId=:cid AND id.categoryMetadataFieldId=:mid")
    CategoryMetadataFieldValues findByMetadataCompositeId(@Param("cid") long categoryId, @Param("mid") long metadataId);

    @Query("select value from CategoryMetadataFieldValues where id.categoryId=:cid AND id.categoryMetadataFieldId=:mid")
    String findValueByCompositeId(@Param("cid") long categoryId, @Param("mid") long metadataId);
}
