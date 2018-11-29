package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieService {

	public Categorie addCategory(Categorie ca);
	
	public List<Categorie> getAllCategory();

	public int deleteCategory(Categorie ca);
	
	public Categorie getCategory(Categorie ca);
	
	public int updateCategory(Categorie ca);
	
}
