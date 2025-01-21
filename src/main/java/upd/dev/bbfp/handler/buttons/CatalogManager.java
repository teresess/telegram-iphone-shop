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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogManager implements Execute {

    public CatalogManager() {}

    @Override
    public void exe(Update update) {
        Services services = Controller.getServices();
        String category = update.getCallbackQuery().getData().replace("CAT", "");

        List<Product> products = services.getProductService().getAllProducts();

        Map<String, Integer> sortedProducts = new HashMap<>();

        for (Product product : products) {
            if (product.getProductId().startsWith(category)) {
                if (sortedProducts.containsKey(product.getProductId())) {
                    sortedProducts.put(product.getProductId(), sortedProducts.get(product.getProductId())+1);
                } else {
                    sortedProducts.put(product.getProductId(), 1);
                }
            }
        }

        Edit edit = Actions.createEdit(
                Main.getLoader(), "Наш список %s в наличии:".formatted(category), String.valueOf(update.getCallbackQuery().getMessage().getChatId()), update.getCallbackQuery().getMessage().getMessageId()
        );

        if (!sortedProducts.isEmpty()) {
            sortedProducts.forEach((id, count) -> {
                edit.addRow(Buttons.createUnderMessage(Util.formatProduct(id) + " | %sшт.".formatted(count), "listProduct"+id).build());
            });
        } else {
            edit.addRow(Buttons.createUnderMessage("Нет товара в наличии ⛔️", "staffNotFound").build());
        }

        edit.addRow(Buttons.createUnderMessage("Назад", "catalog").build());
        edit.exe();
    }
}