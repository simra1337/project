package com.spring.ecommerce.repos;

import com.spring.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {

    @Query(value = "select * from address where user_id=:id", nativeQuery = true)
    public List<Address> findAddressByUserId(@Param("id") long id);

    Address findById(long id);

    @Query(value = "select id from address where user_id=:id",nativeQuery = true)
    List<Long> findAddressIdsForUserId(@Param("id") long id);
}
