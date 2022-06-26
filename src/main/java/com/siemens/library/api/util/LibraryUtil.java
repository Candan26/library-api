package com.siemens.library.api.util;

public class LibraryUtil {
    /// FORMAT DEFINITIONS
    public static final String isbnRegex = "[0-9-.]+";
    public static final String publishDateFormat = "YYYY-MM-DDThh:mm:ss.sssZ";
    public static final int isnLength = 13;
    public static final int titleLength = 200;
    public static final int authorLength = 200;
    public static final int categoryLength = 100;
    public static final int publisherLength = 100;
    public static final int editionLength = 100;
    public static final int nameLength = 100;
    public static final int surnameLength = 100;
    public static final int emailLength = 100;
    public static final int phoneNumberLength = 50;
    public static final int bookIdLength = 100;
    public static final int memberIdLength = 100;
    public static final String joinDateFormat = "YYYY-MM-DDThh:mm:ss.sssZ";
    /// RESPONSE STATUS
    public static final String FAILED = "1";
    public static final String SUCCEED = "0";
    /// ERROR CODES
    public static final String REQUIRED_OBJ_MISSING_BOOK_ERROR_CODE = "ROMB01";
    public static final String REQUIRED_OBJ_MISSING_MEMBER_ERROR_CODE = "ROMM01";
    public static final String REQUIRED_OBJ_MISSING_BORROW_ERROR_CODE = "ROMB01";
    public static final String ISBN_FORMAT_ERROR_CODE = "IF01";
    public static final String TITLE_LENGTH_ERROR_CODE = "TL01";
    public static final String NAME_LENGTH_ERROR_CODE = "NL01";
    public static final String SURNAME_LENGTH_ERROR_CODE = "SNL01";
    public static final String EMAIL_LENGTH_ERROR_CODE = "EML01";
    public static final String PHONE_NUMBER_LENGTH_ERROR_CODE = "PNL01";
    public static final String AUTHOR_LENGTH_ERROR_CODE = "AL01";
    public static final String CATEGORY_LENGTH_ERROR_CODE = "CL01";
    public static final String PUBLISHER_LENGTH_ERROR_CODE = "PL01";
    public static final String EDITION_LENGTH_ERROR_CODE = "EDL01";
    public static final String PUBLISH_DATE_PARSE_ERROR_CODE = "PDP01";
    public static final String BORROW_DATE_PARSE_ERROR_CODE ="BDP01";
    public static final String BOOK_NOT_FOUND_ERROR_CODE = "BNF01";
    public static final String BOOK_ID_EMPTY_CODE = "BIE01";
    public static final String MEMBER_NOT_FOUND_ERROR_CODE = "PINM01";
    public static final String BOOK_IS_NOT_AVAILABLE_EMPTY_ERROR_CODE ="BINA01";
    public static final String BORROWING_NOT_FOUND_ERROR_CODE ="BNF01";
    /// ERROR MESSAGES
    public static final String REQUIRED_OBJ_MISSING_BOOK_ERROR = "the isbn, title , author parameters are required";
    public static final String REQUIRED_OBJ_MISSING_MEMBER_ERROR = "the name, surname , email parameters are required";
    public static final String REQUIRED_OBJ_MISSING_BORROW_ERROR = "the bookid , memberid, borrowDate, dueDate parameters are required";
    public static final String ISBN_FORMAT_ERROR = "The isbn should be a number 0-9 and max length is 13";
    public static final String TITLE_LENGTH_ERROR = "The title of book cannot exceed 200 character";
    public static final String NAME_LENGTH_ERROR = "The name of member cannot exceed 100 character";
    public static final String SURNAME_LENGTH_ERROR = "The surname of member cannot exceed 100 character";
    public static final String EMAIL_LENGTH_ERROR = "The email of member cannot exceed 100 character";
    public static final String PHONE_NUMBER_LENGTH_ERROR = "The phone of member cannot exceed 50 character";
    public static final String AUTHOR_LENGTH_ERROR = "The author of book cannot exceed 200 character";
    public static final String CATEGORY_LENGTH_ERROR = "The category of book cannot exceed 100 character";
    public static final String PUBLISHER_LENGTH_ERROR = "The publisher of book cannot exceed 100 character";
    public static final String EDITION_LENGTH_ERROR = "The edition of book cannot exceed 100 character";
    public static final String PUBLISH_DATE_PARSE_ERROR = " the given publish data does not match with format.";
    public static final String BORROW_DATE_PARSE_ERROR = " the given borrow data does not match with format.";
    public static final String BOOK_NOT_FOUND_ERROR = "Book is not found.";
    public static final String BOOK_ID_EMPTY_ERROR = "Id field cannot be empty";
    public static final String BOOK_IS_NOT_AVAILABLE_EMPTY_ERROR = "Book is not available.";
    public static final String MEMBER_NOT_FOUND_ERROR = "Member not found for given id";
    public static final String BORROWING_NOT_FOUND_ERROR = "Borrowing not found for given id";

}
