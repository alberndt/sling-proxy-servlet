package com.alexanderberndt.aem.proxy;

public class ProxyServlet {

}
//
//import org.osgi.service.component.annotations.Activate;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.ConfigurationPolicy;
//import org.osgi.service.component.annotations.ServiceScope;
//import org.osgi.service.metatype.annotations.AttributeDefinition;
//import org.osgi.service.metatype.annotations.Designate;
//import org.osgi.service.metatype.annotations.ObjectClassDefinition;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.Servlet;
//
//@Component(
//        service = Servlet.class,
//        scope = ServiceScope.PROTOTYPE,
//        configurationPolicy = ConfigurationPolicy.REQUIRE,
//        property = {
////                "osgi.http.whiteboard.servlet.pattern=/on/demandware.store/*",
//                "osgi.http.whiteboard.context.select=(osgi.http.whiteboard.context.name=" + ServletContext.CONTEXT_NAME + ")"}
//)
//@Designate(ocd = ProxyServlet.Configuration.class, factory = true)
//public class ProxyServlet extends org.mitre.dsmiley.httpproxy.ProxyServlet {
//
//    // Timeout, if no connection is established after milliseconds
//    private static final int CONNECTION_TIMEOUT = 10000;
//
//    // Timeout, if connection hangs
//    private static final int READ_TIMEOUT = 20000;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyServlet.class);
//
//
//
//    private ProxyServlet.Configuration config;
//
//    @Override
//    protected String getConfigParam(String key) {
//        switch (key) {
//            case P_TARGET_URI:
//                return config.targetUri();
//            case P_LOG:
//                return Boolean.toString(config.enableLogging());
//            case P_FORWARDEDFOR:
//                return Boolean.toString(config.doForwardIp());
//            case P_PRESERVEHOST:
//                return Boolean.toString(config.doPreserveHost());
//            case P_PRESERVECOOKIES:
//                return Boolean.toString(config.doPreserveCookies());
//            case P_HANDLEREDIRECTS:
//                return Boolean.toString(config.doHandleRedirects());
//            case P_CONNECTTIMEOUT:
//                return Integer.toString(config.connectTimeout());
//            case P_READTIMEOUT:
//                return Integer.toString(config.readTimeout());
//            case P_CONNECTIONREQUESTTIMEOUT:
//                return Integer.toString(config.connectionRequestTimeout());
//            case P_MAXCONNECTIONS:
//                return Integer.toString(config.maxConnections());
//            case P_USESYSTEMPROPERTIES:
//                return Boolean.toString(config.useSystemProperties());
//            default:
//                return null;
//        }
//
//    }
//
//    @Activate
//    protected void activate(ProxyServlet.Configuration configuration) {
//        this.config = configuration;
//    }
//}
//
//
