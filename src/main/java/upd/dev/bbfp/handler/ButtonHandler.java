package upd.dev.bbfp.handler;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.bbfp.Loader;
import upd.dev.bbfp.handler.buttons.Catalog;
import upd.dev.bbfp.handler.buttons.CatalogManager;
import upd.dev.bbfp.handler.buttons.ProductList;
import upd.dev.bbfp.handler.buttons.ProductSelect;
import upd.dev.bbfp.handler.commands.Start;
import upd.dev.bbfp.handler.events.ButtonEvent;

import java.util.HashMap;
import java.util.Map;

public class ButtonHandler implements ButtonEvent {
    Map<String, Execute> btns = new HashMap<>();
    int counter = 0;
    public ButtonHandler(Loader bot) {

        regBtn("catalog", new Catalog());
        regBtn("CAT", new CatalogManager());
        regBtn("listProduct", new ProductList());
        regBtn("backToStart", new Start());
        regBtn("product", new ProductSelect());

        System.out.printf("ButtonHandler: load %s buttons\n", counter);
    }
    @Override
    public void onButtonEvent(Update update) throws TelegramApiException {
        String btnId = update.getCallbackQuery().getData();

        if (btns.containsKey(btnId)) btns.get(btnId).exe(update);

        if (btnId.startsWith("CAT")) btns.get("CAT").exe(update);
        if (btnId.startsWith("listProduct")) btns.get("listProduct").exe(update);
        if (btnId.startsWith("product")) btns.get("product").exe(update);
    }
    void regBtn(String name, Execute event) {
        btns.put(name, event);
        counter++;
    }
}
