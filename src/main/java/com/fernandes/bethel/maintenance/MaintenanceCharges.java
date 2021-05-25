package com.fernandes.bethel.maintenance;

import java.util.Random;
import java.util.UUID;

public class MaintenanceCharges {
    private Long serviceCharges;
    private Long waterCharges;
    private final Long totalMaintenanceCharges;
    private final Long orderId;

    public MaintenanceCharges(Long serviceCharges,
                              Long waterCharges,
                              Long totalMaintenanceCharges,
                              Long orderId) {
        this.serviceCharges = serviceCharges;
        this.waterCharges = waterCharges;
        this.totalMaintenanceCharges = totalMaintenanceCharges;
        this.orderId = orderId;
    }

    public Long getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Long serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public Long getWaterCharges() {
        return waterCharges;
    }

    public void setWaterCharges(Long waterCharges) {
        this.waterCharges = waterCharges;
    }

    public Long getTotalMaintenanceCharges() {
        return waterCharges + serviceCharges;
    }

    public Long getOrderId() {
        return Math.abs(new Random().nextLong());
    }
}