package com.michealyang.commons.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieUtil {

    public static final String ROOT_PATH="/";

    private static final int DEFAULT_MAX_AGE=-1;
    private static final int DELETE_MAX_AGE=0;

    public static Cookie getCookie(HttpServletRequest request, String cookieName){
        Cookie cookies[]=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equalsIgnoreCase(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName){
        Cookie cookie=getCookie(request, cookieName);
        if(cookie!=null){
            return cookie.getValue();
        }
        return null;
    }

    public static String getDecodeCookieValue(HttpServletRequest request, String cookieName) throws UnsupportedEncodingException{
        String cookieValue=getCookieValue(request, cookieName);
        if(cookieValue!=null){
            return URLDecoder.decode(cookieValue, "UTF-8");
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String key, String value){
        setCookie(response, key, value, null, ROOT_PATH, DEFAULT_MAX_AGE);
    }

    public static void setCookie(HttpServletResponse response, String key, String value, String domain, String path, Integer maxAge){
        Cookie cookie=new Cookie(key, value);
        if(domain!=null && !domain.trim().isEmpty()){
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    private static String calculatePath(HttpServletRequest request, Cookie cookie){
        String path=cookie.getPath();
        if(path==null || path.trim().isEmpty()){
            path=request.getContextPath();
        }
        if(path==null || path.trim().isEmpty()){
            path=ROOT_PATH;
        }
        return path.trim();
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name){
        Cookie cookie=getCookie(request, name);
        if(cookie!=null){
            setCookie(response, name, "", cookie.getDomain(), calculatePath(request, cookie), DELETE_MAX_AGE);
        }
    }
}
