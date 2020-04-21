package WX;

/**
 * created on 2020/4/6
 */
public class Token {
    //此类包含获取OpenID和AccessToken的方法
    private final static String APPID = "wxed9ea3b25d4bea1f";
    private final static String APPSECRET = "83c842b7b96d33bff0b1cc49fb0e1261";

    public static String getAccessToken(String openId) {
        String str_access = Token.getAccessToken();
        String accessToken = Json.getValueFromJson(str_access, "access_token");
        return accessToken;

    }

    public static String getOpenId(String code) {
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append(APPID);
        url.append("&secret=");//secret设置
        url.append(APPSECRET);
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");
        String str_userInfo = HttpUtil.sendGet(url.toString(), "utf-8");
        return Json.getValueFromJson(str_userInfo,"openid");
    }

    public static String getAccessToken() {
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
        url.append(APPID);
        url.append("&secret=");
        url.append(APPSECRET);
        String s = HttpUtil.sendGet(url.toString(), "utf-8");
        return s;
    }

}
