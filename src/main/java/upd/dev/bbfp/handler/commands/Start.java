package upd.dev.bbfp.handler.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import upd.dev.bbfp.Loader;
import upd.dev.bbfp.Main;
import upd.dev.bbfp.actions.Actions;
import upd.dev.bbfp.actions.Buttons;
import upd.dev.bbfp.actions.run.events.Edit;
import upd.dev.bbfp.actions.run.events.Send;
import upd.dev.bbfp.handler.Execute;
import upd.dev.bbfp.logistic.Util;
import upd.dev.bbfp.logistic.controllers.Controller;
import upd.dev.bbfp.logistic.controllers.Services;
import upd.dev.bbfp.logistic.model.User;

public class Start implements Execute {


    public Start() {}
    @Override
    public void exe(Update update) {
        Services services = Controller.getServices();
        Loader loader = Main.getLoader();
        String CATALOG = "Каталог 🛒", STOCK = "Акции 〽️", ADDRESS = "Наш адрес 📍", REVIEW = "Отзывы 📫", ADMIN = "Админ панель"
                , MESSAGE = "Добро пожаловать в наш магазин Apple техники, ознакомьтесь с нашим асортиментом ниже:";

        if (update.hasMessage()) {
            Long id = update.getMessage().getChatId();
            String text;

            if (services.getUserService().getUser(id) == null) {
                services.getUserService().regUser(new User(id, "default", 0L));
                text = "Вы успешно зарегистрировались в системе.";
            } else {
                text = "Вы успешно вошли в систему.";
            }

            String role = Util.formatRole(services.getUserService().getUser(id).getRole());

            Actions.createSend(
                            loader, text, String.valueOf(id))
                    .addRow(
                            Buttons.createUnderKeyboard("Роль: " + role).build()
                    ).exe();
            Send send = Actions.createSend(
                            loader, MESSAGE, String.valueOf(id))
                    .addRow(
                            Buttons.createUnderMessage(CATALOG, "catalog").build(),
                            Buttons.createUnderMessage(STOCK, "stock").build())
                    .addRow(
                            Buttons.createUnderMessage(ADDRESS, "address").build(),
                            Buttons.createUnderMessage(REVIEW, "review").build());

            if (services.getUserService().isAdmin(id)) {
                send.addRow(Buttons.createUnderMessage(ADMIN, "adminPanel").build());
            }
            send.exe();
        } else {
            Long id = update.getCallbackQuery().getMessage().getChatId();

            if (services.getUserService().getUser(id) == null) services.getUserService().regUser(new User(id, "default", 0L));

            Edit edit = Actions.createEdit(
                            loader, MESSAGE, String.valueOf(id)
                            , update.getCallbackQuery().getMessage().getMessageId())
                    .addRow(
                            Buttons.createUnderMessage(CATALOG, "catalog").build(),
                            Buttons.createUnderMessage(STOCK, "stock").build())
                    .addRow(
                            Buttons.createUnderMessage(ADDRESS, "address").build(),
                            Buttons.createUnderMessage(REVIEW, "review").build());
            if (services.getUserService().isAdmin(id)) {
                edit.addRow(Buttons.createUnderMessage(ADMIN, "adminPanel").build());
            }
            edit.exe();
        }
    }
}