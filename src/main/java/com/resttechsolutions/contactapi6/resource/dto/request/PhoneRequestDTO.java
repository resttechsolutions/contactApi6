package com.resttechsolutions.contactapi6.resource.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String phoneNumber;
    @JsonBackReference
    private Long contactId;

}
