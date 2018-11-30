package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pubMB")
public class PubManagedBean implements Serializable {

	// association UML
	@ManagedProperty(value = "#{prService}")
	private IProduitService prService;

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}

	// attributs

	private List<Produit> listeProduits;
	private List<Produit> listeProduitsChoisis;
	HttpSession maSession;
	private String saisie;
	private String textePublicite;

	// constructeur vide

	public PubManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		List<Produit> liste = prService.getProductByPub();
		maSession.setAttribute("listeProduitsPub", liste);
	}

	// getter setter

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public String getTextePublicite() {
		return textePublicite;
	}

	public void setTextePublicite(String textePublicite) {
		this.textePublicite = textePublicite;
	}

	public String getSaisie() {
		return saisie;
	}

	public void setSaisie(String saisie) {
		this.saisie = saisie;
	}

	public List<Produit> getListeProduitsChoisis() {
		return listeProduitsChoisis;
	}

	public void setListeProduitsChoisis(List<Produit> listeProduitsChoisis) {
		this.listeProduitsChoisis = listeProduitsChoisis;
	}

	// m�thodes

	public String getProductbySaisie() {

		listeProduits = prService.getProductbyString(saisie);
		maSession.setAttribute("listeProduitsSession", listeProduits);
		return "updatePub";
	}

	public String ajouterDansBandeau() {
		int verif = 0;
		List<Produit> liste = prService.getProductByPub();
		
		if (liste.size() + listeProduitsChoisis.size() < 4) {
			
			for (Produit pr : listeProduitsChoisis) {
				
				Produit prOut = prService.getProduit(pr);
				prOut.setPub(true);
				if (!textePublicite.equals("")) {
					prOut.setTextePub(textePublicite);
				} else if (prOut.getTextePub() != null) {
					prOut.setTextePub(prOut.getTextePub());
				} else {
					prOut.setTextePub(prOut.getDescription());
				}
				int verif1 = prService.updateProduit(prOut);
				if (verif1 == 0) {
					verif = 0;
					break;
				} else {
					verif = 1;
				}
			}

			if (verif != 0) {
				
				return "updatePub";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("La mise � jour du bandeau a �chou�"));
				return "updatePub";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Le nombre de produits dans le bandeau est limit� � 3"));
			return "updatePub";
		}

	}
	
	public String deleteLigne() {
		
		
		
		return "updatePub";
	}

}
