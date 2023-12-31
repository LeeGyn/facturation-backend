package com.etslyam.facturationbackend.utils;

public class MacAddressValidationHandler {

    // Méthode pour valider une adresse MAC
    public boolean isValidMacAddress(String macAddress) {

        if (macAddress == null) {
            return false;
        }

        // Convertir la chaîne en lettres majuscules pour la vérification
        String macAddresse = macAddress.toUpperCase();

        // Vérifier le format de l'adresse MAC avec une expression régulière
        String macAddressRegex = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
        if (!macAddress.matches(macAddressRegex)) {
            return false;
        }

        // Vérifier que tous les caractères sont valides (chiffres, lettres hexadécimales en majuscules et tirets)
        for (char c : macAddress.toCharArray()) {
            // Vérifier si le caractère est un chiffre, une lettre hexadécimale en majuscules ou le caractère '-'
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || c == '-')) {
                return false;
            }
        }

        /*// Vérifier que tous les caractères sont valides (chiffres et lettres hexadécimales)
        for (char c : macAddresse.toCharArray()) {
            if (!isHexadecimalCharacter(c)) {
                return false;
            }
        }*/

        return true;
    }

    // Méthode pour vérifier si un caractère est un chiffre ou une lettre hexadécimale
    private boolean isHexadecimalCharacter(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

}
