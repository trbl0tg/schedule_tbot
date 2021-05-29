package com.ntu.bot.handler.message;

import com.ntu.bot.utils.Emoji;
import com.ntu.bot.conditions.BotCondition;
import com.ntu.bot.keyboard.ReplyKeyboardMarkupBuilder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;

@Component
public class DictionaryMessageHandler implements MessageHandler {

    private List<String> dictTypes = Arrays.asList("Адреси", "Платіжні реквізити", "Корисні ресурси");

    @Override
    public boolean canHandle(BotCondition botCondition) {
        return botCondition.equals(BotCondition.DICTIONARY);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {
        if (dictTypes.contains(message.getText())) {
            String msg = message.getText();
            switch (msg) {
                case "Адреси":
                    return getAddressesMenu(message.getChatId());
                case "Платіжні реквізити":
                    return getPaymentsMenu(message.getChatId());
                case "Корисні ресурси":
                    return getResoursesMenu(message.getChatId());
            }
        }
        return getDictionaryTypes(message.getChatId());
    }

    private BotApiMethod<Message> getResoursesMenu(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .row()
                .setText("Список корисних ресурсів:")
                .setText(Emoji.ONE + " Оплата послуг НТУ онлайн: https://next.privat24.ua/payments/form/%7B%22token%22:%220d69ab781432daae5fe9f3f74d659406z8vwysa2%22%7D\n")
                .setText(Emoji.TWO + " Рейтинг успішності: http://www.ntu.edu.ua/rejting-uspishnosti/\n")
                .setText(Emoji.THREE + " Розклад: http://www.ntu.edu.ua/studentam/rozklad/\n")
                .setText(Emoji.FOUR + " Документи для гуртожитку (Внизу): http://www.ntu.edu.ua/studentam/studmistechko/\n")
                .setText(Emoji.FIVE + " Вакансії: http://www.ntu.edu.ua/studentam/vakansii/\n")
                .setText(Emoji.SIX + " Список кафедр: http://www.ntu.edu.ua/pidrozdili/kafedri/\n")
                .endRow()
                .row()
                .button("Назад")
                .endRow()
                .row()
                .button("Головне меню")
                .endRow()
                .build();
    }

    private BotApiMethod<Message> getPaymentsMenu(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("Платіжні реквізити:")
                .setText("(з 01 січня 2020 року)\n")
                .setText(Emoji.ONE + " Оплата за навчання (денна, заочна форма): \nUA858201720313241005201011361 \nЄДРПОУ: 02070915 \n")
                .setText(Emoji.TWO + "Оплата за проживання в гуртожитку, оплата за дипломи,\n" +
                        "студентські квитки та втрачені речі: \nUA868201720313211005202011361 \nЄДРПОУ: 02070915 \n")
                .setText("Банк отримувача:  ДКСУ у м. Києві\n\n")
                .setText("Головний бухгалтер: С.М.Коцюруба\n\n")
                .row()
                .button("Назад")
                .endRow()
                .row()
                .button("Головне меню")
                .endRow()
                .build();
    }

    private BotApiMethod<Message> getAddressesMenu(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("Адреса університету:\n" +
                        "Україна, 01010, м. Київ,\n" +
                        "вул. М. Омеляновича-Павленка, 1\n" +
                        "тел. +38 (044) 280-82-03\n" +
                        "е-mail: general@ntu.edu.ua\n" +
                        "http://www.ntu.edu.ua\n" +
                        "\n" +
                        "Приймальна комісія:\n" +
                        "кімн. 119б,\n" +
                        "тел.+38 (044) 280-54-09\n")
                .setText("Студмістечко: \n")
                .setText("Директор студмістечка – Черній Світлана Вікторівна\n")
                .setText("м. Київ, вул. Кіквідзе, 36-Б, тел. (044) 284-95-20\n")
                .setText("Заступник директора студмістечка по виховній роботі та режиму – Правдюк Аліна Леонідівна\n" +
                        "тел. (044) 286-49-77\n" +
                        "\n")
                .setText("Начальник паспортного столу – Наумова Анна Іллівна\n" +
                        "тел. (044) 286-49-77\n" +
                        "\n")
                .setText("Гуртожиток " + Emoji.ONE + " \n" +
                        "м. Київ, вул. Івана Кудрі, 32\n" +
                        "тел. (044) 285-57-47\n" +
                        "Зав. гуртожитком – Ткаченко Валентина Володимирівна\n" +
                        "\n")
                .setText("Гуртожиток " + Emoji.TWO + "\n" +
                        "м. Київ, вул. Кіквідзе, 39\n" +
                        "тел. (044) 285-51-21\n" +
                        "Зав. гуртожитком – Васильєва Євгенія Іванівна\n" +
                        "\n")
                .setText("Гуртожиток " + Emoji.THREE + "\n" +
                        "м. Київ, вул. Кіквідзе, 36\n" +
                        "тел. (044) 285-34-90\n" +
                        "Зав. гуртожитком – Тарнавська Ірина Юріївна\n" +
                        "\n")
                .setText("Гуртожиток " + Emoji.FOUR + "\n" +
                        "м. Київ, вул. Кіквідзе, 38-А\n" +
                        "тел. (044) 285-47-55\n" +
                        "Т.в.о. зав. гуртожитком – Правдюк Аліна Леонідівна\n" +
                        "\n")
                .setText("Гуртожиток " + Emoji.FIVE + "\n" +
                        "м. Київ, вул. Кіквідзе,40-А\n" +
                        "тел. (044) 285-46-83\n" +
                        "Зав. гуртожитком – Дутова Людмила Леонидівна.\n" +
                        "\n")
                .row()
                .button("Назад")
                .endRow()
                .row()
                .button("Головне меню")
                .endRow()
                .build();
    }

    private BotApiMethod<Message> getDictionaryTypes(Long chatId) {
        return ReplyKeyboardMarkupBuilder.create(chatId)
                .setText("Якого типу інформацію ви шукаєте?")
                .row()
                .button("Адреси")
                .endRow()
                .row()
                .button("Платіжні реквізити")
                .endRow()
                .row()
                .button("Корисні ресурси")
                .endRow()
                .row()
                .button("Головне меню")
                .endRow()
                .build();

    }
}
