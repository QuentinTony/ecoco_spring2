package fr.adaming.dao;

import fr.adaming.model.Client;

public interface IClientDao {

	public Client addClient(Client cl);
	
	public int deleteClient(Client cl);
	
	public int updateClient(Client cl);
	
	public Client getClient(Client cl);
	
	public Client isExist(Client cl);

}
