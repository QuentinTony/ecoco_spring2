package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection dépendance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Commande addCommande(Commande co) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCommande(Commande co) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Commande getCommande(Commande co) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> getAllCommandes(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

}
