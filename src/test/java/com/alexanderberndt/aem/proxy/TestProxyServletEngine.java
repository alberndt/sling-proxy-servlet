package com.alexanderberndt.aem.proxy;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestProxyServletEngine {

    private Server server;

    private ServletHandler handler;

    public TestProxyServletEngine(int port) {
        this.server = new Server(port);
        this.handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(HelloServlet.class, "/hello");
    }

    public void activateReverseProxy(ReverseProxyServletConfiguration configuration) {

        // create new servlet and simulate OSGi activation
        ReverseProxyServlet servlet = new ReverseProxyServlet();
        servlet.activate(configuration);

        // register servlet with servlet engine
        handler.addServletWithMapping(
                new ServletHolder(servlet),
                configuration.osgi_http_whiteboard_servlet_pattern()
        );
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        this.server.stop();
    }

    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException,
                IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("Hello from HelloServlet");
        }
    }
}

