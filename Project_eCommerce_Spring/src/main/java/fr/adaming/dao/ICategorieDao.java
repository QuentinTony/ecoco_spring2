package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieDao {
	
	public Categorie addCategory(Categorie ca);
	
	public List<Categorie> getAllCategory();

	public int deleteCategory(Categorie ca);
	
	public Categorie getCategory(Categorie ca);
	
	public int updateCategory(Categorie ca);
	

}
