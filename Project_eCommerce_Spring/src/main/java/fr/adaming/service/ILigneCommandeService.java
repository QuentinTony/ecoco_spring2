package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

public interface ILigneCommandeService {

	public LigneCommande addLigneCommande(LigneCommande lc, Commande co);

	public int deleteLigneCommande(LigneCommande lc, Commande co, Client cl);

	public int updateLigneCommande(LigneCommande lc, Commande co, Client cl);

	public LigneCommande getLigneCommande(LigneCommande lc, Commande co, Client cl);

	public List<LigneCommande> getAllLigneCommande(Commande co);

}
