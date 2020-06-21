package com.ntu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.print.Pageable;
import java.io.IOException;

import static com.ntu.Status.CHOSEN_GROUP;
import static com.ntu.Status.WORKING;

public class ScheduleBot extends TelegramLongPollingBot {

    private Status status = Status.NONE;
    private String groups = "";

    @Override
    public String getBotToken() {
        return "1250921121:AAG6OXg-V-a-OiXxRwrsbOadWoFlFkPxoVY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();


                if (inMessage.getText().equals("/rozklad") || inMessage.getText().equals("/start")){
                    sendMsg(inMessage, "Укажіть свій курс: ");
                    status = WORKING;
                    return;
                } else if (status == WORKING) {
                    String kurs = inMessage.getText();
                    System.out.println("kurs: " + kurs);
                    groups = ExcelWorker.chooseGroupToDisplay(kurs);
                    sendMsg(inMessage, groups);
                    status = CHOSEN_GROUP;
                    return;
                } if (status == CHOSEN_GROUP){
                    String group = inMessage.getText();
                    System.out.println("group: " + group);

                    if (!groups.contains(group)) {
                        sendMsg(inMessage, "Обрано неіснуючу групу, або ви допустили помилку.");
                        status = Status.NONE;
                        return;
                    }

                    String resp = "Розклад: ";
                    String scheduleInfo = ExcelWorker.getRowsByGroupName(group);
                    resp+= "\n" + scheduleInfo.trim();
                    sendMsg(inMessage, resp);
                    status = Status.NONE;
                    return;
                } else {
                    status = Status.NONE;
                }
            }
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message msg, String text) throws TelegramApiException {
        SendMessage outMessage = new SendMessage();
        outMessage.setChatId(msg.getChatId());
        outMessage.setText(text);
        execute(outMessage);
    }

    @Override
    public String getBotUsername() {
        return "ntu_schedule_bot";
    }
}
