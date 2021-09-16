package com.resttechsolutions.contactapi6.repository;

import com.resttechsolutions.contactapi6.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
