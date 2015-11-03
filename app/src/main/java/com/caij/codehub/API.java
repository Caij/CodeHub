package com.caij.codehub;

import java.util.Map;

/**
 * Created by Administrator on 2015/8/25.
 */
public class API {

    public final static String API_HOST ="https://api.github.com";
    public final static String TOKEN_NOTE = "Github APP Token";
    public final static String[] SCOPES = {"public_repo","repo", "user", "gist"};
    public final static String AUTHORIZATION_URL = API_HOST + "/authorizations"; // POST

    public final static String REPOSITORY_STARRED_URI = "/starred";
    public final static String REPOSITORY_REPOS_URI = "/repos";

    public final static String SEARCH_REPOSITORY_URI = "/search/repositories";

    public static final String AUTHORIZATION = "Authorization";

    public static final String PER_PAGE = "per_page";
    public static final String PAGE = "page";

    public static final String Q = "q";
    public static final String SORT = "sort";
    public static final String ORDER = "order";

    public static Map<String, String> configAuthorizationHead(Map<String, String> head, String token) {
        head.put(AUTHORIZATION, "Token " + token);
        return head;
    }

    public static final String TRENDING_REPOSITORY_HOST = "http://trending.codehub-app.com/v2/trending";

    public static final String TENDING_REPOSITORY_PARAM_LANGUAGE = "language";

    public static final String TENDING_REPOSITORY_PARAM_SINCE = "since";

    public static final String GITHUB_README = "https://github.com/%s/%s/blob/master/README.md";

    public static final String GITHUB_FILE = "https://github.com/%s/%s/%s/%s/%s";
}
