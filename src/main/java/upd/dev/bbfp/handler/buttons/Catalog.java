package upd.dev.bbfp.handler.buttons;

import org.telegram.telegrambots.meta.api.objects.Update;
import upd.dev.bbfp.Main;
import upd.dev.bbfp.actions.Actions;
import upd.dev.bbfp.actions.Buttons;
import upd.dev.bbfp.handler.Execute;

public class Catalog implements Execute {

    public Catalog() {}
    @Override
    public void exe(Update update) {
        Actions.createEdit(
                Main.getLoader(), "Выберете товар из каталога:",
                String.valueOf(update.getCallbackQuery().getMessage().getChatId()),
                update.getCallbackQuery().getMessage().getMessageId()
        ).addRow(
                Buttons.createUnderMessage("iPhone 📱", "CATiphone").build(),
                Buttons.createUnderMessage("AirPods 📞", "CATairpods").build()
        ).addRow(
                Buttons.createUnderMessage("Apple Watch ⌚", "CATapplewatch").build(),
                Buttons.createUnderMessage("iPad 📱", "CATipad").build()
        ).addRow(
                Buttons.createUnderMessage("MacBook 💻", "CATmacbook").build(),
                Buttons.createUnderMessage("Dyson 🌫", "CATdyson").build()
        ).addRow(
                Buttons.createUnderMessage("PlayStation 5 🎮", "CATps5").build()
        ).addRow(
                Buttons.createUnderMessage("Назад", "backToStart").build()
        ).exe();
    }
}
