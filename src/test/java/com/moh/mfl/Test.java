/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.moh.mfl;

import com.moh.mfl.config.SecurityConfig;

/**
 *
 * @author chulu
 */
public class Test {

    public static void main(String[] args) {
        String key = "QMIM08qNX3";
        String authKey = "IGEENv2qAu";
        String enc="$2a$10$UHJxxmayS1I.z9bo2FEdBeh/vNT3XGvuRGJBvGwjEu1/ULawl0nXK";
        SecurityConfig config = new SecurityConfig();
//        String encoded = config.encodeKey(key + authKey);
        System.out.println("Encoded key is: " + enc);
        if (config.verifyKey(key + authKey, enc)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}
