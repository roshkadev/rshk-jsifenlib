package com.roshka.sifen.soap;

import javax.xml.soap.MimeHeaders;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class MimeHeadersHelper {

    public static MimeHeaders getFromHttpURLConnection(HttpURLConnection httpURLConnection)
    {
        MimeHeaders mimeHeaders = new MimeHeaders();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields != null) {
            for (String key : headerFields.keySet()) {
                if (key == null) continue;
                for (String v : headerFields.get(key)) {
                    mimeHeaders.addHeader(key, v);
                }
            }
        }
        return mimeHeaders;
    }
}
