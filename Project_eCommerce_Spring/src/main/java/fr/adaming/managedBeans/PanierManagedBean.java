package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "paMB")
public class PanierManagedBean {

	// association UML

	@ManagedProperty(value = "#{lcService}")
	private ILigneCommandeService lcService;

	@ManagedProperty(value = "#{prService}")
	private IProduitService pService;

	// getter setter

	public void setLcService(ILigneCommandeService lcService) {
		this.lcService = lcService;
	}

	public void setpService(IProduitService pService) {
		this.pService = pService;
	}

	// attributs

	private Panier panier;

	private Produit produit;

	private int quantite;

	private LigneCommande lcDelete;

	HttpSession maSession;

	// constructeur

	public PanierManagedBean() {

	}

	@PostConstruct
	public void initPanier() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Panier pOut = (Panier) maSession.getAttribute("paSession");
		if (pOut != null) {
			this.panier = pOut;

		} else {

			List<LigneCommande> liste = new ArrayList<LigneCommande>();

			this.panier = new Panier();
			panier.setListeLigneCommandes(liste);

			maSession.setAttribute("paSession", panier);
		}
		this.produit = new Produit();

	}

	// getter setter

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public LigneCommande getLcDelete() {
		return lcDelete;
	}

	public void setLcDelete(LigneCommande lcDelete) {
		this.lcDelete = lcDelete;
	}

	// m�thodes

	public void vQuantite(FacesContext context, UIComponent composant, Object valeur) throws ValidatorException {

		String saisie = Integer.toString(quantite);
		int longueur = saisie.length();
		String nb = "01234565789";

		for (int i = 0; i < longueur; i++) {
			String test = Character.toString(saisie.charAt(i));

			if (!nb.contains(test)) {
				throw new ValidatorException(new FacesMessage("La quantit� est un nombre !"));
			}
		}

	}

	public String addToPanier() {

		LigneCommande lc = new LigneCommande(produit.getDesignation(),quantite, produit.getPrix() * quantite);
		lc.setProduit(produit);
		List<LigneCommande> liste = panier.getListeLigneCommandes();
		LigneCommande lcErreur = new LigneCommande();

		int i = 0;
		boolean modif = false;
		for (LigneCommande lcElem : liste) {
			if (lc.getProduit().equalObjet(lcElem.getProduit())) {
				lcErreur = new LigneCommande(lcElem.getNomProduit(),lcElem.getQuantite(), lcElem.getPrix());
				lcErreur.setProduit(lcElem.getProduit());

				lc.setPrix(lc.getPrix() + lcElem.getPrix());
				lc.setQuantite(lc.getQuantite() + lcElem.getQuantite());

				liste.set(i, lc);
				modif = true;
				break;
			}

			i++;
		}

		if (modif == false) {
			liste.add(lc);
		}

		if (lc.getProduit().getQuantite() >= lc.getQuantite()) {

			panier.setListeLigneCommandes(liste);

			double prixTot = 0;
			for (LigneCommande lcElem : liste) {
				prixTot = prixTot + lcElem.getPrix();
			}

			panier.setPrixTotal(prixTot);

			maSession.setAttribute("paSession", this.panier);

			return this.afficherProduits();

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur ! Le stock de ce produit est de : " + lcErreur.getProduit().getQuantite()
							+ "\nD�j� " + lcErreur.getQuantite() + " dans votre panier !"));

			liste.set(i, lcErreur);

			double prixTot = 0;
			for (LigneCommande lcElem : liste) {
				prixTot = prixTot + lcElem.getPrix();
			}

			panier.setListeLigneCommandes(liste);
			panier.setPrixTotal(prixTot);

			maSession.setAttribute("paSession", this.panier);

			return "afficherOneProduit";
		}

	}

	public String deleteLigne() {

		List<LigneCommande> lcIn = panier.getListeLigneCommandes();
		for (LigneCommande lc : lcIn) {
			if (lc.getPrix() == lcDelete.getPrix() && lc.getQuantite() == lcDelete.getQuantite()
					&& lc.getProduit().getDesignation().equals(lcDelete.getProduit().getDesignation())) {
				lcIn.remove(lc);
				break;
			}
		}
		panier.setListeLigneCommandes(lcIn);

		double prixTot = 0;
		for (LigneCommande lcElem : lcIn) {
			prixTot = prixTot + lcElem.getPrix();
		}

		panier.setPrixTotal(prixTot);

		maSession.setAttribute("paSession", panier);

		return this.afficherAccueil();
	}

	public String afficherAccueil() {

		quantite = 0;

		return "accueilSite";
	}

	public String afficherProduits() {

		quantite = 0;

		return "afficherProduits";
	}

	public void onToggle(ToggleEvent event) {
		Visibility visibility = event.getVisibility();
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Fieldset " + event.getPhaseId() + " toggled");
		msg.setDetail("Visibility: " + visibility);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		 }
}
