package com.bitcamp.board_back.common;

public interface ResponseCode {

    // ======================= 200 - SUCCESS ======================= //
    String SUCCESS = "SU";


    // ======================= 400 - BAD REQUEST ERRORS ======================= //
    String VALIDATTION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";


    // ======================= 401 - UNAUTHORIZED ERRORS ======================= //
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";


    // ======================= 403 - FORBIDDEN ERRORS ======================= //
    String NO_PERMISSION = "NP";


    // ======================= 500 - INTERNAL SERVER ERROR ======================= //
    String DATABASE_ERROR = "DBE";

}
