package com.ntu;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) throws TelegramApiRequestException {

        System.out.println("Bot starting...");
        new FileReader();

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(new ScheduleBot());
        System.out.println("Bot started!");
    }
}
