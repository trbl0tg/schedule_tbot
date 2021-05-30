package com.ntu.bot.handler.message;

import com.google.api.services.classroom.model.Course;
import com.ntu.bot.conditions.BotCondition;
import com.ntu.bot.keyboard.ReplyKeyboardMarkupBuilder;
import com.ntu.bot.service.GoogleClassroomService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@AllArgsConstructor
@Component
public class ClassRoomMessageHandler implements MessageHandler{

    private final GoogleClassroomService googleClassroomService;

    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.CLASSROOM);
    }

    @SneakyThrows
    @Override
    public BotApiMethod<Message> handle(Message message) {
        if (message.getText().equals("Мої курси")){
            return getMyCurses(message.getChatId());
        }
        return getClassroomActions(message.getChatId());
    }

    private BotApiMethod<Message> getMyCurses(Long chatId) throws GeneralSecurityException, IOException {
        List<Course> clientCourses = GoogleClassroomService.getClientCourses(50);

        ReplyKeyboardMarkupBuilder response = ReplyKeyboardMarkupBuilder.create(chatId);
        for (Course clientCours : clientCourses) {
            Object classUrl = clientCours.get("alternateLink");
            response.setText(clientCours.getName() + ": "+classUrl + "\n");

        }
        return response
                .build();
    }

    private BotApiMethod<Message> getClassroomActions(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("З чим тобі допомогти?")
                .row()
                .button("Мої курси")
                .endRow()
                .row()
                .button("Головне меню")
                .endRow().build();
    }
}
