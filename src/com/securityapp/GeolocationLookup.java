/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.securityapp;

import java.util.HashMap;

/**
 *
 * @author hp
 */
public class GeolocationLookup {

    public static HashMap<String, String> GetUserLocationDetails(String ipAddress) {
        IPGeolocationAPI api = new IPGeolocationAPI("6654da30fe25402f8d0d4994b62bd6c2");
        HashMap<String, String> locationDetails = new HashMap();
        // Get geolocation for IP address (1.1.1.1) and fields (geo, time_zone and currency)
        GeolocationParams geoParams = new GeolocationParams();

        geoParams.setIPAddress(ipAddress);
        geoParams.setFields("geo,time_zone,currency");

        Geolocation geolocation = api.getGeolocation(geoParams);

// Check if geolocation lookup was successful
        if (geolocation.getStatus() == 200) {
//            locationDetails.put("ContinentName", geolocation.getContinentName());
            locationDetails.put("CountryName", geolocation.getCountryName());
//            locationDetails.put("CountryFlag", geolocation.getCountryFlag());
            locationDetails.put("StateProvince", geolocation.getStateProvince());
//            locationDetails.put("District", geolocation.getDistrict());
            locationDetails.put("City", geolocation.getCity());
            locationDetails.put("ZipCode", geolocation.getZipCode());
            locationDetails.put("Latitude", geolocation.getLatitude());
            locationDetails.put("Longitude", geolocation.getLongitude());
//            locationDetails.put("ISP", geolocation.getISP());
//            locationDetails.put("ConnectionType", geolocation.getConnectionType());
//            locationDetails.put("Asn", geolocation.getAsn());
            locationDetails.put("Timezone", "" + geolocation.getTimezone());
            
        } else {
            System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
        }

        return locationDetails;
    }

}
