package com.roshka.sifen.test.fields;

import org.junit.Test;

import java.math.BigDecimal;

import static com.roshka.sifen.core.fields.util.FieldFormatUtil.formattdCRed;

public class TdCEedFieldTests {

    @Test
    public void testTdCEedFieldFormat() {
        BigDecimal bd = new BigDecimal(4882.3983882);
        String s = formattdCRed(bd);
        assert s.equals("4882.3984");
    }

}
