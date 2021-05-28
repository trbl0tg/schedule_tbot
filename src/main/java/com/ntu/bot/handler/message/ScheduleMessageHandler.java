package com.ntu.bot.handler.message;

import com.ntu.CLExcelWorker;
import com.ntu.EXExcelWorker;
import com.ntu.Emoji;
import com.ntu.Utils;
import com.ntu.bot.BotCondition;
import com.ntu.bot.keyboard.ReplyKeyboardMarkupBuilder;
import com.ntu.telegram.ReplyMessageService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.ntu.StringUtils.convertToSlavicLetters;

@Component
public class ScheduleMessageHandler implements MessageHandler {

    private final ReplyMessageService replyMessageService;
    private BotCondition currentBotCondition;
    private String[] curses = new String[]{"1", "2", "3", "4", "5"};
    private List<String> faculties = Arrays.asList("AMF", "CMO", "FEP", "FTIT", "FTB");
    private List<String> groups = Collections.emptyList();
    private int scdType = 0; //1-заняття, 2 -екз.
    private int courseToSearch = 0;
    private CLExcelWorker clExcelWorker = new CLExcelWorker("src/main/resources/rozklad/1-2/fep-1-2.xlsx");
    private EXExcelWorker exExcelWorker = new EXExcelWorker("src/main/resources/rozkladispit/3-4/fep-3-4.xlsx");
    private String fac;

    public ScheduleMessageHandler(ReplyMessageService replyMessageService) {
        this.replyMessageService = replyMessageService;
    }

    @Override
    public boolean canHandle(BotCondition botCondition) {
        currentBotCondition = botCondition;
        return botCondition.equals(BotCondition.SCHEDULE)
                || botCondition.equals(BotCondition.SCHEDULE_COURSE);
    }

    //type(zaniatia||ekz)/speciality(5:FTIT,AVTOMEH...)/kurs/GROUP

    @SneakyThrows
    @Override
    public BotApiMethod<Message> handle(Message message) {


        if (Arrays.asList(curses).contains(message.getText())) { //courses
            courseToSearch = Integer.parseInt(message.getText());
            if (scdType == 1) {
                groups = clExcelWorker.chooseGroupToDisplay(message.getText());
            } else groups = exExcelWorker.chooseGroupToDisplay(message.getText());
            return getFaculty(message.getChatId());
            //            return getSpecialitiesMenu(message.getChatId());
        } else if (faculties.contains(message.getText())) {
            fac = message.getText();
            //load file with schedule
            String filePath = pickFileNameWithSchedule(fac, scdType);
            if (scdType == 1) {
                clExcelWorker = new CLExcelWorker(filePath);
                groups = clExcelWorker.chooseGroupToDisplay(String.valueOf(courseToSearch));
            } else {
                exExcelWorker = new EXExcelWorker(filePath);
                groups = exExcelWorker.chooseGroupToDisplay(String.valueOf(courseToSearch));
            }
            return getSpecialitiesMenu(message.getChatId());
        } else if (groups.contains(message.getText())) { //groups
            String group = message.getText();
            group = convertToSlavicLetters(group);
            String scheduleInfo;
            if (scdType == 1) {
                scheduleInfo = clExcelWorker.getRowsByGroupName(group);

            } else {
                scheduleInfo = exExcelWorker.getRowsByGroupName(group);

            }
            scheduleInfo = scheduleInfo.replaceAll("\n\n\n\n\n\n\n\n", "");
            System.out.println("got schedule: " + scheduleInfo.trim());
            return displaySchedule(message.getChatId(), scheduleInfo.trim());
        } else if (message.getText().equals("Заняття")) {
            scdType = 1;
            return getScheduleMenu(message.getChatId());
        } else if (message.getText().equals("Екзамени")) {
            scdType = 2;
            return getScheduleMenu(message.getChatId());
        }
        return getScheduleType(message.getChatId());
    }

    private String pickFileNameWithSchedule(String fac, int scdType) {
//        /1-заняття, 2 -екз.
        String scTypeString = "";
        if (scdType == 1) {
            scTypeString = "rozklad";
        } else scTypeString = "rozkladispit";

        ArrayList<String> filteredFacs = new ArrayList<>();
        for (String path : new Utils().files.keySet()) {
            if (path.contains(fac.toLowerCase()) && (path.contains(scTypeString))) {
                filteredFacs.add(path);
            }
        }
        for (String facy : filteredFacs) {
            if (courseToSearch == 5) {
                if (facy.contains(String.valueOf(courseToSearch - 1))) {
                    return facy;
                }
            } else if (facy.contains(String.valueOf(courseToSearch))) {
                return facy;
            }
        }

        return "Error while geting faculty :C";
    }

    private BotApiMethod<Message> getFaculty(Long chatId) {
        int cnt = 0;
        ReplyKeyboardMarkupBuilder result = ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("А з якого ти факультету?")
                .row();
        for (String faculty : faculties) {

            if (cnt == 3) {
                cnt = 0;
                result.button(faculty);
                result.endRow();
                result.row();
            } else {
                cnt++;
                result.button(faculty);
            }
        }
        result
                .endRow()
                .row()
                .button("Головне меню")
                .endRow();
        return result.build();
    }

    private BotApiMethod<Message> getScheduleType(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("Тобі потрібен розклад заннять чи єкзаменів?")
                .row()
                .button("Заняття")
                .button("Екзамени")
                .endRow()
                .row()
                .button("Головне меню")
                .endRow().build();
    }

    private BotApiMethod<Message> getSpecialitiesMenu(Long chatId) {
        int cnt = 0;
        ReplyKeyboardMarkupBuilder result = ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("На якій спеціальності?")
                .row();
        for (String group : groups) {
            if (!group.isEmpty()) {
                if (cnt == 3) {
                    cnt = 0;
                    result.button(group);
                    result.endRow();
                    result.row();
                } else {
                    cnt++;
                    result.button(group);
                }
            }
        }
        result
                .endRow()
                .row()
                .button("Головне меню")
                .endRow();
        return result.build();
    }

    private SendMessage getScheduleMenu(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("На який курс потрібен розклад?")
                .row()
                .button("1")
                .button("2")
                .button("3")
                .button("4")
                .button("5")
                .endRow()
                .row()
                .row()
                .button("Головне меню")
                .endRow()
                .build();
    }

    private SendMessage displaySchedule(Long chatId, String schedule) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText(schedule).build();
    }
}
