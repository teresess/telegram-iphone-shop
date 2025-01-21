package upd.dev.bbfp.actions.run.events;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.bbfp.Loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Send {
    Loader bot;
    String text;
    String chat_id;
    List<List<InlineKeyboardButton>> underMessage = new ArrayList<>();
    ReplyKeyboardMarkup underKeyboard;

    public Send(Loader bot, String text, String chat_id) {
        this.bot = bot;
        this.text = text;
        this.chat_id = chat_id;
    }
    public Send addRow(InlineKeyboardButton... btns) {

        underMessage.add(new ArrayList<>(Arrays.asList(btns)));
        return this;
    }
    public Send addRow(KeyboardButton... btns) {
        KeyboardRow row = new KeyboardRow();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        row.addAll(new ArrayList<>(Arrays.asList(btns)));

        if (underKeyboard != null) {
            keyboardRows = underKeyboard.getKeyboard();
        }
        keyboardRows.add(row);

        underKeyboard = new ReplyKeyboardMarkup();
        underKeyboard.setKeyboard(keyboardRows);
        underKeyboard.setResizeKeyboard(true);

        return this;
    }

    public void exe() {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chat_id);
        sendMessage.setText(text);
        sendMessage.enableHtml(true);

        if (underMessage != null) {
            sendMessage.setReplyMarkup(new InlineKeyboardMarkup(underMessage));
        }
        if (underKeyboard != null) {
            underKeyboard.setOneTimeKeyboard(false);
            sendMessage.setReplyMarkup(underKeyboard);
        }

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}