package fr.adaming.dao;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao {

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection dépendance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Client addClient(Client cl) {
		Session s = sf.getCurrentSession();
		s.persist(cl);
		return cl;
	}

	@Override
	public int deleteClient(Client cl) {
		Session s = sf.getCurrentSession();
		try {
			s.delete(cl);
			return 1;
		} catch (Exception ex) {
			return 0;
		}
	
	}

	@Override
	public int updateClient(Client cl) {
		Session s = sf.getCurrentSession();
		try {
			s.saveOrUpdate(cl);
			return 1;
		} catch (Exception ex) {
			return 0;
		}
	}

	@Override
	public Client getClient(Client cl) {
		Session s = sf.getCurrentSession();
		try {
			Client clOut = (Client) s.get(Client.class, cl.getId());
			return clOut;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Client isExist(Client cl) {
		Session s = sf.getCurrentSession();

		String req = "SELECT cl FROM Client as cl WHERE cl.mail=:pMail AND cl.mdp=:pMdp";

		// création du query
		try {
			Query query = s.createQuery(req);
			query.setParameter("pMail", cl.getMail());
			query.setParameter("pMdp", cl.getMdp());
			return (Client) query.uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}

}
