package com.etslyam.facturationbackend.utils.beans;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Les différentes tailles de token (d'identifiants uniques)
 *
 * {@link #LENGTH_32}
 * {@link #LENGTH_64}
 *
 * @author jyoboue
 * @version 1.0
 */
public enum TokenLength {
    /**
     * Taille 32 caractères
     */
    LENGTH_32(32),
    /**
     * Taille 64 caractères
     */
    LENGTH_64(64);

    /**
     * Taille du token
     */
    private final int value;

    /**
     * Constructeur
     *
     * @param value Valeur de la taille du token
     */
    TokenLength(int value) {
        this.value = value;
    }

    /**
     * Retourne l'objet sous forme de chaîne de caractères
     *
     * @return Statut sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        return this.value + "";
    }

    /**
     * Retourne la valeur de la taille du token
     *
     * @return Valeur de la taille du token
     */
    @JsonValue
    public int value() {
        return this.value;
    }
}
