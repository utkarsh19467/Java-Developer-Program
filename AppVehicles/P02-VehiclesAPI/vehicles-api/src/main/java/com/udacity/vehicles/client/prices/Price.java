package com.udacity.vehicles.client.prices;

import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 */
public class Price {

    private String currency;
    private BigDecimal price;
    private Long vehicleId;

    public Price(String price) {
        String[] priceInfo = price.split(" ");
        this.currency = priceInfo[0];
        this.price = new BigDecimal(priceInfo[1]);
    }

    public Price() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
