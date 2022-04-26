package com.example.businessapp.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfigs {
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "thuong",
                "api_key", "753394882693917",
                "api_secret", "ItIvv3XFhxXHBdB1lL7Xhqa0Nx8",
                "secure", true
        ));
        return c;
    }
}