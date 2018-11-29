package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

public interface ILigneCommandeDao {

	public LigneCommande addLigneCommande(LigneCommande lc);

	public int deleteLigneCommande(LigneCommande lc);

	public int updateLigneCommande(LigneCommande lc);

	public LigneCommande getLigneCommande(LigneCommande lc);

	public List<LigneCommande> getAllLigneCommande(Commande co);

}
