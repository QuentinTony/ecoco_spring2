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
		Client clOut = this.getClient(cl);

		return clDao.deleteClient(clOut);

	}

	@Override
	public int updateClient(Client cl) {
		Client clOut = this.getClient(cl);
		clOut.setId(cl.getId());
		clOut.setAdresse(cl.getAdresse());
//		clOut.setListeCommandes(cl.getListeCommandes());
		clOut.setMail(cl.getMail());
		clOut.setMdp(cl.getMdp());
		clOut.setNom(cl.getNom());
		clOut.setTel(cl.getTel());
		
		return clDao.updateClient(clOut);
	}

	@Override
	public Client getClient(Client cl) {
		return clDao.getClient(cl);
	}

	@Override
	public Client isExist(Client cl) {
		// TODO Auto-generated method stub
		return clDao.isExist(cl);
	}

	@Override
	public Client addClient(Client cl) {
		return clDao.addClient(cl);

	}



}
