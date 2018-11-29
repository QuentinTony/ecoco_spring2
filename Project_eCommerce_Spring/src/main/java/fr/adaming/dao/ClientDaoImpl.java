package fr.adaming.dao;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client getClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client isExist(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

}
