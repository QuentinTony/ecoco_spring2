package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service(value="clService")
@Transactional
public class ClientServiceImpl implements IClientService {

	// association UML
	@Autowired
	private IClientDao clDao;

	// setter

	public void setClDao(IClientDao clDao) {
		this.clDao = clDao;
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

	@Override
	public Client addClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}



}
