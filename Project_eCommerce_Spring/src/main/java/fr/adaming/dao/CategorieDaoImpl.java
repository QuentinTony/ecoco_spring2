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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return 0;
	}

}
