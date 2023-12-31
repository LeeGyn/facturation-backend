package com.etslyam.facturationbackend.utils.beans;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Interface d'une réponse simple sans donnée
 *
 * @author jyoboue
 * @version 1.0
 */
public interface Response<T> {
    /**
     * Retourne le code du statut du traitement
     *
     * @return Le statut du traitement
     */
    Status getStatus();

    /**
     * Définie le code du statut du traitement
     *
     * @param status Le statut du traitement
     */
    void setStatus(Status status);

    /**
     * Retourne le message du statut de traitement
     *
     * @return Le message décrivant le statut du traitement
     */
    String getMessage();

    /**
     * Définie le message du statut du traitement
     *
     * @param message Le message décrivant le statut du traitement
     */
    void setMessage(String message);

    String getPathFile();

    /**
     * Définie le message du statut du traitement
     *
     * @param message Le message décrivant le statut du traitement
     */
    void setPathFile(String message);

    /**
     * Retourne les données
     *
     * @return Les données
     */
    T getData();


    /**
     * Retourne les données
     *
     * @return Les données
     */
    List<T> getDatas();

    /**
     * Définie les données
     *
     * @param data Les données
     */
    void setData(T data);

    /**
     * Retourner la liste des erreurs de validation
     *
     * @return Les erreurs de validation
     */
    List<FieldError> getFieldErrors();

    /**
     * Définir la liste des erreurs de validation
     *
     * @param fieldErrors Les erreurs de validation
     */
    void setFieldErrors(List<FieldError> fieldErrors);
}
