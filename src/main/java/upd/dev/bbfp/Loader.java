package upd.dev.bbfp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.bbfp.handler.ButtonHandler;
import upd.dev.bbfp.handler.CommandHandler;


public class Loader extends TelegramLongPollingBot {
    CommandHandler commandHandler;
    ButtonHandler buttonHandler;
    Config config;

    @SuppressWarnings("deprecation")
    public Loader() {
        config = new Config();
        System.out.println("Dotenv: is load");

        commandHandler = new CommandHandler(this);
        buttonHandler = new ButtonHandler(this);
        System.out.println("Loader: all handlers is loaded");
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                commandHandler.onCommandEvent(update);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasCallbackQuery()) {
            try {
                buttonHandler.onButtonEvent(update);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Config loadEnv() {
        return config;
    }
    @Override
    public String getBotUsername() {
        return config.getBotUserName();
    }
    @SuppressWarnings("deprecation")
    @Override
    public String getBotToken() {
        return config.getBotToken();
    }
    public void onRegister() {
        System.out.println("Bot: is load");
    }
}