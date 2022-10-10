package Pages;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class HeaderPage {
    //static String APIbodyTOkenLOgin;
    //static List<Header> headerlist = new ArrayList<Header>();
    //static public String token;

    //Header Reusable Variables
//    static public String authorizationName;
//    static public String authorizationToken;

    static public String headerType = "Content-Type";
    static public String headerMediaType = "application/json";


    public Headers allHeaders(List<Header> newHeaders) {
        System.out.println(newHeaders);
//        System.out.println(b);
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header(headerType, headerMediaType));
        //headerlist.add(new Header(authorizationName, authorizationToken));
        if (!newHeaders.isEmpty()) {
            headerlist.addAll(newHeaders);
        }
        Headers headers = new Headers(headerlist);
        System.out.println(headers);
        return headers;

    }
}
