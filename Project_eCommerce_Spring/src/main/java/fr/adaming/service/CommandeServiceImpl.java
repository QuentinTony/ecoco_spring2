package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Service(value="coService")
@Transactional
public class CommandeServiceImpl implements ICommandeService {

	// association UML
	
	@Autowired
	private ICommandeDao coDao;
	
	// setter pr l'injection

	public void setCoDao(ICommandeDao coDao) {
		this.coDao = coDao;
	}

	@Override
	public Commande addCommande(Commande co, Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCommande(Commande co, Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Commande getCommande(Commande co, Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> getAllCommandes(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
