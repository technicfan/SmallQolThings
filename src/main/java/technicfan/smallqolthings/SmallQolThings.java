package technicfan.smallqolthings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmallQolThings {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmallQolThings.class);

    public static void info(String message) {
        LOGGER.info("[QOL] " + message);
    }
}
