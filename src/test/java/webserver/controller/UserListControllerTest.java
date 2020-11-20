package webserver.controller;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import model.User;
import webserver.HttpRequestFixture;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

class UserListControllerTest {
    private static final Logger log = LoggerFactory.getLogger(UserListControllerTest.class);
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new UserListController();
    }

    @Test
    void get() throws IOException {
        HttpRequest httpRequest = HttpRequestFixture.httpRequestOfUserList();

        HttpResponse httpResponse = controller.service(httpRequest);


    }

    @Test
    void name() throws Exception {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);

        Template template = handlebars.compile("user/profile");

        User user = new User("javajigi", "password", "자바지기", "javajigi@gmail.com");
        String profilePage = template.apply(user);
        log.debug("ProfilePage : {}", profilePage);
    }
}