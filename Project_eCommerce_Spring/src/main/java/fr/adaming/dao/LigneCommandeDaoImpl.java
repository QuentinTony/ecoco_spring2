package fr.adaming.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection dépendance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LigneCommande> getAllLigneCommande(Commande co) {
		// TODO Auto-generated method stub
		return null;
	}

}
