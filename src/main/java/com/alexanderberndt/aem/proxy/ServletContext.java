package com.alexanderberndt.aem.proxy;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.context.ServletContextHelper;

import static org.osgi.framework.Constants.SERVICE_RANKING;
import static org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME;
import static org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_PATH;

@Component(
        service = ServletContextHelper.class,
        scope = ServiceScope.BUNDLE,
        property = {
                HTTP_WHITEBOARD_CONTEXT_NAME + "=" + ServletContext.CONTEXT_NAME,
                HTTP_WHITEBOARD_CONTEXT_PATH + "=/",
                SERVICE_RANKING +":Integer=201"})
public class ServletContext extends ServletContextHelper {

//        contextProperties.put(Constants.SERVICE_DESCRIPTION, "Apache Sling Engine Servlet Context Helper");
//        contextProperties.put(Constants.SERVICE_VENDOR, "The Apache Software Foundation");

    public static final String CONTEXT_NAME = "com.alexanderberndt.sling.proxy";

    // no need to implement methods
}

