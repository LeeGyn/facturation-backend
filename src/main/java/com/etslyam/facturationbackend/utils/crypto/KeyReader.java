package com.etslyam.facturationbackend.utils.crypto;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Classe permettant de charger le contenant d'un fichier de clé privée ou publique
 *
 * @author jfyoboue
 * @version 1.0
 */
public class KeyReader {
    /**
     * Charger un fichier de clé privée
     *
     * @param fileName Chemin vers le fichier de clé privée
     *
     * @return La clé privée
     *
     * @throws FileNotFoundException @see {@link FileNotFoundException}
     * @throws IOException @see {@link IOException}
     * @throws NoSuchAlgorithmException @see {@link NoSuchAlgorithmException}
     * @throws InvalidKeySpecException @see {@link InvalidKeySpecException}
     */
    public static PrivateKey getPrivateKey(String fileName) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        File file = new File(fileName);
        return getPrivateKey(file);
    }

    /**
     * Charger un fichier de clé privée
     *
     * @param file Fichier de clé privée
     *
     * @return La clé privée
     *
     * @throws FileNotFoundException @see {@link FileNotFoundException}
     * @throws IOException @see {@link IOException}
     * @throws NoSuchAlgorithmException @see {@link NoSuchAlgorithmException}
     * @throws InvalidKeySpecException @see {@link InvalidKeySpecException}
     */
    public static PrivateKey getPrivateKey(File file) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)file.length()];
        dis.readFully(keyBytes);
        dis.close();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    /**
     * Charger un fichier de clé publique
     *
     * @param fileName Chemin vers le fichier publique
     *
     * @return La clé publique
     *
     * @throws FileNotFoundException @see {@link FileNotFoundException}
     * @throws IOException @see {@link IOException}
     * @throws NoSuchAlgorithmException @see {@link NoSuchAlgorithmException}
     * @throws InvalidKeySpecException @see {@link InvalidKeySpecException}
     */
    public static PublicKey getPublicKey(String fileName) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        File file = new File(fileName);
        return getPublicKey(file);
    }

    /**
     * Charger un fichier de clé publique
     *
     * @param file Fichier de clé publique
     *
     * @return La clé publique
     *
     * @throws FileNotFoundException @see {@link FileNotFoundException}
     * @throws IOException @see {@link IOException}
     * @throws NoSuchAlgorithmException @see {@link NoSuchAlgorithmException}
     * @throws InvalidKeySpecException @see {@link InvalidKeySpecException}
     */
    public static PublicKey getPublicKey(File file) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)file.length()];
        dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        //PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}
