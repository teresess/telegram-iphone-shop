package upd.dev.bbfp.logistic;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static String formatRole(String role) {
        Map<String, String> roles = new HashMap<>();
        roles.put("default", "Покупатель");
        roles.put("admin", "Админ");
        return roles.get(role);
    }

    public static String formatProduct(String productId) {
        Map<String, String> products = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            if (i == 10) {
                products.put("iphone"+i, "iPhone X");
                continue;
            }
            products.put("iphone"+i, "iPhone "+i);
        }
        for (int i = 0; i < 5; i++) {
            products.put("airpods"+i, "AirPods "+i);
        }
        for (int i = 0; i < 5; i++) {
            products.put("applewathch"+i, "Apple Watch "+i);
        }
        for (int i = 0; i < 5; i++) {
            products.put("ipad"+i, "iPad "+i);
        }
        products.put("dyson", "Dyson");
        products.put("ps5", "PlayStation 5");

        return products.getOrDefault(productId, productId);
    }

    public static String formatUsed(boolean used) {
        if (used) {
            return "Б/У";
        } else return "Новый";
    }
}
