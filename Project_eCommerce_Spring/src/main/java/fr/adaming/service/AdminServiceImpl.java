package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Service(value="aService")
@Transactional
public class AdminServiceImpl implements IAdminService{

	// transformation de l'association UML
	
	@Autowired
	private IAdminDao aDao;

	// setter pour l'injection de dépendance

	public void setaDao(IAdminDao aDao) {
		this.aDao = aDao;
	}

	@Override
	public Admin isExist(Admin a) {
		
		return aDao.isExist(a);
	}

}
