package com.etslyam.facturationbackend.utils.beans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64Methodes {
    public static String convertirPhotoEnBase64(byte[] photo) {
        return Base64.getEncoder().encodeToString(photo);
    }
    public static boolean estBase64Image(String chaine) {
        boolean result = false;
        try {
            // Vérifier la taille
            if (chaine.length() < 4) {
                return false; // La chaîne Base64 devrait avoir au moins 4 caractères
            }

            // Décodez la chaîne Base64 pour vérifier sa validité
            byte[] decode = Base64.getDecoder().decode(chaine);

            // Vérifier la taille après le décodage
            if (decode.length == 0) {
                return false; // La chaîne décodée ne doit pas être vide
            }

            // Vérifier la présence de caractères non-valides
            if (!chaine.matches("^[A-Za-z0-9+/=]+$")) {
                return false; // La chaîne contient des caractères non-valides pour Base64
            }

            // Vérifier si la chaîne représente une image
            if (estImageJPEG(decode) || estImagePNG(decode)) {
                return true;
            }

            return false;
        } catch (Exception e) {
            // Une exception indique que la chaîne n'est pas en Base64
            return false;
        }
    }
    private static boolean estImageJPEG(byte[] donnees) {
        try (InputStream inputStream = new ByteArrayInputStream(donnees)) {
            BufferedImage image = ImageIO.read(inputStream);
            return image != null && "jpeg".equalsIgnoreCase((String) image.getProperty("formatName"));
        } catch (IOException e) {
            return false;
        }
    }
    private static boolean estImagePNG(byte[] donnees) {
        try (InputStream inputStream = new ByteArrayInputStream(donnees)) {
            BufferedImage image = ImageIO.read(inputStream);
            return image != null && "png".equalsIgnoreCase((String) image.getProperty("formatName"));
        } catch (IOException e) {
            return false;
        }
    }
}
