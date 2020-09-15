package com.yarmak.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JdkHttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String IOS_UA = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.2 Mobile/15E148 Safari/604.1";

    private final HttpClient httpClient;

    public JdkHttpClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String sendGet(final URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("User-Agent", IOS_UA)
                .build();
        HttpResponse<String> response = null;
        try {
            response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        processHeaders(response);
        return response.body();
    }

    private void processHeaders(final HttpResponse<String> response) {
        final HttpHeaders headers = response.headers();
        LOGGER.debug("Headers contain {} entries.", headers.map().size());
    }

}
