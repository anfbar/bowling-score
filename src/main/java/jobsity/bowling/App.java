package jobsity.bowling;

import jobsity.bowling.controller.GameController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.URISyntaxException;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("jobsity.bowling.service.impl, jobsity.bowling.controller.impl")
public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        try {
            getGameController().startGame();
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Check file format", e);
        } catch (Exception e) {
            LOGGER.error("System error", e);
        }
    }

    private static GameController getGameController() {
        return getApplicationContext().getBean(GameController.class);
    }

    private static ApplicationContext getApplicationContext() {
        return new AnnotationConfigApplicationContext(App.class);
    }
}
