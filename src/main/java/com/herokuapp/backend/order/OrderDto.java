package com.herokuapp.backend.order;

import java.time.LocalDateTime;

public class OrderDto {

    private Long id;
    private Long driver_id;
    private Long client_id;
    private String status;
    private String fromm;
    private String to;
    private LocalDateTime start_date;
    private LocalDateTime end_time;

    public OrderDto(Long driver_id, Long client_id,
                       String status, String fromm, String to) {
        this.driver_id = driver_id;
        this.client_id = client_id;
        this.status = status;
        this.fromm = fromm;
        this.to = to;
//        this.start_date = start_date;

    }

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromm() {
        return fromm;
    }

    public void setFrom(String fromm) {
        this.fromm = fromm;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }
}
