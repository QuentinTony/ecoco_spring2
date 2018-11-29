package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Service(value="prService")
@Transactional
public class ProduitServiceImpl implements IProduitService {

	// transformation de l'association UML en Java
	@Autowired
	public IProduitDao pDao;
	
	// setter

	public void setpDao(IProduitDao pDao) {
		this.pDao = pDao;
	}

	@Override
	public Produit addProduct(Produit p, Client cl, Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductbyClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProduct(Produit p, Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduit(Produit p, Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit getProduit(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProduit(Produit p, Client cl, Categorie cat) {
		// TODO Auto-generated method stub
		return 0;
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
