package com.caij.codehub.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Caij on 2015/12/4.
 * https://avatars.githubusercontent.com/u/10348598?v=3 same as https://avatars.githubusercontent.com/u/10348598
 * lead to Glide load image twice
 */
public class AvatarUrlUtil {

    public static String restoreAvatarUrl(String stringUrl) {
        try {
            StringBuilder sb = new StringBuilder();
            URL url = new URL(stringUrl);
            sb.append(url.getProtocol()).append("://").append(url.getHost()).append(url.getPath());
            return sb.toString();
        } catch (MalformedURLException e) {
            return stringUrl;
        }
    }

}
