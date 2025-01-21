package upd.dev.bbfp.actions;

import upd.dev.bbfp.actions.run.btns.UnderKeyboard;
import upd.dev.bbfp.actions.run.btns.UnderMessage;

public class Buttons {
    public static UnderMessage createUnderMessage(String label, String callback_date) {
        return new UnderMessage(label, callback_date);
    }
    public static UnderKeyboard createUnderKeyboard(String label) {
        return new UnderKeyboard(label);
    }
}
