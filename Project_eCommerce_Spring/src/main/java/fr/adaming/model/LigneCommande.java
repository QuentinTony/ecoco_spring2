package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lignes")
public class LigneCommande {

	// attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_l")
	private long id;

	private String nomProduit;
	private int quantite;
	private double prix;

	// association UML

	@ManyToOne
	@JoinColumn(name = "p_id", referencedColumnName = "id_p")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "co_id", referencedColumnName = "id_co")
	private Commande commande;

	// constructeurs

	public LigneCommande(long id, String nomProduit, int quantite, double prix) {
		super();
		this.id = id;
		this.nomProduit = nomProduit;
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(String nomProduit, int quantite, double prix) {
		super();
		this.nomProduit = nomProduit;
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande() {
		super();
	}

	// getter setter
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", nomProduit=" + nomProduit + ", quantite=" + quantite + ", prix=" + prix
				+ "]";
	}
	
	
}
