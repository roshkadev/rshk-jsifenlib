package com.roshka.sifen.core.beans;

import com.roshka.sifen.internal.util.SifenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la respuesta a la validación de la firma digital de un DE.
 */
public class ValidezFirmaDigital {
    private boolean esValido;
    private String motivoInvalidez;

    /**
     * Datos del sujeto encontrados en el certificado utilizado para la firma digital.
     */
    private List<SujetoCertificado> sujetosCertificado;

    private ValidezFirmaDigital(boolean esValido, String motivoInvalidez, List<SujetoCertificado> sujetosCertificado) {
        this.esValido = esValido;
        this.motivoInvalidez = motivoInvalidez;
        this.sujetosCertificado = sujetosCertificado;
    }

    /**
     * Método interno, no usar.
     */
    public static ValidezFirmaDigital create(boolean isValid, List<SujetoCertificado> certificateSubjects) {
        return new ValidezFirmaDigital(isValid, null, certificateSubjects);
    }

    /**
     * Método interno, no usar.
     */
    public static ValidezFirmaDigital create(boolean isValid, String invalidityReason) {
        return new ValidezFirmaDigital(isValid, invalidityReason, new ArrayList<>());
    }

    /**
     * Método interno, no usar.
     */
    public static ValidezFirmaDigital create(boolean isValid, String invalidityReason, List<SujetoCertificado> certificateSubjects) {
        return new ValidezFirmaDigital(isValid, invalidityReason, certificateSubjects);
    }

    public static class SujetoCertificado {
        private String numeroDocumento;
        private String razonSocial;

        private SujetoCertificado(String numeroDocumento, String razonSocial) {
            this.numeroDocumento = numeroDocumento;
            this.razonSocial = razonSocial;
        }

        /**
         * Método interno, no usar.
         */
        public static SujetoCertificado create(String documentNumberParam, String registeredNameParam) {
            String documentNumber = null, registeredName = null;

            if (SifenUtil.isNotBlank(documentNumberParam)) {
                if (documentNumberParam.startsWith("CI")) {
                    documentNumber = documentNumberParam.substring(2).trim();
                } else if (documentNumberParam.startsWith("RUC")) {
                    documentNumber = documentNumberParam.substring(3).trim();
                }
            }

            if (documentNumber != null && !documentNumber.contains("-")) {
                documentNumber = documentNumber + "-" + SifenUtil.generateDv(documentNumber);
            }

            if (SifenUtil.isNotBlank(registeredNameParam)) {
                registeredName = registeredNameParam.trim();
            }

            return new SujetoCertificado(documentNumber, registeredName);
        }

        public String getNumeroDocumento() {
            return numeroDocumento;
        }

        public void setNumeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
        }

        public String getRazonSocial() {
            return razonSocial;
        }

        public void setRazonSocial(String razonSocial) {
            this.razonSocial = razonSocial;
        }
    }

    public boolean isValido() {
        return esValido;
    }

    public void setEsValido(boolean esValido) {
        this.esValido = esValido;
    }

    public String getMotivoInvalidez() {
        return motivoInvalidez;
    }

    public void setMotivoInvalidez(String motivoInvalidez) {
        this.motivoInvalidez = motivoInvalidez;
    }

    public List<SujetoCertificado> getSujetosCertificado() {
        return sujetosCertificado;
    }

    public void setSujetosCertificado(List<SujetoCertificado> sujetosCertificado) {
        this.sujetosCertificado = sujetosCertificado;
    }
}
