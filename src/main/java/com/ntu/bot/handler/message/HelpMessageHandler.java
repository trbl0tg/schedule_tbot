package com.ntu.bot.handler.message;

import com.ntu.bot.conditions.BotCondition;
import com.ntu.telegram.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handles {@link Message} when {@link BotCondition} is {@link BotCondition#HELP}.
 * <p>
 * Informs how the bot works.
 */
@Component
public class HelpMessageHandler implements MessageHandler {

    private final ReplyMessageService replyMessageService;

    public HelpMessageHandler(ReplyMessageService replyMessageService) {
        this.replyMessageService = replyMessageService;
    }

    @Override
    public SendMessage handle(Message message) {
        Long chatId = message.getChatId();
        return replyMessageService.getTextMessage(chatId,
                "Щоб зкористатися ботом просто натискайте кнопки внизу екрану.\n" +
                        "При виникненні труднощей, або проблем з роботою боту, можете звернутися до розробника: @trb_dev.\n" +
                        "GIT: https://github.com/trbl0tg/schedule_tbot"
        +"\n\n NTU 2021, Kyiv");
    }

    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.HELP);
    }

}