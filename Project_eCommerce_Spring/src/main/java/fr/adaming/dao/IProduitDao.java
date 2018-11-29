package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

public interface IProduitDao {
	
	public Produit addProduct(Produit p);
	
	public List<Produit> getProductbyClient(Client cl);

	public int deleteProduct(Produit p);
	
	public Produit getProduit(Produit p);
	
	public int updateProduit(Produit p);
	
	public List<Produit> getProductbyCategory (Categorie ca);
	
	public List<Produit> getProductbyString (String saisie);
	
	public List<Produit> getProductbyDouble (double d1,double d2);
	
	public List<Produit> getProductbyCatAndString (String saisie,Categorie ca);
	
	public List<Produit> getProductbyClAndString (String saisie,Client cl);
	
	public List<Produit> getAllProducts();
}
