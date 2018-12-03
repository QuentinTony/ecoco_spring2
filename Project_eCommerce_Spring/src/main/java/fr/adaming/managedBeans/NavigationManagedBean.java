package fr.adaming.managedBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navMB")
public class NavigationManagedBean {

	// attributs

	private int indiceNav;

	// constructeur

	public NavigationManagedBean() {
		super();
	}

	// getter setter

	public int getIndiceNav() {
		return indiceNav;
	}

	public void setIndiceNav(int indiceNav) {
		this.indiceNav = indiceNav;
	}

	// méthodes

	public String retour() {

		String nav = "";
		System.out.println(indiceNav);
		switch (indiceNav) {
		case 0:
			nav = "afficherProduits";
			break;

		case 1:
			nav = "afficherProduitsPrix";
			break;

		case 2:
			nav = "afficherProduitsSaisie";
			break;

		case 3:
			nav = "afficherProduitsSaisieCat";
			break;

		case 4:
			nav = "getAllProducts";
			break;

		case 5:
			nav = "rechercheAvancee";
			break;

		case 6:
			nav = "accueilSite";
			break;

		default:
			break;
		}

		return nav;

	}

}
