package com.yarmak.rates;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class CurrencyPair {

    private final BigDecimal buyingPrice;
    private final BigDecimal sellingPrice;

    @Override
    public String toString() {
        return String.format("%s : %s", this.buyingPrice, this.sellingPrice);
    }
}

