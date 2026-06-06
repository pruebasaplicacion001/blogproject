

package com.example.blogproject.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ModerationService {

    @Value("${sightengine.api-user}")
    private String apiUser;

    @Value("${sightengine.api-secret}")
    private String apiSecret;

    private final RestClient restClient = RestClient.create();

    public String checkImage(String imageUrl) {

        String url =
                "https://api.sightengine.com/1.0/check.json"
                        + "?models=nudity-2.1,self-harm,violence,gore-2.0"
                        + "&api_user=" + apiUser
                        + "&api_secret=" + apiSecret
                        + "&url=" + imageUrl;

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }
}