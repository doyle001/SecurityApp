/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.securityapp;

import java.util.Map;

/**
 *
 * @author hp
 */
public class GeolocationCurrency {
    public String name;
    public String code;
    public String symbol;

    GeolocationCurrency(Map json) {
        this.name = (String) json.get("name");
        this.code = (String) json.get("code");
        this.symbol = (String) json.get("symbol");
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.format("name: '%s' \ncode: '%s' \nsymbol: '%s'", name, code, symbol);
    }
}

