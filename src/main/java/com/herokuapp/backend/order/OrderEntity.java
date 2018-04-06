package com.herokuapp.backend.order;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordertable", catalog = "taxihub")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long driverId;
    private Long clientId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.OPEN;

    private double fromLatitude;
    private double fromLongitude;
    private double toLatitude;
    private double toLongitude;

    private LocalDateTime openTime = LocalDateTime.now();
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public OrderEntity() {
    }


    public OrderEntity(Long driverId, Long clientId, OrderStatus status, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, LocalDateTime openTime, LocalDateTime startTime, LocalDateTime endTime) {
        this.driverId = driverId;
        this.clientId = clientId;
        this.status = status;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.openTime = openTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OrderEntity(Long driverId, Long clientId, OrderStatus status, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, LocalDateTime endTime) {
        this.driverId = driverId;
        this.clientId = clientId;
        this.status = status;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.endTime = endTime;
    }

    public OrderEntity(Long clientId, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
        this.clientId = clientId;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
    }


    public OrderEntity(Long driverId, Long clientId, OrderStatus status, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, LocalDateTime startTime, LocalDateTime endTime) {
        this.driverId = driverId;
        this.clientId = clientId;
        this.status = status;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OrderEntity(Long clientId, OrderStatus status, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, LocalDateTime startTime) {
        this.clientId = clientId;
        this.status = status;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.startTime = startTime;
    }

    public OrderEntity(Long id, Long driverId, Long clientId, OrderStatus status, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.driverId = driverId;
        this.clientId = clientId;
        this.status = status;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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
                ", driverId=" + driverId +
                ", clientId=" + clientId +
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