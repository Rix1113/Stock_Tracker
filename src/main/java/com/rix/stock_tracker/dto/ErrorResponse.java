package com.rix.stock_tracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponse(@JsonFormat(pattern = "'date:' dd-MM-yyyy 'time:' HH:mm:ss") LocalDateTime timestamp, String genericMessage,
                            String detailMessage, int responseStatus, String path) {
}
