package com.roshka.sifen.core.fields.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class FieldFormatUtil {

    private static NumberFormat _tdCRedFormat = NumberFormat.getInstance();

    static {
        _tdCRedFormat.setGroupingUsed(false);
        _tdCRedFormat.setMaximumFractionDigits(4);
        _tdCRedFormat.setMaximumIntegerDigits(4);
    }

    public static String formattdCRed(BigDecimal val) {
        return _tdCRedFormat.format(val);
    }

}
