package ru.volsu.alerting.telegram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.volsu.alerting.telegram.dto.SendMessageRequest;
import ru.volsu.alerting.telegram.service.SendMessageService;

@RestController
@RequestMapping(value = "/api/bot")
public class TelegramBotController {

    @Autowired
    private SendMessageService sendMessageService;

    @PostMapping
    public void sendMessage(@RequestBody SendMessageRequest sendMessageRequest) {
        sendMessageService.send(sendMessageRequest);
    }
}
