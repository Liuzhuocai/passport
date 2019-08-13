package elf.m.passportsimple.ui

/**

 * author：liuzuo on 19-5-16 16:16

 *

 */
object Config {

    // 是否在开发测试模式
    val LOAD_DATA_DEBUG = true

    //    public static final String HTTP_API_URL = "http://192.168.31.209:8080/appstore-web-api/";
    //val HTTP_API_URL = "http://13.235.21.130:3000/v5/common/"
    val HTTP_API_URL = "http://13.235.21.130:3000/v5/common/"
    val HTTP_API_URL_PASTPORT = "http://13.235.21.130:3001/v5/passport/"

    //common params
    val APPVERSION = "version"
    val DEVICEID = "deviceId"
    val MODEL = "model"
    val SYSTEMVERSION = "systemVersion"
    val BODY = "body"
    val VERSIONNAME = "versionName"
    var JWTTOKEN = "jwttoken"
    var JWTREFRESHTOKEN = "jwtrefreshtoken"
    var USER_ID = "user_id"

    //Login params
    val WECHATTYPE = 1
    val QQTYPE = 2
    val SINATYPE = 3
    val FACEBOOKTYPE = 4
    val TWITTERTYPE = 5


    //result code
    val LOGIN_PHONE = 1
    val LOGIN_THREEPARTY = 2
    val LOGIN_THREEPARTY_WITHOUT_PHONE = 3
    val LOGIN_OUT = 4
    val UPDATE_USER_ICON = 5

    val SP_FIRST_NAME = "my_first_name"
    val SP_LAST_NAME = "my_last_name"
    val SP_COUNTRY = "my_country"
    val SP_DOB = "my_dob"
    val SP_CITY = "my_city"
    val SP_MARTIAL_STATUS = "my_martial_status"
    val SP_GENDER = "my_gender"
    val SP_CURRENT_ADDRESS = "my_current_address"
    val SP_IMAGE_URL = "my_image_url"






    val SP_PHONE = "Login_phone"
    val SP_USERID = "Login_userId"
    val SP_USER_ICON = "Login_user_icon"
    val SP_PWD = "Login_pwd"
    val SP_THREEPARTY = "Login_type_now"
    val SP_BIRTHDAY = "my_birthday"
    val SP_LOCATION = "my_location"

    //fragment arguments
    val ARGUMENT_TITLE = "title"
    val ARGUMENT_TYPE = "type"
    val ACCOUNT_TYPE_SPENT = 0
    val ACCOUNT_TYPE_CREDIT_TRANSFER = 1
    val ACCOUNT_TYPE_RECHARGE = 2
    val REQUEST_CODE_PHOTO = 0X02
    val REQUEST_CODE_SCAN = 0X01

    enum class LoginType {
        LOGIN_IN, LOGIN_OUT
        //登录界面类型
    }
}