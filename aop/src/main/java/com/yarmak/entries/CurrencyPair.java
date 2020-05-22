package com.yarmak.entries;

import java.math.BigDecimal;

public class CurrencyPair {

    private final BigDecimal buyingPrice;
    private final BigDecimal sellingPrice;

    public CurrencyPair(final BigDecimal buyingPrice, final BigDecimal sellingPrice) {
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal buyingPrice() {
        return buyingPrice;
    }

    public BigDecimal sellingPrice() {
        return sellingPrice;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", this.buyingPrice, this.sellingPrice);
    }
}

