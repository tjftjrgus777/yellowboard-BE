package com.bitcamp.board_back.common;

public interface ResponseMessage {

     // ======================= 200 - SUCCESS ======================= //
     String SUCCESS = "Success.";

     // ======================= 400 - BAD REQUEST ERRORS ======================= //
     String VALIDATTION_FAILED = "Validation failed.";
     String DUPLICATE_EMAIL = "Duplicate email.";
     String DUPLICATE_NICKNAME = "Duplicate nickname.";
     String DUPLICATE_TEL_NUMBER = "Duplicate tel number.";
     String NOT_EXISTED_USER = "This user does not exist.";
     String NOT_EXISTED_BOARD = "This board does not exist.";

     // ======================= 401 - UNAUTHORIZED ERRORS ======================= //
     String SIGN_IN_FAIL = "Login information mismatch.";
     String AUTHORIZATION_FAIL = "Authorization Failed.";

     // ======================= 403 - FORBIDDEN ERRORS ======================= //
     String NO_PERMISSION = "Do not have permission.";

     // ======================= 500 - INTERNAL SERVER ERROR ======================= //
     String DATABASE_ERROR = "Database error.";


}
