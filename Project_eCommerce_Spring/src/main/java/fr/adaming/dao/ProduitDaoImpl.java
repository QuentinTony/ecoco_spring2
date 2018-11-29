package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection dépendance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Produit addProduct(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductbyClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProduct(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduit(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProduit(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Produit> getProductbyCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductbyString(String saisie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductbyDouble(double d1, double d2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductbyCatAndString(String saisie, Categorie ca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductbyClAndString(String saisie, Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
