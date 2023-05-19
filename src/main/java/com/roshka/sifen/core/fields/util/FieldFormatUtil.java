package com.roshka.sifen.core.fields.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class FieldFormatUtil {

    private static DecimalFormat _tdCRedFormat = (DecimalFormat) DecimalFormat.getInstance();

    static {
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        _tdCRedFormat.setDecimalSeparatorAlwaysShown(false);
        _tdCRedFormat.setGroupingUsed(false);
        _tdCRedFormat.setMaximumFractionDigits(4);
        _tdCRedFormat.setMaximumIntegerDigits(4);
        _tdCRedFormat.setDecimalFormatSymbols(dfs);
    }

    public static String formattdCRed(BigDecimal val) {
        return _tdCRedFormat.format(val);
    }

}
