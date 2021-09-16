package com.resttechsolutions.contactapi6.repository;

import com.resttechsolutions.contactapi6.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
