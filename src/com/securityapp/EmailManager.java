/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.securityapp;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hp
 */
public class EmailManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        String ipAddress = MachineManager.getPublicIPAddress();
        sendEmail("doyinmolaajiboye@gmail.com", "Doyin", ipAddress);

    }

    public static void sendEmail(String ToEmail, String UserName, String ipAddress) throws IOException, InterruptedException {
        // Recipient's email ID needs to be mentioned.
        String to = ToEmail;//"doyinmolaajiboye@gmail.com";
        HashMap<String, String> locationDetails = GeolocationLookup.GetUserLocationDetails(ipAddress);
        // Sender's email ID needs to be mentioned
        String from = "saint@thewealthmarket.com";
        final String username = "saint@thewealthmarket.com";//change accordingly
        final String password = "@Dee20mene";//change accordingly

        //sending email through local host server
//        String host = "localhost";
        String host = "thewealthmarket.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("WM Login Security Alert");
            // Set Message sent date
            message.setSentDate(new Date());

            // Send the actual HTML message, as big as you like
            message.setContent(
                    "<h2 style='color:#d85a33'>Security Alert </h2>"
                    + "<div style='margin-bottom:2em'> "
                    + "<p>The machine with the following details has been used to access your acount. </p>"
                    + "<p><strong>System Name:</strong>" + MachineManager.getSystemName() + "</p>"
                    + "<p><strong>IP Adress:</strong>" + MachineManager.getIPAddress() + "</p>"
                    + "<p><strong>Public IP Adress:</strong>" + MachineManager.getPublicIPAddress() + "</p>"
                    + "<p><strong>MAC Address:</strong>" + MachineManager.getMAC() + "</p>"
                    + "<p><strong>System OS:</strong>" + MachineManager.getSystemOS() + "</p>"
                    + "<p><strong>System S/N:</strong>" + MachineManager.getSystemSN() + "</p>"
//                    + "<p><strong>Continent:</strong>" + locationDetails.get("ContinentName") + "</p>"
                    + "<p><strong>Country:</strong>" + locationDetails.get("CountryName") + "</p>"
//                    + "<p><strong>Country Flag:</strong>" + locationDetails.get("CountryFlag") + "</p>"
                    + "<p><strong>State/Province:</strong>" + locationDetails.get("StateProvince") + "</p>"
                    + "<p><strong>District:</strong>" + locationDetails.get("District") + "</p>"
                    + "<p><strong>City:</strong>" + locationDetails.get("City") + "</p>"
                    + "<p><strong>Zip Code:</strong>" + locationDetails.get("ZipCode") + "</p>"
                    + "<p><strong>Latitude:</strong>" + locationDetails.get("Latitude") + "</p>"
                    + "<p><strong>Longitude:</strong>" + locationDetails.get("Longitude") + "</p>"
                    + "<p><strong>ISP:</strong>" + locationDetails.get("ISP") + "</p>"
                    + "<p><strong>Connection Type:</strong>" + locationDetails.get("ConnectionType") + "</p>"
                    + "<p><strong>AS Number:</strong>" + locationDetails.get("Asn") + "</p>"
                    + "<p><strong>Time Zone:</strong>" + locationDetails.get("Timezone") + "</p>"
                    + "<p>If you are not the one that login with this machine, please contact us by email at info@thewealthmarket.com.com or call 0806 000 0000, or visit <a href='http://www.thewealthmarket.com/'>http://www.thewealthmarket.com/</a> </p>",
                    "text/html");
//            message.setContent(
//                    "<!DOCTYPE html><html><body>"
//                    + "<h2 style='color:#d85a33'> Dear " + UserName + "</h2>"
//                    + "<div style='margin-bottom:2em'> "
//                    + "<h2 style='color:#d85a33'>Security Alert </h2>"
//                    + "<div style='margin-bottom:2em'> "
//                    + "<p>The machine with the following details has been used to access your acount. </p>"
//                    + "<p><strong>System Name:</strong>" + MachineManager.getSystemName() + "</p>"
//                    + "<p><strong>IP Adress:</strong>" + MachineManager.getIPAddress() + "</p>"
//                    + "<p><strong>Public IP Adress:</strong>" + MachineManager.getPublicIPAddress() + "</p>"
//                    + "<p><strong>MAC Address:</strong>" + MachineManager.getMAC() + "</p>"
//                    + "<p><strong>System OS:</strong>" + MachineManager.getSystemOS() + "</p>"
//                    + "<p><strong>System S/N:</strong>" + MachineManager.getSystemSN() + "</p>"
//                    + "<p><strong>System S/N:</strong>" + countryName + "</p>"
//                    + "<p>If you are not the one that login with this machine, please contact us by email at info@thewealthmarket.com.com or call 0806 000 0000, or visit <a href='http://www.thewealthmarket.com/'>http://www.thewealthmarket.com/</a> </p>",
//                    "</div></body></html>");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
