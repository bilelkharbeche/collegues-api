package dev.exemple.controller.DTO;

public class CollegueDtoPhoto {

	private String matricule;
	private String photoUrl;

	/**
	 * Constructor
	 * 
	 */
	public CollegueDtoPhoto() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param matricule
	 * @param photoUrl
	 */
	public CollegueDtoPhoto(String matricule, String photoUrl) {
		super();
		this.matricule = matricule;
		this.photoUrl = photoUrl;
	}

	/**
	 * Getter
	 * 
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Setter
	 * 
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * Getter
	 * 
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * Setter
	 * 
	 * @param photoUrl
	 *            the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
