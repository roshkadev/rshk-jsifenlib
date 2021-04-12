package com.roshka.sifen.internal.helpers;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Helper encargado de manejar algunas peticiones HTTP.
 */
public class HttpHelper {
    public static void request(SSLSocketFactory sslSocketFactory, String url) {
        try {
            URL actualURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) actualURL.openConnection();
            connection.setInstanceFollowRedirects(false);
            if (connection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) connection).setSSLSocketFactory(sslSocketFactory);
            }
            connection.setRequestMethod("GET");
            DataInputStream in = new DataInputStream(connection.getInputStream());
            connection.connect();
            byte[] buff = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int count;
            while ((count = in.read(buff)) != -1) {
                baos.write(buff, 0, count);
            }

            System.out.println("Headers");
            System.out.println("-------");

            Map<String, List<String>> headerFields = connection.getHeaderFields();
            if (headerFields != null) {
                for (String key : headerFields.keySet()) {
                    List<String> strings = headerFields.get(key);
                    System.out.println(key + ": [" + String.join(",", strings) + "]");
                }
            }

            System.out.println(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}