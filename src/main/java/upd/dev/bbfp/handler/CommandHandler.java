package upd.dev.bbfp.handler;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.bbfp.Config;
import upd.dev.bbfp.Loader;
import upd.dev.bbfp.handler.commands.Start;
import upd.dev.bbfp.handler.events.CommandEvent;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements CommandEvent {

    @Getter
    public static Map<String, Execute> cmds = new HashMap<>();
    int counter = 0;
    Loader bot;
    public CommandHandler(Loader bot) {
        this.bot = bot;
        Config config = bot.loadEnv();

        regCmd("start", new Start());

        System.out.printf("CommandHandler: load %s commands\n", counter);
    }
    @Override
    public void onCommandEvent(Update update) throws TelegramApiException {
        String cmd = update.getMessage().getText().replace("/", ""),
                userId = String.valueOf(update.getMessage().getFrom().getId());

        if(cmds.containsKey(cmd)) {
            cmds.get(cmd).exe(update);
        }
    }
    void regCmd(String name, Execute event) {
        cmds.put(name, event);
        counter++;
    }
}