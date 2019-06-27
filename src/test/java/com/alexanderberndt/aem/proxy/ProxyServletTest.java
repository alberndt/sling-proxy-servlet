package com.alexanderberndt.aem.proxy;

import com.alexanderberndt.aem.proxy.testutils.HttpTester;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class ProxyServletTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyServletTest.class);

    private static WireMockServer wireMockServer;

    private static TestProxyServletEngine testProxyServletEngine;

    private enum TestServer {
        WIRE_MOCK(8090, false),
        EMBEDDED_JETTY(8080, true),
        //SLING_SERVER(8080, true),
        //AEM_SERVER(4502, true),
        APACHE2_WEBSERVER(80, true);

        private final int port;

        private final boolean isProxy;

        TestServer(int port, boolean isProxy) {
            this.port = port;
            this.isProxy = isProxy;
        }

        public int getPort() {
            return port;
        }

        public boolean isProxy() {
            return isProxy;
        }
    }

    private static Stream<Arguments> provideTestServer() {
        return Stream.of(
                arguments(TestServer.WIRE_MOCK),
                arguments(TestServer.EMBEDDED_JETTY),
                arguments(TestServer.APACHE2_WEBSERVER)
        );
    }

    @BeforeAll
    static void setupTestServer() throws Exception {
        startProxyServletEngine();
        startWireMockServer();
    }

    @AfterAll
    static void tearDownTestServer() throws Exception {
        testProxyServletEngine.stop();
        wireMockServer.stop();
    }

    @BeforeEach
    private void setup() {
        wireMockServer.resetAll();
    }

    private static void startProxyServletEngine() throws Exception {
        testProxyServletEngine = new TestProxyServletEngine(8080);
        testProxyServletEngine.start();
        testProxyServletEngine.activateReverseProxy(
                ConfigBuilder.of(ReverseProxyServletConfiguration.class)
                        .set("targetUri", "http://localhost:8090/an")
                        .set("osgi_http_whiteboard_servlet_pattern", "/an/*")
                        .build());
    }

    private static void startWireMockServer() {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
    }


    @ParameterizedTest
    @MethodSource("provideTestServer")
    void simpleGet2(TestServer testServer) throws IOException {
        LOGGER.info("Test with {} on port {}", testServer.name(), testServer.getPort());


        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/an/endpoint"))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withStatusMessage("Everything fine here!")
                        .withHeader("X-VALUE", "Berndt")
                        .withBody("Hello World")));
        //wireMockServer.start();


        HttpGet httpGet = new HttpGet("http://localhost:" + testServer.getPort() + "/an/endpoint");
        HttpTester.execute(httpGet)
                .assertStatusCode(200)
                .assertStatusLine("Everything fine here!")
                .assertResponseHeader("X-VALUE", "Berndt")
                .assertEntityBody("Hello World");
    }


    @Test
    void simpleGetPrototype() throws IOException {
        //wireMockServer.resetAll();

        wireMockServer = new WireMockServer(9999);
        wireMockServer.start();

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/an/endpoint"))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withStatusMessage("Everything fine here!")
                        .withHeader("X-VALUE", "Berndt")
                        .withBody("Hello World")));
        //wireMockServer.start();


        HttpGet httpGet = new HttpGet("http://localhost:9999/an/endpoint");
        HttpTester.execute(httpGet)
                .assertStatusCode(200)
                .assertStatusLine("Everything fine here!")
                .assertResponseHeader("X-VALUE", "Berndt")
                .assertEntityBody("Hello World");

        wireMockServer.stop();

    }

}
