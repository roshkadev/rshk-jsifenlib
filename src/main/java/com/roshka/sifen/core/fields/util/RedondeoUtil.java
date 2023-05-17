package com.roshka.sifen.core.fields.util;

import com.roshka.sifen.core.types.CMondT;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class RedondeoUtil {



    //
    // reglas de redon

    /**
     * En consideración a la Resolución 314 del 2014 (Secretaría de Defensa del Consumidor-SEDECO). Las
     * reglas de redondeo aplican a múltiplos de 50 guaraníes de la siguiente manera:
     * Guaraníes 107.437  | Redondeo | 37 |  107.400
     * Guaraníes  47.789  | Redondeo | 39 |   47.750
     *
     * Observación: Para monedas extranjeras o cualquier otro cálculo que contenga decimales, las reglas
     * de validación aceptarán redondeos de 50 céntimos (por encima o por debajo).
     *
     * @param cMondT
     * @param valor
     * @return
     */
    public static BigDecimal redondeoOficialSET(CMondT cMondT, BigDecimal valor) {

        if (cMondT == CMondT.PYG) {
            return redondeoOficialGuaranies(valor);
        } else {
            return redondeoOficialOtrasMonedas(valor);
        }
    }

    public static BigDecimal redondeoOficialGuaranies(BigDecimal valor) {
        return valor.remainder(BigDecimal.valueOf(50));
    }

    public static BigDecimal redondeoOficialOtrasMonedas(BigDecimal value) {
        BigDecimal fiftyCents = new BigDecimal("0.5");
        BigDecimal dividedValue = value.divide(fiftyCents, 0, RoundingMode.HALF_UP);
        BigDecimal roundedValue = dividedValue.multiply(fiftyCents);
        return roundedValue.subtract(value).multiply((new BigDecimal("-1.0")));
    }
}
