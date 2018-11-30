package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "produits")
public class Produit {

	// les attributs

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_p")
	private long idProduit;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	private boolean selectionne;
	private String lien;
	private String textePub;
	private boolean pub;
	@Lob
	private byte[] photo;
	@Transient
	private String image;

	// les attributs==> les associations
	@ManyToOne
	@JoinColumn(name = "c_id", referencedColumnName = "id_ca")
	private Categorie categorie;

	@OneToMany(mappedBy = "produit")
	private List<LigneCommande> listeLignes;

	@ManyToOne
	@JoinColumn(name = "cl_id", referencedColumnName = "id_cl")
	private Client client;

	// constructeurs vides

	public Produit() {
		super();
	}

	public Produit(String designation, String description, double prix, int quantite, boolean selectionne, String lien,
			byte[] photo, String image) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.lien = lien;
		this.photo = photo;
		this.image = image;
	}

	public Produit(long idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, String lien, byte[] photo, String image) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.lien = lien;
		this.photo = photo;
		this.image = image;
	}

	// getters et setters
	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public boolean getPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public String getTextePub() {
		return textePub;
	}

	public void setTextePub(String textePub) {
		this.textePub = textePub;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<LigneCommande> getListeLignes() {
		return listeLignes;
	}

	public void setListeLignes(List<LigneCommande> listeLignes) {
		this.listeLignes = listeLignes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// ToString
	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", selectionne=" + selectionne + ", photo="
				+ Arrays.toString(photo) + "]";
	}

	public boolean equalObjet(Object obj) {

		return (obj instanceof Produit) && ((Produit) obj).getDesignation().equals(designation)
				&& ((Produit) obj).getDescription().equals(description) && ((Produit) obj).getPrix() == prix;
	}

}
