package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Service(value="lcService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService{

	// association UML
	@Autowired
	private ILigneCommandeDao lcDao;
	
	// setter pour injection de dépendance

	public void setLcDao(ILigneCommandeDao lcDao) {
		this.lcDao = lcDao;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc, Commande co) {
		lc.setCommande(co);
		return lcDao.addLigneCommande(lc);
	}

	@Override
	public int deleteLigneCommande(LigneCommande lc, Commande co, Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLigneCommande(LigneCommande lc, Commande co, Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lc, Commande co, Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LigneCommande> getAllLigneCommande(Commande co) {
		
		return lcDao.getAllLigneCommande(co);
	}
	


	
	
}
