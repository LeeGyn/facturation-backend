package com.etslyam.facturationbackend.utils.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Réponse sans donnée d'une requête
 *
 * @author jyoboue
 * @version 1.0
 */
@JsonRootName("response")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ResponseImpl<T> implements Response<T> {
    // Fields
    /**
     * Code du statut de traitement
     *
     * @see Response
     */
    protected Status status;

    /**
     * Message du statut de traitement
     *
     * @see Response
     */
    protected String message;

    /**
     * hasError du statut de traitement
     *
     * @see Response
     */
    protected boolean hasError;


    /**
     * count  de traitement
     *
     * @see Response
     */
    protected Long count;

    /**
     * @see Response
     */
    protected T data;

    /**
     * @see Response
     */
    protected List<T> datas;
    /**
     * @see Response
     */
    protected List<FieldError> fieldErrors;
    /**
     * @see Response
     */
    protected String pathFile;

    // Constructors
    /**
     * Constructeur
     *
     * @param status Statut du traitement
     * @param message Message décrivant le statut du traitement
     */
    public ResponseImpl(Status status, String message) {
        this.status = status;
        this.message = message;
        this.fieldErrors = new ArrayList<>();
    }

    /**
     * Constructeur
     *
     * @param status Statut du traitement
     * @param message Message décrivant le statut du traitement
     * @param data Données contenues dans la réponse
     */
    public ResponseImpl(Status status, String message, T data) {
        this(status, message);
        this.data = data;
    }

    /**
     * Constructeur
     *
     * @param status Statut du traitement
     * @param message Message décrivant le statut du traitement
     * @param fieldErrors Liste des erreurs de validation
     */
    public ResponseImpl(Status status, String message, List<FieldError> fieldErrors) {
        this(status, message);
        this.fieldErrors = fieldErrors;
    }


    // Public Methods
    /**
     * Ajouter une erreur de validation
     *
     * @param field Le champ sur lequel l'erreur s'est produite
     * @param rejectedValue La valeur qui a été rejetée
     * @param defaultMessage Le message de d'erreur
     */
    public void addFieldError(String field, Object rejectedValue, String defaultMessage) {
        fieldErrors.add(new FieldError(field, (String) rejectedValue, defaultMessage));
    }

    /**
     * @return
     */

    /**
     * Définir la liste des erreurs de validation
     *
     * @param fieldErrors Les erreurs de validation
     */
    @Override
    public void setFieldErrors(List<FieldError> fieldErrors) {

    }
}
