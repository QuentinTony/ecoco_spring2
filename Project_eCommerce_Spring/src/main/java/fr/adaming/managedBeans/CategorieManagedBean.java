package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "caMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// transformation et injection de l'asso UML en JAVA
	@ManagedProperty(value = "#{caService}")
	private ICategorieService caService;

	@ManagedProperty(value = "#{prService}")
	private IProduitService prService;

	// setters obligatoires

	public void setCaService(ICategorieService caService) {
		this.caService = caService;
	}

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}

	// attributs

	private Categorie categorie;
	private Admin admin;
	HttpSession maSession;
	private List<Categorie> listeCategories;
	private UploadedFile file;

	// constructeur vide

	public CategorieManagedBean() {

	}

	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.categorie = new Categorie();
		this.listeCategories = caService.getAllCategory();
//
//		for (Categorie ca : listeCategories) {
//			List<Produit> liste = prService.getProductbyCategory(ca);
//			ca.setListeProduits(liste);
//		}

	}

	// getter setter

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	// les méthodes

	public String addLinkCategory() {
		return "addCategory";
	}

	public String addCategory() {
		this.categorie.setPhoto(file.getContents());
		Categorie caOut = caService.addCategory(this.categorie);
		if (caOut != null) {

			this.listeCategories = caService.getAllCategory();

			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas pu être effectué"));
			return "addCategory";
		}

	}

	public String deleteCategory() {
		int verif = caService.deleteCategory(this.categorie);
		if (verif != 0) {
			this.listeCategories = caService.getAllCategory();
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression n'a pas pu être effectuée"));
			return "accueilAdmin";
		}

	}

	public String getCategory() {

		Categorie caOut = caService.getCategory(this.categorie);

		if (caOut != null) {
			this.categorie = caOut;
			return "getCategory";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche n'a pas pu être effectuée"));
			return "accueilAdmin";
		}
	}

	public String updateLinkCategory() {
		return "updateCategory";
	}

	public String updateCategory() {

		if (file.getSize() > 0) {

			this.categorie.setPhoto(file.getContents());
		} else {
			Categorie caOut = caService.getCategory(categorie);
			categorie.setPhoto(caOut.getPhoto());
			categorie.setImage(caOut.getImage());
		}

		int verif = caService.updateCategory(this.categorie);

		if (verif != 0) {
			this.listeCategories = caService.getAllCategory();
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification n'a pas pu être effectuée"));
			return "updateCategory";
		}
	}

}
