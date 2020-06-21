/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.securityapp;

/**
 *
 * @author hp
 */
class Strings {

    static String nullToEmpty(String s) {
        if(s == null) {
            s = "";
        }
        return s;
    }

    static Boolean isNullOrEmpty(String string) {
        return string == null || string.trim().equals("");
    }
}