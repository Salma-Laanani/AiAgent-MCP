package org.chatbot.chatbotagent.telegram;

import jakarta.annotation.PostConstruct;
import org.chatbot.chatbotagent.agents.AIAgent;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot extends TelegramLongPollingBot {

    private AIAgent aiAgent;

    public TelegramBot(AIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }
    @Override
    public String getBotUsername() {
        return "zouhraai_bot";
    }

    @Override
    public String getBotToken() {
        return "8372191585:AAFuBW5KWAqhR-6oct1bMYKUuT-c9qDe-Rc";
    }


    @Override
    public void onUpdateReceived(Update telegramRequest) {
if (!telegramRequest.hasMessage() ) {
    return;

}

        String message = telegramRequest.getMessage().getText();
String answer=aiAgent.askAgent(message);
senTypingQuestion(telegramRequest.getMessage().getChatId(),answer);
sendTextMessage(telegramRequest.getMessage().getChatId(),answer);
    }

    @PostConstruct
    public void registerBot()  {
        try {
            TelegramBotsApi api=new TelegramBotsApi(DefaultBotSession.class);
                    api.registerBot(this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void    sendTextMessage(long chatId,String message) {
        SendMessage sendMessage=new SendMessage(String.valueOf(chatId),message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void senTypingQuestion(long chatId,String message) {
        SendChatAction sendChatAction=new SendChatAction();
        sendChatAction.setChatId(String.valueOf(chatId));
        sendChatAction.setAction(ActionType.TYPING);
    }

}
