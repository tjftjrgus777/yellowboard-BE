package com.bitcamp.board_back.common;

public class CertificationNumber {

    public static String getCertificationNumber () {

        String certificationNumber = "";

        for (int count = 0; count <4; count++) certificationNumber += (int) (Math.random() * 10);

        return certificationNumber;
    }

 
    
}
