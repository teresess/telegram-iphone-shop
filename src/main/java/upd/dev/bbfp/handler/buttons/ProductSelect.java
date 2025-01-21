package upd.dev.bbfp.handler.buttons;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.bbfp.Main;
import upd.dev.bbfp.actions.Actions;
import upd.dev.bbfp.actions.Buttons;
import upd.dev.bbfp.handler.Execute;
import upd.dev.bbfp.logistic.Util;
import upd.dev.bbfp.logistic.controllers.Controller;
import upd.dev.bbfp.logistic.controllers.Services;
import upd.dev.bbfp.logistic.model.Product;

public class ProductSelect implements Execute {
    public ProductSelect() {}

    @Override
    public void exe(Update update) throws TelegramApiException {
        Services services = Controller.getServices();
        String productId = update.getCallbackQuery().getData().replace("product", "");

        Product p = services.getProductService().getProduct(Long.valueOf(productId));

        Actions.createEdit(
                Main.getLoader()
                , "<strong>%s %s %s %s</strong>\n\n<strong>ID товара: </strong>%s\n<strong>Цена: </strong>%sр\n<strong>Описание:</strong> %s"
                        .formatted(
                                Util.formatProduct(p.getProductId()),
                                p.getProductModel(),
                                p.getColor(),
                                Util.formatUsed(p.isUsed()),
                                p.getId(),
                                p.getPrice(),
                                p.getProductBio())
                , String.valueOf(update.getCallbackQuery().getMessage().getChatId()), update.getCallbackQuery().getMessage().getMessageId()
        ).addRow(Buttons.createUnderMessage("Назад", "catalog").build()).exe();
    }
}
