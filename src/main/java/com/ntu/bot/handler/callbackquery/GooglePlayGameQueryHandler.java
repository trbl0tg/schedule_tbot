package com.ntu.bot.handler.callbackquery;

import com.ntu.telegram.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;


import java.io.Serializable;


/**
 * Returns information in different forms: pop-up notification, text message.
 */
@Slf4j
@Component
public class GooglePlayGameQueryHandler implements CallbackQueryHandler {

//    private final GooglePlayGameService googlePlayGameService;

    private final ReplyMessageService replyMessageService;

    public GooglePlayGameQueryHandler(
                                      ReplyMessageService replyMessageService) {
        this.replyMessageService = replyMessageService;
    }

    public PartialBotApiMethod<? extends Serializable> handleCallbackQuery(CallbackQuery callbackQuery) {
        String callBackData = callbackQuery.getData();
        String callBackId = callbackQuery.getId();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        Long chatId = callbackQuery.getMessage().getChatId();

        String title = callBackData.substring(callBackData.indexOf(' ') + 1); //<skip callback data name and extract game title
//        GooglePlayGame game;

        //            game = googlePlayGameService.getGameByTitle(title);

        switch (callBackData.split("\\s+")[0]) {
            case "/price":
                return replyMessageService.getPopUpAnswer(callBackId, "text");

            default:
                return replyMessageService.getTextMessage(chatId, "Не удалось получить информацию об игре.");
        }

    }
}
