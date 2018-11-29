package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		
		Session s = sf.getCurrentSession();
		
		s.save(co);
		
		return co;
	}

	@Override
	public int deleteCommande(Commande co) {
		
		Session s = sf.getCurrentSession();
		
		String req = "DELETE FROM Commande as co WHERE co.id=:pIdco AND co.client.id=:pIdcl";
		
		Query query = s.createQuery(req);
		query.setParameter("pIdco", co.getId());
		query.setParameter("pIdcl", co.getClient().getId());
		
		return query.executeUpdate();
	}

	@Override
	public Commande getCommande(Commande co) {
		
		Session s = sf.getCurrentSession();
		
		return (Commande) s.get(Commande.class, co.getId());
	}

	@Override
	public List<Commande> getAllCommandes(Client cl) {
		
		Session s = sf.getCurrentSession();
		
		String req = "FROM Commande as co WHERE co.client.id=:pIdcl";
		
		Query query = s.createQuery(req);
		query.setParameter("pIdcl", cl.getId());
		
		return query.list();
	}

}
