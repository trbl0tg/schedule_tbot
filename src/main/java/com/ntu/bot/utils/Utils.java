package com.ntu.bot.utils;

import com.ntu.bot.keyboard.ReplyKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public Map<String, String> files = new HashMap<>();

    public Utils() {
        files.put("src/main/resources/rozkladispit/1-2/amf-1-2.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/amf-1-2.xlsx");
        files.put("src/main/resources/rozkladispit/1-2/ftb-1-2.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/ftb-1-2.xlsx");
        files.put("src/main/resources/rozkladispit/1-2/fmlt-1-2.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/fmlt-1-2.xlsx");
        files.put("src/main/resources/rozkladispit/1-2/fep-1-2.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/fep-1-2.xlsx");
        files.put("src/main/resources/rozkladispit/1-2/ftit-1-2.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/ftit-1-2.xlsx");
//        3-4 курс
        files.put("src/main/resources/rozkladispit/3-4/amf-3-4.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/amf-3-4.xlsx");
        files.put("src/main/resources/rozkladispit/3-4/ftb-3-4.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/ftb-3-4.xlsx");
        files.put("src/main/resources/rozkladispit/3-4/fmlt-3-4.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/fmlt-3-4.xlsx");
        files.put("src/main/resources/rozkladispit/3-4/fep-3-4.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/fep-3-4.xlsx");
        files.put("src/main/resources/rozkladispit/3-4/ftit-3-4.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/ftit-3-4.xlsx");
        files.put("src/main/resources/rozkladispit/2-4/cmo-2-4.xlsx", "http://vstup.ntu.edu.ua/rozkladispit/cmo-2-4.xlsx");

//        Розклад занять на 2 семестр 2020-21 н.р.
//        1-2 курс
        files.put("src/main/resources/rozklad/1-2/amf-1-2.xlsx", "http://vstup.ntu.edu.ua/rozklad/amf-1-2.xlsx");
        files.put("src/main/resources/rozklad/1-2/ftb-1-2.xlsx", "http://vstup.ntu.edu.ua/rozklad/ftb-1-2.xlsx");
        files.put("src/main/resources/rozklad/1-2/fmlt-1-2.xlsx", "http://vstup.ntu.edu.ua/rozklad/fmlt-1-2.xlsx");
        files.put("src/main/resources/rozklad/1-2/fep-1-2.xlsx", "http://vstup.ntu.edu.ua/rozklad/fep-1-2.xlsx");
        files.put("src/main/resources/rozklad/1-2/ftit-1-2.xlsx", "http://vstup.ntu.edu.ua/rozklad/ftit-1-2.xlsx");
//        3-4 курс
        files.put("src/main/resources/rozklad/3-4/amf-3-4.xlsx", "http://vstup.ntu.edu.ua/rozklad/amf-3-4.xlsx");
        files.put("src/main/resources/rozklad/3-4/ftb-3-4.xlsx", "http://vstup.ntu.edu.ua/rozklad/ftb-3-4.xlsx");
        files.put("src/main/resources/rozklad/3-4/fmlt-3-4.xlsx", "http://vstup.ntu.edu.ua/rozklad/fmlt-3-4.xlsx");
        files.put("src/main/resources/rozklad/3-4/fep-3-4.xlsx", "http://vstup.ntu.edu.ua/rozklad/fep-3-4.xlsx");
        files.put("src/main/resources/rozklad/3-4/ftit-3-4.xlsx", "http://vstup.ntu.edu.ua/rozklad/ftit-3-4.xlsx");
        files.put("src/main/resources/rozklad/2-4/cmo-2-4.xlsx", "http://vstup.ntu.edu.ua/rozklad/cmo-2-4.xlsx");

        files.put("src/main/resources/dyst/4/isp-ftit-4.xlsx", "http://vstup.ntu.edu.ua/rozklad/dyst/isp-ftit-4.xlsx");
    }

    /**
     * Формирование имени пользователя
     *
     * @param msg сообщение
     */
    public static String getUserName(Message msg) {
        return getUserName(msg.getFrom());
    }

    /**
     * Формирование имени пользователя. Если заполнен никнейм, используем его. Если нет - используем фамилию и имя
     *
     * @param user пользователь
     */
    public static String getUserName(User user) {
        return (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    public static ReplyKeyboardMarkupBuilder getHomeButton(ReplyKeyboardMarkupBuilder func) {
        return func
                .row()
                .button("Головне меню")
                .endRow();
    }
}
