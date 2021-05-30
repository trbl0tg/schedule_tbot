package com.ntu.bot.handler.message;

import com.ntu.bot.utils.Emoji;
import com.ntu.bot.conditions.BotCondition;
import com.ntu.bot.keyboard.ReplyKeyboardMarkupBuilder;
import com.ntu.telegram.ReplyMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handles {@link Message} when {@link BotCondition} is {@link BotCondition#MAIN_MENU}.
 *
 * Sends reply keyboard with main menu to interact with it.
 */
@Component
public class StartMessageHandler implements MessageHandler {

    private final ReplyMessageService replyMessageService;

    public StartMessageHandler(ReplyMessageService replyMessageService) {
        this.replyMessageService = replyMessageService;
    }

    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.MAIN_MENU);
    }

    @Override
    public SendMessage handle(Message message) {
                return getMainMenu(message.getChatId());
    }

    private SendMessage getMainMenu(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("Привіт! "
                        + "\n\nЩоб почати користуватися функціоналом, натискай на потрібну кнопку внизу. "
                        + Emoji.MENU)
                .row()
                .button("Classroom")
                .button("Розклад")
                .endRow()
                .row()
                .button("Довідник")
                .button("Допомога")
                .endRow()
                .build();
    }

}