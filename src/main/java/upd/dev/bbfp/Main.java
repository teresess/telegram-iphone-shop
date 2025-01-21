package upd.dev.bbfp;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class Main {

    @Getter
    public static Loader loader;

    public static void main(String[] args) throws TelegramApiException {
        try {
            SpringApplication.run(Main.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TelegramBotsApi tba = new TelegramBotsApi(DefaultBotSession.class);
        System.out.println("main: loading...");
        loader = new Loader();
        tba.registerBot(loader);
  }
}