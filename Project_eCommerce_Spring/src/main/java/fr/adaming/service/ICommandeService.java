package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeService {

	public Commande addCommande(Commande co, Client cl);

	public int deleteCommande(Commande co, Client cl);

	public Commande getCommande(Commande co, Client cl);

	public List<Commande> getAllCommandes(Client cl);

}
