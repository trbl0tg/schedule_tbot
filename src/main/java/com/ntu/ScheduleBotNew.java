//package com.ntu;
//
//import com.ntu.commands.StartCommand;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.User;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ScheduleBotNew extends TelegramLongPollingCommandBot {
//
//
////    private final NonCommand nonCommand;
//
//    public ScheduleBotNew() {
//        super();
//        //создаём вспомогательный класс для работы с сообщениями, не являющимися командами
////        this.nonCommand = new NonCommand();
//        //регистрируем команды
//        register(new StartCommand("start", "Старт"));
//
//    }
//
//
//    @Override
//    public String getBotToken() {
//        return "1250921121:AAG6OXg-V-a-OiXxRwrsbOadWoFlFkPxoVY";
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "ntu_schedule_bot";
//    }
//
//    @Override
//    public void processNonCommandUpdate(Update update) {
//        Message msg = update.getMessage();
//        Long chatId = msg.getChatId();
//        String userName = getUserName(msg);
//
////        String answer = nonCommand.nonCommandExecute(chatId, userName, msg.getText());
//        setAnswer(chatId, userName, "answer");
//    }
//
//    /**
//     * Формирование имени пользователя
//     * @param msg сообщение
//     */
//    private String getUserName(Message msg) {
//        User user = msg.getFrom();
//        String userName = user.getUserName();
//        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
//    }
//
//    /**
//     * Отправка ответа
//     * @param chatId id чата
//     * @param userName имя пользователя
//     * @param text текст ответа
//     */
//    private void setAnswer(Long chatId, String userName, String text) {
//        SendMessage answer = new SendMessage();
//        answer.setText(text);
//        answer.setChatId(chatId.toString());
//        try {
//            execute(answer);
//        } catch (TelegramApiException e) {
//            //логируем сбой Telegram Bot API, используя userName
//        }
//    }
//}
