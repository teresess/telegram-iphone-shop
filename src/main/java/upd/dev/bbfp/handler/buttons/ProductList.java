package upd.dev.bbfp.handler.buttons;

import org.telegram.telegrambots.meta.api.objects.Update;
import upd.dev.bbfp.Main;
import upd.dev.bbfp.actions.Actions;
import upd.dev.bbfp.actions.Buttons;
import upd.dev.bbfp.actions.run.events.Edit;
import upd.dev.bbfp.handler.Execute;
import upd.dev.bbfp.logistic.Util;
import upd.dev.bbfp.logistic.controllers.Controller;
import upd.dev.bbfp.logistic.controllers.Services;
import upd.dev.bbfp.logistic.model.Product;

import java.util.List;

public class ProductList implements Execute {

    public ProductList() {}
    @Override
    public void exe(Update update) {
        Services services = Controller.getServices();
        String product = update.getCallbackQuery().getData().replace("listProduct", "");

        List<Product> products = services.getProductService().getAllProducts();
        Edit edit = Actions.createEdit(
                Main.getLoader(), "Доступно:", String.valueOf(update.getCallbackQuery().getMessage().getChatId()), update.getCallbackQuery().getMessage().getMessageId()
        );

        for (Product pr : products) {
            if (pr.getProductId().equals(product)) {
                edit.addRow(Buttons.createUnderMessage("%s %s %s %s"
                        .formatted(
                                Util.formatProduct(pr.getProductId()), pr.getProductModel(), pr.getColor(), Util.formatUsed(pr.isUsed())
                        ), "product"+pr.getId()).build());
            }
        }
        edit.addRow(Buttons.createUnderMessage("Назад", "catalog").build()).exe();
    }
}