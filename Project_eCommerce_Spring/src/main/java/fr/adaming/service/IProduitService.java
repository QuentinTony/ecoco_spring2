package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

public interface IProduitService {

	public Produit addProduct(Produit p, Client cl, Categorie cat);

	public List<Produit> getProductbyClient(Client cl);

	public int deleteProduct(Produit p, Client cl);

	public Produit getProduit(Produit p, Client cl);

	public Produit getProduit(Produit p);

	public int updateProduit(Produit p, Client cl, Categorie cat);

	public int updateProduit(Produit p);

	public List<Produit> getProductbyCategory(Categorie ca);

	public List<Produit> getProductbyString(String saisie);

	public List<Produit> getProductbyDouble(double d1, double d2);

	public List<Produit> getProductbyCatAndString(String saisie, Categorie ca);

	public List<Produit> getProductbyClAndString(String saisie, Client cl);

	public List<Produit> getProductbySaisieDouble(String saisie, double d1, double d2);

	public List<Produit> getProductbyCatDouble(double d1, double d2, Categorie ca);

	public List<Produit> getProductbyCatSaisieDouble(double d1, double d2, String saisie, Categorie ca);

	public List<Produit> getAllProducts();

}
