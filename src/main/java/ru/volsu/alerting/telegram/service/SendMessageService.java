package ru.volsu.alerting.telegram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.volsu.alerting.telegram.dao.MessageHistoryRepository;
import ru.volsu.alerting.telegram.dao.MessageRepository;
import ru.volsu.alerting.telegram.dto.MessageDTO;
import ru.volsu.alerting.telegram.dto.SendMessageRequest;
import ru.volsu.alerting.telegram.model.Message;
import ru.volsu.alerting.telegram.model.MessageHistory;
import ru.volsu.alerting.user.model.User;
import ru.volsu.alerting.user.service.UserService;

import java.util.List;

@Service
public class SendMessageService {

    @Autowired
    private AbsSender botSender;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageHistoryRepository messageHistoryRepository;

    @Autowired
    private UserService userService;

    private final Logger log = LoggerFactory.getLogger(SendMessageService.class);

    public void send(SendMessageRequest sendMessageRequest) {
        String userGroupName = sendMessageRequest.getUserGroupName();
        List<User> users = userService.getUsersByUserGroupName(userGroupName);

        MessageDTO messageDTO = sendMessageRequest.getMessage();
        Message message = messageRepository.save(messageDTO.toEntity());

        String text = message.toTelegramMessage();
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder()
                .text(text);
        for (User user : users) {
            sendMessageBuilder.chatId(user.getChatId());
            try {
                botSender.execute(sendMessageBuilder.build());
            } catch (TelegramApiException e) {
                log.error("При отправке сообщения пользователю: {} произошла ошибка: {}", user.getTelegramUsername(), e.getMessage());
            }
        }

        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setMessage(message);
        messageHistory.setUserGroup(userService.getUserGroupByName(userGroupName));
        messageHistoryRepository.save(messageHistory);
    }
}
