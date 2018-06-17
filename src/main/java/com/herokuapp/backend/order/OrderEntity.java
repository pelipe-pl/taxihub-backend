package com.herokuapp.backend.order;

import com.herokuapp.backend.client.ClientEntity;
import com.herokuapp.backend.driver.DriverEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordertable")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.OPEN;

    @NotNull
    private double fromLatitude;

    @NotNull
    private double fromLongitude;

    @NotNull
    private double toLatitude;

    @NotNull
    private double toLongitude;

    private LocalDateTime openTime = LocalDateTime.now();
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public OrderEntity() {
    }

    public OrderEntity(DriverEntity driver, ClientEntity client, OrderStatus status, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, LocalDateTime openTime, LocalDateTime startTime, LocalDateTime endTime) {
        this.driver = driver;
        this.client = client;
        this.status = status;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.openTime = openTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OrderEntity(ClientEntity client, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
        this.client = client;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
    }

    public Long getId() {
        return id;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(double fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public double getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(double fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public double getToLatitude() {
        return toLatitude;
    }

    public void setToLatitude(double toLatitude) {
        this.toLatitude = toLatitude;
    }

    public double getToLongitude() {
        return toLongitude;
    }

    public void setToLongitude(double toLongitude) {
        this.toLongitude = toLongitude;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", driver=" + driver +
                ", client=" + client +
                ", status=" + status +
                ", fromLatitude=" + fromLatitude +
                ", fromLongitude=" + fromLongitude +
                ", toLatitude=" + toLatitude +
                ", toLongitude=" + toLongitude +
                ", openTime=" + openTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}