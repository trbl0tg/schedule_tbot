package com.ntu.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Retrieves information from complex {@link Update} object by merging existing methods.
 */
public interface TelegramUpdateExtractor {

    Long getChatId(Update update);

    String getUserName(Update update);

    Integer getUserId(Update update);

    Integer getMessageId(Update update);

    String getUserLanguage(Update update);

}
