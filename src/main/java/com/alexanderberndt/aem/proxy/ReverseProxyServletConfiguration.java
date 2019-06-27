package com.alexanderberndt.aem.proxy;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Adobe AEM Reverse Proxy Servlet",
        description = "Catch all requests matching the URL pattern, and forwards them to the target uri..")
public @interface ReverseProxyServletConfiguration {


    @AttributeDefinition(
            name = "Target Uri",
            description = "name for the target (destination) URI to proxy to (e.g. https://www.hugoboss.com)"
    )
    String targetUri();

    @AttributeDefinition(
            name = "URL Pattern",
            description = "URL Pattern as in <servlet-mapping><url-pattern>....</..></..> as in standard web.xml configurations (e.g. /on/demandware.store/*)"
    )
    String osgi_http_whiteboard_servlet_pattern();


    @AttributeDefinition(
            name = "Enable Proxy",
            description = "Catch all requests matching the URL pattern, and forwards them to the target uri."
    )
    boolean enable() default false;


    @AttributeDefinition(
            name = "Enable Logging",
            description = "Enable logging of input and target URLs to the servlet log."
    )
    boolean enableLogging() default false;

    @AttributeDefinition(
            name = "Forward Client-IP",
            description = "Enable forwarding of the client IP."
    )
    boolean doForwardIp() default true;

    @AttributeDefinition(
            name = "Preserve Host",
            description = "Keep HOST parameter as-is."
    )
    boolean doPreserveHost() default false;

    @AttributeDefinition(
            name = "Preserve Cookies",
            description = "Keep COOKIES as-is."
    )
    boolean doPreserveCookies() default false;

    @AttributeDefinition(
            name = "Auto-handle Redirects",
            description = "have auto-handle redirects (http.protocol.handle-redirects)."
    )
    boolean doHandleRedirects() default false;

    @AttributeDefinition(
            name = "HTTP Socket Timeout",
            description = "set the socket connection timeout in millis (http.socket.timeout)."
    )
    int connectTimeout() default -1;

    @AttributeDefinition(
            name = "HTTP Read Timeout",
            description = "set the socket read timeout in millis (http.read.timeout)."
    )
    int readTimeout() default -1;

    @AttributeDefinition(
            name = "HTTP Connection Request Timeout",
            description = "set the connection request timeout in millis (http.connectionrequest.timeout)."
    )
    int connectionRequestTimeout() default -1;

    @AttributeDefinition(
            name = "Max. Connections",
            description = "set max connection number (http.maxConnections)."
    )
    int maxConnections() default -1;


    @AttributeDefinition(
            name = "Use System Properties",
            description = "Use JVM-defined system properties to configure various networking aspects."
    )
    boolean useSystemProperties() default true;

}


