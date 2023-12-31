package com.etslyam.facturationbackend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLInjectionChecker {

    public static boolean isSQLInjection(String input) {
        // Liste de mots-clés associés aux injections SQL
        String[] sqlKeywords = {"SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "EXEC",
                "UNION", "ALL", "ALTER", "CREATE", "MERGE", "TRUNCATE"};

        // Construire la regex pour rechercher les mots-clés
        String regex = "\\b(" + String.join("|", sqlKeywords) + ")\\b";

        // Utiliser Pattern et Matcher pour rechercher la présence de mots-clés dans la chaîne d'entrée
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

}
