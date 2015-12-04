package com.caij.codehub;

import android.test.AndroidTestCase;

import com.caij.codehub.utils.AvatarUrlUtil;
import com.caij.lib.utils.LogUtil;

/**
 * Created by Administrator on 2015/8/24.
 */
public class DataTest extends AndroidTestCase{
    public final static String HTTPS = "https://";
    public final static String HOST = "api.github.com";
    public final static String URL_SPLITTER = "/";
    public final static String API_HOST = HTTPS + HOST + URL_SPLITTER;
    public final static String TOKEN_NOTE = "Github APP Token";
    private final static String[] SCOPES = {"public_repo","repo", "user", "gist"};
    public final static String CREATE_TOKEN = API_HOST + "authorizations"; // POST

    public static final String AUTHORIZATION = "Authorization";

    public final static String REPO = "user/repos";

    public void testAvatarUrlUtil() {
        String url = AvatarUrlUtil.restoreAvatarUrl("https://avatars.githubusercontent.com/u/10348598?v=2");
        LogUtil.d("AndroidTestCase", url);
    }

}
