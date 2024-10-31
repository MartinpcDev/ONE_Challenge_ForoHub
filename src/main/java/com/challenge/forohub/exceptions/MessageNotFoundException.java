package com.challenge.forohub.exceptions;

public class MessageNotFoundException extends RuntimeException {

  public MessageNotFoundException(String message) {
    super(message);
  }
}
