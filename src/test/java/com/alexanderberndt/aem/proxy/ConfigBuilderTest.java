package com.alexanderberndt.aem.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigBuilderTest {

    @Test
    void of() {
        ReverseProxyServletConfiguration config = ConfigBuilder.of(ReverseProxyServletConfiguration.class)
                .set("targetUri", "http://www.example.com")
                .build();

        assertEquals("http://www.example.com", config.targetUri());
        assertEquals(-1, config.connectTimeout());
        assertFalse(config.enable());
        assertTrue(config.doForwardIp());
        assertNull(config.osgi_http_whiteboard_servlet_pattern());
    }
}