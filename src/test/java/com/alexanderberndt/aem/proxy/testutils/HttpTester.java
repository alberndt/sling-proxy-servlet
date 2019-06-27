package com.alexanderberndt.aem.proxy.testutils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpTester {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpTester.class);

    private String requestLine;
    private int statusCode;
    private String statusLine;
    private String entityBody;
    private Map<String, String> headers;

    public static HttpTester execute(HttpGet httpget) throws IOException {

        HttpTester result = new HttpTester();
        result.requestLine = httpget.getRequestLine().toString();

        LOGGER.info("Executing request {}", httpget);
        try (CloseableHttpClient httpclient = buildHttpClient()) {
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {

                result.statusCode = response.getStatusLine().getStatusCode();
                result.statusLine = response.getStatusLine().getReasonPhrase();
                result.headers = Arrays.stream(response.getAllHeaders())
                        .collect(Collectors.toMap(Header::getName, Header::getValue));

                if (result.statusCode >= 200 && result.statusCode < 300) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        result.entityBody = EntityUtils.toString(entity);
                    }
                }
            }
        }
        return result;
    }

    private static CloseableHttpClient buildHttpClient() {
        return HttpClients.custom()
                .setProxy(new HttpHost("localhost", 8888))
                .build();
    }

    public HttpTester assertStatusCode(int expected) {
        assertEquals(expected, this.statusCode, this.requestLine + " => status code");
        return this;
    }

    public HttpTester assertStatusLine(String expected) {
        assertEquals(expected, this.statusLine, this.requestLine + " => status line");
        return this;
    }

    public HttpTester assertResponseHeader(String key, String value) {
        assertEquals(value, this.headers.get(key), this.requestLine + " => " + key + " (header)");
        return this;
    }

    public HttpTester assertEntityBody(String expected) {
        assertEquals(expected, this.entityBody, this.requestLine + " => entity body");
        return this;
    }

}
