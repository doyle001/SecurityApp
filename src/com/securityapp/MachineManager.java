/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.securityapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.rmi.activation.ActivationException;

/**
 *
 * @author hp
 */
public class MachineManager {

/**
 *
 * @author hp
 */

    public static void main(String args[]) throws IOException, InterruptedException, ActivationException {
        System.out.println("System Name : " + getSystemName());
        System.out.println("System IP   : " + getIPAddress());
        System.out.println("Public IP Address: " + getPublicIPAddress());
        System.out.println("MAC Address : " + getMAC());
        System.out.println("OS : " + getSystemOS());
        System.out.println("SN: " + getSystemSN());
        
        String machine_details = getSystemName() + "," + getIPAddress() + "," + getPublicIPAddress() + "," + getMAC() + "," + getSystemOS() + "," + getSystemSN();
   
    }

    public static String getSystemName() {
        try {
            InetAddress inetaddress = InetAddress.getLocalHost(); //Get LocalHost refrence
            String name = inetaddress.getHostName(); //Get Host Name
            return name; //return Host Name
        } catch (Exception E) {
            E.printStackTrace(); //print Exception StackTrace

        }
        return null;

    }

    /**
     *      * method to get Host IP      * @return Host IP Address      
     */
    public static String getIPAddress() {
        try {
            InetAddress inetaddress = InetAddress.getLocalHost(); //Get LocalHost refrence

            String ip = inetaddress.getHostAddress(); // Get Host IP Address
            return ip; // return IP Address
        } catch (Exception E) {

            E.printStackTrace(); //print Exception StackTrace
            return null;
        }

    }
    
    public static String getPublicIPAddress(){
       String systemipaddress = ""; 
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = 
            new BufferedReader(new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (Exception e) 
        { 
            systemipaddress = "Cannot Execute Properly"; 
        }
        return systemipaddress;
    
    }

    /**
     *      * method to get Host Mac Address      * @return  Mac Address      
     */
    public static String getMAC() {
        try {
            InetAddress inetaddress = InetAddress.getLocalHost(); //Get LocalHost refrence

            NetworkInterface network = NetworkInterface.getByInetAddress(inetaddress); //get Network interface Refrence by InetAddress Refrence

            byte[] macArray = network.getHardwareAddress(); //get Harware address Array

            StringBuilder str = new StringBuilder();

            // Convert Array to String 
            for (int i = 0; i < macArray.length; i++) {

                str.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
            }
            String macAddress = str.toString();

            return macAddress; //return MAc Address

        } catch (Exception E) {
            E.printStackTrace();//print Exception StackTrace   return null;
        }
        return null;
    }

//    method to get system os     * @return os
    public static String getSystemOS() {
        try {
            String OSName = "";
            OSName = System.getProperty("os.name");
            return OSName;
        } catch (Exception E) {
            E.printStackTrace();
        }
        return null;
    }

    public static String getSystemMotherBoard_SerialNumber() throws IOException, InterruptedException {
        try {
            String OSName = System.getProperty("os.name");
            if (OSName.contains("Windows")) {
                return (getWindowsMotherboard_SerialNumber());
            } else {
                return (GetLinuxMotherBoard_serialNumber());
            }
        } catch (Exception E) {
            System.err.println("System MotherBoard Exp : " + E.getMessage());
            return null;
        }
    }

    /**
     * Method for get Windows Machine MotherBoard Serial Number
     *
     * @return
     */
    public static String getWindowsMotherboard_SerialNumber() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs
                    = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n"
                    + "Next \n";

            fw.write(vbs);
            fw.close();

            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception E) {
            System.err.println("Windows MotherBoard Exp : " + E.getMessage());
        }
        return result.trim();
    }

    /**
     * Method for get Linux Machine MotherBoard Serial Number
     *
     * @return
     */
    private static String GetLinuxMotherBoard_serialNumber() {
        String command = "dmidecode -s baseboard-serial-number";
        String sNum = null;
        try {
            Process SerNumProcess = Runtime.getRuntime().exec(command);
            BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
            sNum = sNumReader.readLine().trim();
            SerNumProcess.waitFor();
            sNumReader.close();
        } catch (Exception ex) {
            System.err.println("Linux Motherboard Exp : " + ex.getMessage());
            sNum = null;
        }
        return sNum;
    }

    public static String getSystemSN() throws IOException, InterruptedException {
        String motherBoard_SerialNumber = getSystemMotherBoard_SerialNumber();
        return motherBoard_SerialNumber;

    }
}