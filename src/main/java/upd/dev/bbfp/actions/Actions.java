package upd.dev.bbfp.actions;

import upd.dev.bbfp.Loader;
import upd.dev.bbfp.actions.run.events.Edit;
import upd.dev.bbfp.actions.run.events.Send;

public class Actions {
    public static Send createSend(Loader bot, String text, String chat_id) {
        return new Send(bot, text, chat_id);
    }
    public static Edit createEdit(Loader bot, String text, String chat_id, int message_id) {
        return new Edit(bot, text, chat_id, message_id);
    }
}
