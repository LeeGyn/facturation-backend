package com.etslyam.facturationbackend.utils;

public class IPAddressValidationHandler {

    // Méthode pour valider une adresse IP (IPv4)
    public boolean isValidIPAddress(String ipAddress) {
        // Vérifier le format de l'adresse IP avec une expression régulière
        String ipAddressRegex = "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})$";
        if (!ipAddress.matches(ipAddressRegex)) {
            return false;
        }

        // Vérifier que chaque nombre dans l'adresse IP est compris entre 0 et 255
        String[] octets = ipAddress.split("\\.");
        for (String octet : octets) {
            int num = Integer.parseInt(octet);
            if (num < 0 || num > 255) {
                return false;
            }
        }

        return true;
    }
}
