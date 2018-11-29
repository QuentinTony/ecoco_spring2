package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection dépendance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Admin isExist(Admin a) {
		
		Session s = sf.getCurrentSession();
		
		String req = "FROM Admin as a WHERE a.mail=:pMail AND a.mdp=:pMdp";
		
		Query query = s.createQuery(req);
		
		return (Admin) query.uniqueResult();
	}

}
