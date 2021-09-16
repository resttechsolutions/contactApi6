package com.resttechsolutions.contactapi6.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String phoneNumber;
    @JsonBackReference
    private ContactResponseDTO contactDTO;
}
