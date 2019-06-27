package com.alexanderberndt.aem.proxy;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class ReverseProxyServletTest {

    WireMockServer wireMockServer;

    static TestProxyServletEngine testProxyServletEngine;

    static IntStream provideProxyPortForProxyTests() {
        return IntStream.of(8090, 4502, 80);
    }

    @BeforeAll
    static void setupTestServer() throws Exception {
        testProxyServletEngine = new TestProxyServletEngine(8080);
        testProxyServletEngine.activateReverseProxy(
                ConfigBuilder.of(ReverseProxyServletConfiguration.class)
                        .set("targetUri", "http://localhost:8090")
                        .set("osgi_http_whiteboard_servlet_pattern", "/myproxy/*")
                        .build());
        testProxyServletEngine.start();
    }

    @AfterAll
    static void tearDownTestServer() throws Exception {
        testProxyServletEngine.stop();
    }

    @Test
    public void testSomething() {

    }


}