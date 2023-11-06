package by.krainet.management.api.handler;

import java.time.ZonedDateTime;

public record ErrorResponse(int statusCode, String message, ZonedDateTime timeStamp) {
}
