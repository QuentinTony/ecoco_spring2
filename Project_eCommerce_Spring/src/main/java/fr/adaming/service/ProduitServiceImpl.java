package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Service(value = "prService")
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
		p.setCategorie(cat);
		p.setClient(cl);
		return pDao.addProduct(p);
	}

	@Override
	public List<Produit> getProductbyClient(Client cl) {
		return pDao.getProductbyClient(cl);

	}

	@Override
	public int deleteProduct(Produit p, Client cl) {
		p.setClient(cl);
		return pDao.deleteProduct(p);
	}

	@Override
	public Produit getProduit(Produit p, Client cl) {
		p.setClient(cl);
		return pDao.getProduit(p);
	}

	@Override
	public Produit getProduit(Produit p) {
		return pDao.getProduit(p);
	}

	@Override
	public int updateProduit(Produit p, Client cl, Categorie cat) {
		Produit pOut = this.getProduit(p);
		pOut.setDescription(p.getDescription());
		pOut.setDesignation(p.getDesignation());
		pOut.setPrix(p.getPrix());
		pOut.setPhoto(p.getPhoto());
		pOut.setLien(p.getLien());
		pOut.setQuantite(p.getQuantite());
		pOut.setClient(cl);
		pOut.setCategorie(cat);
		return pDao.updateProduit(pOut);
	}

	@Override
	public int updateProduit(Produit p) {
		Produit pOut = this.getProduit(p);
		pOut.setDescription(p.getDescription());
		pOut.setDesignation(p.getDesignation());
		pOut.setPrix(p.getPrix());
		pOut.setPhoto(p.getPhoto());
		pOut.setQuantite(p.getQuantite());
		pOut.setTextePub(p.getTextePub());
		pOut.setPub(p.getPub());
		return pDao.updateProduit(pOut);
	}

	@Override
	public List<Produit> getProductbyCategory(Categorie ca) {
		return pDao.getProductbyCategory(ca);

	}

	@Override
	public List<Produit> getProductbyString(String saisie) {
		return pDao.getProductbyString(saisie);

	}

	@Override
	public List<Produit> getProductbyDouble(double d1, double d2) {
		return pDao.getProductbyDouble(d1, d2);
	}

	@Override
	public List<Produit> getProductbyCatAndString(String saisie, Categorie ca) {
		return pDao.getProductbyCatAndString(saisie, ca);

	}

	@Override
	public List<Produit> getProductbyClAndString(String saisie, Client cl) {
		return pDao.getProductbyClAndString(saisie, cl);

	}

	@Override
	public List<Produit> getAllProducts() {
			return pDao.getAllProducts();
	}

	@Override
	public List<Produit> getProductbySaisieDouble(String saisie, double d1, double d2) {
		
		return pDao.getProductbySaisieDouble(saisie, d1, d2);
	}

	@Override
	public List<Produit> getProductbyCatDouble(double d1, double d2, Categorie ca) {
		
		return pDao.getProductbyCatDouble(d1, d2, ca);
	}

	@Override
	public List<Produit> getProductbyCatSaisieDouble(double d1, double d2, String saisie, Categorie ca) {
		
		return pDao.getProductbyCatSaisieDouble(d1, d2, saisie, ca);
	}

	@Override
	public List<Produit> getProductByPub() {
	
		return pDao.getProductByPub();
	}

	@Override
	public int deleteProduct1(Produit p) {
		
		return pDao.deleteProduct(p);
	}

}
