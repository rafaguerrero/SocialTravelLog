package com.stl.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtils {
    private static final String SLASH = "/";
    private static final String SPACE = " ";
    private static final String UNDERSCORE = "_";


    public final static String generateUrl(String title, String userId) {
        return (new StringBuilder(SLASH))
                        .append(userId)
                        .append(SLASH)
                        .append(title.replaceAll(SPACE, UNDERSCORE).toLowerCase())
                        .toString();
    }
}
