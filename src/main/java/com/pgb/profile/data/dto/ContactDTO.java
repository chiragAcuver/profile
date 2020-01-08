package com.pgb.profile.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO {
    @NotNull(message = "email Id cannot be null")
    private String emailId;

    private String id;

    @NotNull(message = "password cannot be null")
    @Size(min = 6)
    @JsonIgnoreProperties("hidden")
    private String password;


    @Min(1000000000L)
    @Max(9999999999L)
    private Long mobileNo;

    @NotNull(message = "country code for mobile number should be provided")
    private Integer countryCode;

    @Size(min = 1, message = "at-least one address should be provided")
    private List<Address> addresses;

}
