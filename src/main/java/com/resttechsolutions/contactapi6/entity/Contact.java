package com.resttechsolutions.contactapi6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends AbstractPersistable<Long> {

    private String name;
    private String email;
    @OneToMany(mappedBy = "contact", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<Phone> phones;
    @OneToMany(mappedBy = "contact", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<Address> addresses;

    public void setPhones(List<Phone> phones) {
        phones.forEach(item -> item.setContact(this));
        this.phones = phones;
    }

    public void setAddresses(List<Address> addresses) {
        addresses.forEach(item -> item.setContact(this));
        this.addresses = addresses;
    }
}
