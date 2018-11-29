package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
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
		Session s = sf.getCurrentSession();
		s.persist(p);
		return p;
	}

	@Override
	public List<Produit> getProductbyClient(Client cl) {
		Session s = sf.getCurrentSession();
		String req = "SELECT p FROM Produit p WHERE p.client.id=:pIdcl";
		Query query = s.createQuery(req);
		query.setParameter("pIdcl", cl.getId());
		return query.list();
	}

	@Override
	public int deleteProduct(Produit p) {
		Session s = sf.getCurrentSession();
		try {
			s.delete(p);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public Produit getProduit(Produit p) {
		Session s = sf.getCurrentSession();
		Produit pOut = (Produit) s.get(Produit.class, p.getIdProduit());
		pOut.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		return pOut;
	}

	@Override
	public int updateProduit(Produit p) {
		Session s = sf.getCurrentSession();
		String req = "UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.photo=:pPhoto WHERE p.idProduit=:pIdProduit";
		Query query = s.createQuery(req);
		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pPhoto", p.getPhoto());
		query.setParameter("pIdProduit", p.getIdProduit());
		return query.executeUpdate();
	}

	@Override
	public List<Produit> getProductbyCategory(Categorie ca) {
		Session s = sf.getCurrentSession();
		String req = "SELECT p FROM Produit p WHERE p.categorie.id=:pIdca";
		Query query = s.createQuery(req);
		query.setParameter("pIdca", ca.getIdCategorie());
		List<Produit> liste = query.list();
		for (Produit p : liste) {
			p.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}
		return liste;
	}

	@Override
	public List<Produit> getProductbyString(String saisie) {
		Session s = sf.getCurrentSession();
		String req = "SELECT p FROM Produit as p WHERE p.designation LIKE :pSaisie";
		Query query = s.createQuery(req).setParameter("pSaisie", "%" + saisie + "%");
		List<Produit> liste = query.list();
		for (Produit p : liste) {
			p.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}
		return liste;
	}

	@Override
	public List<Produit> getProductbyDouble(double d1, double d2) {
		Session s = sf.getCurrentSession();
		String req = "SELECT p FROM Produit as p WHERE p.prix BETWEEN :pD1 AND :pD2";
		Query query = s.createQuery(req).setParameter("pD1", d1).setParameter("pD2", d2);
		List<Produit> liste = query.list();
		for (Produit p : liste) {
			p.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}
		return liste;
		
	}

	@Override
	public List<Produit> getProductbyCatAndString(String saisie, Categorie ca) {
		Session s = sf.getCurrentSession();
		String req = "SELECT p FROM Produit as p WHERE c_id=:pIdc AND p.designation LIKE :pSaisie";
		Query query = s.createQuery(req).setParameter("pSaisie", "%" + saisie + "%").setParameter("pIdc",
				ca.getIdCategorie());
		List<Produit> liste = query.list();
		for (Produit p : liste) {
			p.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}
		return liste;
	}

	@Override
	public List<Produit> getProductbyClAndString(String saisie, Client cl) {
		Session s = sf.getCurrentSession();
		String req = "SELECT p FROM Produit as p WHERE cl_id=:pIdcl AND p.designation LIKE :pSaisie";
		Query query = s.createQuery(req).setParameter("pSaisie", "%" + saisie + "%").setParameter("pIdcl", cl.getId());
		List<Produit> liste = query.list();
		for (Produit p : liste) {
			p.setImage("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}
		return liste;
	}

	@Override
	public List<Produit> getAllProducts() {
		Session s = sf.getCurrentSession();

		String req = "SELECT p FROM Produit p ";
		Query query = s.createQuery(req);
		return query.list();
	}

}
