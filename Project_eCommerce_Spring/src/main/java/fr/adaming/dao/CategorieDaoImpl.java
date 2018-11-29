package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection dépendance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Categorie addCategory(Categorie ca) {
		Session s = sf.getCurrentSession();
		s.persist(ca);
		return ca;
	}

	@Override
	public List<Categorie> getAllCategory() {
		Session s = sf.getCurrentSession();
		String req = "SELECT ca FROM Categorie ca ";
		Query query = s.createQuery(req);
		List<Categorie> listeCategorie =query.list();
		for (Categorie ca :listeCategorie) {
			ca.setImage("data:image/png;base64," + Base64.encodeBase64String(ca.getPhoto()));
		}
		return listeCategorie;
	}

	@Override
	public int deleteCategory(Categorie ca) {
		Session s = sf.getCurrentSession();
		Categorie caOut = (Categorie) s.get(Categorie.class, ca.getIdCategorie());
		if (caOut != null) {
			s.delete(caOut);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Categorie getCategory(Categorie ca) {
		Session s = sf.getCurrentSession();
		Categorie caOut = (Categorie) s.get(Categorie.class, ca.getIdCategorie());
		caOut.setImage("data:image/png;base64," + Base64.encodeBase64String(ca.getPhoto()));
		return caOut;	}

	@Override
	public int updateCategory(Categorie ca) {
		Session s = sf.getCurrentSession();
		String req = "UPDATE Categorie ca SET ca.nomCategorie=:pNomCategorie, ca.photo=:pPhoto, ca.description=:pDescription WHERE ca.idCategorie=:pIdCategorie";
		Query query = s.createQuery(req);
		query.setParameter("pNomCategorie", ca.getNomCategorie());
		query.setParameter("pPhoto", ca.getPhoto());
		query.setParameter("pDescription", ca.getDescription());
		query.setParameter("pIdCategorie", ca.getIdCategorie());
		return query.executeUpdate();
	}

}
