package upd.dev.bbfp.handler.events;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ButtonEvent {
    void onButtonEvent(Update update) throws TelegramApiException;
}
