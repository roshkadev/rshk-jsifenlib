package com.roshka.sifen.test.format;

import com.roshka.sifen.core.fields.util.FieldFormatUtil;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class FieldFormatUtilsTests {

    @Test
    public void testFormatdCRed00() {
        String expected = "1";
        assertEquals(expected, FieldFormatUtil.formattdCRed(new BigDecimal("1")));
    }

    @Test
    public void testFormatdCRed01() {
        String expected = "-1912";
        assertEquals(expected, FieldFormatUtil.formattdCRed(new BigDecimal("-1912")));
    }

    @Test
    public void testFormatdCRed02() {
        String expected = "0.18";
        assertEquals(expected, FieldFormatUtil.formattdCRed(new BigDecimal("0.18")));
    }

    @Test
    public void testFormatdCRed03() {
        Locale currentLocale = Locale.getDefault();
        // probamos que con un LOCALE con separador "," para los decimales, se mantenga el PUNTO
        // en el formateador
        Locale.setDefault(Locale.GERMAN);
        String expected = "0.18";
        assertEquals(expected, FieldFormatUtil.formattdCRed(new BigDecimal("0.18")));
        Locale.setDefault(currentLocale);
    }



}
