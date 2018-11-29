package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service(value = "caService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {

	// transformation de l'association UML en Java
	@Autowired
	public ICategorieDao caDao;

	// setter

	public void setCaDao(ICategorieDao caDao) {
		this.caDao = caDao;
	}

	@Override
	public Categorie addCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCategory(Categorie ca) {
		// TODO Auto-generated method stub
		return 0;
	}

}
