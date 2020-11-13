package com.roshka.sifen.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtil {
    public static byte[] getByteArrayFromInputStream(InputStream inputStream) throws IOException {
        DataInputStream in = new DataInputStream(inputStream);
        byte[] buff = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int count;
        while ((count = in.read(buff)) != -1) {
            baos.write(buff, 0, count);
        }

        return baos.toByteArray();
    }
}
