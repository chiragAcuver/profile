package com.pgb.profile.data;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Data
public class Profile {
    @Id
    private String id;
    @NotNull(message = "First Name cannot be null")
    @Field
    private String firstName;
    @Field
    private String lastName;
    @Field
    private String profileImageUrl;
    @Field
    private String businessLogoUrl;
    @Field
    private Boolean isVerified;

}
