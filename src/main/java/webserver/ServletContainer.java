package webserver;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import webserver.controller.Controller;
import webserver.domain.request.HttpRequest;

public class ServletContainer {
    private static final Logger logger = LoggerFactory.getLogger(ServletContainer.class);
    private final Map<String, String> servletNameMapper;
    private final Map<String, Controller> servletContainer;

    public ServletContainer() {
        servletNameMapper = new HashMap<>();
        servletContainer = new HashMap<>();
        servletNameMapper.put("/user/create", "webserver.servlet.UserCreate");
        logger.info("ServletContainer has loaded.");
    }

    public boolean hasMappingServlet(HttpRequest httpRequest) {
        return servletNameMapper.get(httpRequest.getDefaultPath()) != null;
    }

    public Controller getController(HttpRequest httpRequest) {
        return getInstance(httpRequest.getDefaultPath());
    }

    private synchronized Controller getInstance(String requestPath) {
        try {
            if (servletContainer.get(requestPath) == null) {
                String className = servletNameMapper.get(requestPath);
                Class clazz = Class.forName(className);
                servletContainer.put(requestPath, (Controller)clazz.newInstance());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage());
            throw new CannotInitServletException(requestPath);
        }
        return servletContainer.get(requestPath);
    }
}