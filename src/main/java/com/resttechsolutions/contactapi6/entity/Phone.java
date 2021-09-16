package com.resttechsolutions.contactapi6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table(name = "phone")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Phone extends AbstractPersistable<Long> {

    private String name;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
}