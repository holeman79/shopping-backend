package com.holeman79.shoppingbackend.common.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderNoGenerator {
    public static long nextOrderNo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyyMMdd");
        String date = simpleDateFormat.format(new Date());

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
