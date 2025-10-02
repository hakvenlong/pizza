package com.setec.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.setec.entities.Booked;

import lombok.Data;

@Service
@Data
public class MyTelegramBot {
  
  @Value("${token}")
  private String token;

  @Value("${chat_id}")
  private long chat_id;
  
  private TelegramBot bot;
  
  public SendResponse message(Booked booked) {
    if (bot == null) {
      bot = new TelegramBot(token);
    }

    String formattedMessage =
        "*Customer Details :*\n" +
        "👤 *Name:* " + booked.getName() + "\n" +
        "📞 *Phone:* " + booked.getPhoneNumber() + "\n" +
        "✉️ *Email:* " + booked.getEmail() + "\n" +
        "📅 *Date:* " + booked.getDate() + "\n" +
        "⌚️ *Time:* " + booked.getTime() + "\n" +
        "👥 *Number of People:* " + booked.getPerson();

    SendResponse sendMessage = bot.execute(
        new SendMessage(chat_id, formattedMessage)
            .parseMode(ParseMode.Markdown)
    );

    return sendMessage;
  }
}
