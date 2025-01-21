package upd.dev.bbfp.handler;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Execute {
    void exe(Update update) throws TelegramApiException;
}
