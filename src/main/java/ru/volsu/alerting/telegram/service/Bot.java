package ru.volsu.alerting.telegram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.volsu.alerting.user.model.User;
import ru.volsu.alerting.user.model.UserGroup;
import ru.volsu.alerting.user.service.UserService;

public class Bot extends SpringWebhookBot {

    private String botToken;

    private String botUsername;

    private String webHookPath;

    @Autowired
    private UserService userService;

    private final Logger log = LoggerFactory.getLogger(Bot.class);

    public Bot(SetWebhook setWebhookInstance, String webHookPath, String botUsername, String botToken) {
        super(setWebhookInstance);
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.webHookPath = webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        Message message = update.getMessage();

        String text = message.getText();
        if (text.contains("Подписка:")) {
            UserGroup userGroup;
            try {
                userGroup = userService.getUserGroupByName(text.split(":")[1]);
            } catch (Exception e) {
                String error = String.format("Ошибка при подписки пользователя: %s", e.getMessage());
                log.error(error);
                return SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text(error)
                        .build();
            }
            org.telegram.telegrambots.meta.api.objects.User from = message.getFrom();

            User user = new User();
            user.setUserGroup(userGroup);
            user.setTelegramUsername(from.getUserName());
            user.setChatId(message.getChatId().toString());
            user.setFirstname(from.getFirstName());
            user.setLastname(from.getLastName());

            try {
                userService.create(user);
            } catch (Exception e) {
                String error = String.format("Ошибка при подписки пользователя: %s", e.getMessage());
                log.error(error);
                return SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text(error)
                        .build();
            }

            return SendMessage.builder()
                    .chatId(message.getChatId().toString())
                    .text("Подписка прошла успешно")
                    .build();
        }

        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text("Для подписки на рассылку напишите \"Подписка:название группы пользователей\"")
                .build();
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
