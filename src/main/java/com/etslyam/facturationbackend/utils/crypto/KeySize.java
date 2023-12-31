package com.etslyam.facturationbackend.utils.crypto;

/**
 * Les différentes taille d'une clé
 * Les valeurs possibles
 *
 * {@link #SIZE_512}
 * {@link #SIZE_1024}
 * {@link #SIZE_2048}
 */
public enum KeySize {
    /**
     * Taille 512
     */
    SIZE_512(512),
    /**
     * Taille 1024
     */
    SIZE_1024(1024),
    /**
     * Taille 2048
     */
    SIZE_2048(2048);

    /**
     * La valeur de la taille
     */
    private final int value;

    /**
     * Constructeur
     *
     * @param value Valeur de la taille
     */
    KeySize(int value) {
        this.value = value;
    }

    /**
     * Retourne les informations de l'objet sous forme de chaîne de caractères
     *
     * @return Les informations de l'objet sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        return value + "";
    }

    /**
     * Retourne la taille
     *
     * @return La taille
     */
    public int value() {
        return value;
    }
}
