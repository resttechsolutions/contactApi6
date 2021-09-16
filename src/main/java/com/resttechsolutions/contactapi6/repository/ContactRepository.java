package com.resttechsolutions.contactapi6.repository;

import com.resttechsolutions.contactapi6.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
