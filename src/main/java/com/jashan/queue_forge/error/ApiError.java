package com.jashan.queue_forge.error;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;


public class ApiError {

    private LocalDateTime timestamp;
    private String error;
    private HttpStatus status;

    public ApiError(){
        this.timestamp=LocalDateTime.now();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ApiError(String error, HttpStatus status){
        this();
        this.error=error;
        this.status=status;
    }
}
