/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author rihem
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




public class sms {
    // Find your Account Sid and Auth Token at twilio.com/console
//    public static final String ACCOUNT_SID = System.getenv("AC7b44891eddb0f5720f8be75872684424");
//    public static final String AUTH_TOKEN = System.getenv("8d7c1cd1f881d69af9cfec823ea96212");
 public static final String ACCOUNT_SID = "AC7b44891eddb0f5720f8be75872684424";
    public static final String AUTH_TOKEN = "8d7c1cd1f881d69af9cfec823ea96212";
    public static void sendSms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21627348474"), // to
                        new PhoneNumber("+12705440655"), // from
                        "Where's Wallace?")
                .create();

        System.out.println(message.getSid());
    }
}
