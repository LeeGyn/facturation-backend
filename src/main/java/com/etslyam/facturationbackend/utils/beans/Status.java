package com.etslyam.facturationbackend.utils.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Les différents code de statut retour
 *
 * {@link #PROCESSING}
 * {@link #SUCCESS}
 * {@link #INVALID_REQUEST}
 * {@link #UNAUTHORIZED}
 * {@link #NOT_AUTHORIZED}
 * {@link #NOT_FOUND}
 * {@link #NOT_ACCEPTABLE}
 * {@link #FAILED}
 * {@link #INTERNAL_ERROR}
 *
 * @author jyoboue
 * @version 1.0
 */
public enum Status {
	/**
	 * En cours de traitement
	 */
	PROCESSING(102),
	/**
	 * Traitement effectué avec succès
	 */
	SUCCESS(200),
	/**
	 * Requête invalide
	 */
	INVALID_REQUEST(400),
	/**
	 * Utilisateur authorisé (Utilisateur non autorisé à effectuer une action)
	 */
	UNAUTHORIZED(401),
	/**
	 * Action non autorisée (Utilisateur doit changer son mot de passe)
	 */
	NOT_AUTHORIZED(403),
	/**
	 * Element non trouvé
	 */
	NOT_FOUND(404),
	/**
	 * Action non acceptée (Token invalide)
	 */
	NOT_ACCEPTABLE(406),
	/**
	 * Bloqué (Utilisateur bloqué)
	 */
	BLOCKED(423),
	/**
	 * Traitement échoué
	 */
	FAILED(424),
	/**
	 * Erreur lors du traitement
	 */
	INTERNAL_ERROR(500),
	/**
	 * Requête vide
	 */
	EMPTY_REQUEST(432),
	/**
	 * Données dupliquées
	 */
	DATA_DUPLICATE(433),
	/**
	 * Champs vide
	 */
	FIELD_EMPTY(434),
	/**
	 * Enregistrement dans la BD
	 */
	FAIL_SAVE(435),
	/**
	 * Données existe
	 */
	DATA_EXIST(436),



	DISALLOWED_OPERATION(437),





	TIME_OUT(438),


	/**
	 * Données existe
	 */
	DATA_EMPTY(436),

	/**
	 * Données inexistante
	 */
	DATA_NOT_EXIST(439);
	/**;




    /**
	 * Valeur de la variable
	 */
	@JsonValue
	private final int value;

	/**
	 * Constructeur
	 *
	 * @param value Valeur de la variable
	 */
	Status(int value) {
		this.value = value;
	}

	/**
	 * Retourne la valeur actuelle de la variable sous forme d'entier
	 *
	 * @return Valeur de la variable
	 */
	public int value() {
		return value;
	}

	/**
	 * Retourne l'objet sous forme de chaîne de caractères
	 *
	 * @return Valeur de la variable sous forme de chaîne de caractères
	 */
	@Override
	public String toString() {
		return this.value + "";
	}



	/**
	 * Crée un objet (Enum) à partir d'un entier
	 *
	 * @param intValue Entier
	 *
	 * @return L'objet (Enum) à partir d'un entier
	 */
	@JsonCreator
	public static Status fromInt(int intValue) {
		if (intValue > 0) {
			for (Status objType : Status.values()) {
				if (intValue == objType.value()) {
					return objType;
				}
			}
		}

		return null;
	}

}
