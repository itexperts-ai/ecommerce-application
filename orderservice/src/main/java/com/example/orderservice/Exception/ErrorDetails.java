package com.example.orderservice.Exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime localDateTime;
    private int status;
    private String message;
    private String path;

    public ErrorDetails(int status, String message, String path) {
        this.localDateTime = localDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
