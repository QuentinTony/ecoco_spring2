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
		return caDao.addCategory(ca);
	}

	@Override
	public List<Categorie> getAllCategory() {
		return caDao.getAllCategory();
	}

	@Override
	public int deleteCategory(Categorie ca) {
		return caDao.deleteCategory(ca);
	}

	@Override
	public Categorie getCategory(Categorie ca) {
		return caDao.getCategory(ca);
	}

	@Override
	public int updateCategory(Categorie ca) {
		Categorie caOut = this.getCategory(ca);
		caOut.setDescription(ca.getDescription());
		caOut.setNomCategorie(ca.getNomCategorie());
		caOut.setPhoto(ca.getPhoto());
		
		return caDao.updateCategory(caOut);
	}

}
