package com.holeman79.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class OrderNoGenerator {
    public static long nextOrderNo() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        long val = -1;
        do {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            val = bi.longValue();
        } while (val < 0 || val > 9007199254740991L);

        String orderNo = date + val;

        return Long.parseLong(orderNo.substring(0, 18));
    }
}
