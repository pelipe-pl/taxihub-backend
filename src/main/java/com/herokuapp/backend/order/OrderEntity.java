package com.herokuapp.backend.order;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Order", catalog = "taxihub")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long driver_id;
    private Long client_id;
    private OrderStatus status;
    private String from;
    private String to;
    private LocalDateTime start_date;
    private LocalDateTime end_time;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Long driver_id, Long client_id, OrderStatus status, String from, String to, LocalDateTime start_date, LocalDateTime end_time) {
        this.id = id;
        this.driver_id = driver_id;
        this.client_id = client_id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.start_date = start_date;
        this.end_time = end_time;
    }

    public OrderEntity(Long driver_id, Long client_id, OrderStatus status, String from, String to, LocalDateTime start_date, LocalDateTime end_time) {
        this.driver_id = driver_id;
        this.client_id = client_id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.start_date = start_date;
        this.end_time = end_time;
    }

    public Long getId() {
        return id;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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




