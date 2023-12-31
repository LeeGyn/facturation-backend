package com.etslyam.facturationbackend.utils.crypto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Les différents encodages des chaînes cryptées
 *
 * {@link #BASE64}
 * {@link #HEX}
 */
public enum Encoding {
    /**
     * Encodage en Base 64
     */
    BASE64("BASE64"),
    /**
     * Encodage en Hexadécimal
     */
    HEX("HEX");

    /**
     * Valeur de l'encodage
     */
    @JsonValue
    private final String value;

    /**
     * Constructeur
     *
     * @param value Valeur de l'encodage
     */
    Encoding(String value) {
        this.value = value;
    }
}
