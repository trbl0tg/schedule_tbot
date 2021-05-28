package com.ntu.bot.handler.message;

import com.ntu.bot.BotCondition;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handles incoming {@link Message} by current bot condition.
 */
public interface MessageHandler {

    boolean canHandle(BotCondition botCondition);

    BotApiMethod<Message> handle(Message message);

}
