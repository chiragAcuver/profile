package com.pgb.profile.data.dto;

import com.pgb.profile.data.Profile;
import lombok.Data;

import javax.validation.Valid;

@Data
public class CreateProfileDTO {
    @Valid
    private Profile profile;
    @Valid
    private ContactDTO contactDTO;
}
