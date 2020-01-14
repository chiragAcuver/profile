package com.pgb.profile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component public class ServiceConfigurations {
    @Value("${api.identity.service-host}")
    public String API_IDENTITY_SIGNUP_HOST;

    @Value("${api.identity.sign-up-endpoint}")
    public String API_IDENTITY_SIGNUP_ENDPOINT;
}
