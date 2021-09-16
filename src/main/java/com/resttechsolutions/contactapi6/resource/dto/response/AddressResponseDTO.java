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
public class AddressResponseDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String addressName;
    @JsonBackReference
    private ContactResponseDTO contactDTO;

}
