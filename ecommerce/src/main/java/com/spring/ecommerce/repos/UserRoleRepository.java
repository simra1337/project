package com.spring.ecommerce.repos;

import com.spring.ecommerce.entities.UserRole;
import com.spring.ecommerce.entities.UserRoleId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {

    @Query(value = "select * from user_role where user_id=:userId", nativeQuery = true)
    public List<UserRole> findRecordForUserId(@Param("userId") long userId);
}
