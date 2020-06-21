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
public class GeolocationTimezone {

    public String name;
    public Double offset;
    public String currentTime;
    public Double currentTimeUnix;
    public Boolean isDST;
    public Double dstSavings;

    GeolocationTimezone(Map json) {
        this.name = (String) json.get("name");
        this.offset = (Double) json.get("offset");
        this.currentTime = (String) json.get("current_time");
        this.currentTimeUnix = (Double) json.get("current_time_unix");
        this.isDST = (Boolean) json.get("is_dst");
        this.dstSavings = (Double) json.get("dst_savings");
    }

    public String getName() {
        return name;
    }

    public Double getOffset() {
        return offset;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public Double getCurrentTimeUnix() {
        return currentTimeUnix;
    }

    public Boolean isDST() {
        return isDST;
    }

    public Double getDSTSavings() {
        return dstSavings;
    }

    @Override
    public String toString() {
        return String.format("name: '%s' \noffset: '%s' \ncurrent_time: '%s' \ncurrent_time_unix: '%f' \nis_dst: '%b' \ndst_savings: '%s'", name, offset, currentTime, currentTimeUnix, isDST, dstSavings);
    }
}
