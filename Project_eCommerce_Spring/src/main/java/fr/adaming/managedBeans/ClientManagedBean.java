package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "clMB")
public class ClientManagedBean implements Serializable {

	// association UML
	@ManagedProperty(value = "#{clService}")
	private IClientService clService;

	@ManagedProperty(value = "#{prService}")
	private IProduitService pService;

	@ManagedProperty(value = "#{coService}")
	private ICommandeService coService;

	@ManagedProperty(value = "#{lcService}")
	private ILigneCommandeService lcService;

	// setters obligatoires

	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	public void setpService(IProduitService pService) {
		this.pService = pService;
	}

	public void setCoService(ICommandeService coService) {
		this.coService = coService;
	}

	public void setLcService(ILigneCommandeService lcService) {
		this.lcService = lcService;
	}

	// attributs

	private List<Produit> listeProduits;

	private List<Client> listeClients;
	HttpSession maSession;

	private Client client;

	// constructeurs vides
	public ClientManagedBean() {

	}

	@PostConstruct
	public void initClient() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.client = new Client();

	}

	// getters setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	// methode

	public String loginLinkClient() {

		Client clOut = (Client) maSession.getAttribute("clSession");

		if (clOut != null) {
			this.listeProduits = pService.getProductbyClient(clOut);
			maSession.setAttribute("listeProduitsSession", listeProduits);

			List<Commande> listeCommande = coService.getAllCommandes(clOut);

			List<LigneCommande> lcOut = new ArrayList<LigneCommande>();

			for (Commande co : listeCommande) {
				List<LigneCommande> lcIn = lcService.getAllLigneCommande(co);
				for (LigneCommande lc : lcIn) {
					lc.setCommande(co);
				}
				co.setLignesCommandes(lcIn);
				lcOut.addAll(lcIn);
			}

			clOut.setListeCommandes(listeCommande);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", clOut);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lcSession", lcOut);

			return "accueilClient";

		} else {
			return "loginClient";
		}

	}

	public String isExist() {
		Client clOut = clService.isExist(this.client);
		if (clOut != null) {
			this.listeProduits = pService.getProductbyClient(clOut);
			maSession.setAttribute("listeProduitsSession", listeProduits);

			List<Commande> listeCommande = coService.getAllCommandes(clOut);

			List<LigneCommande> lcOut = new ArrayList<LigneCommande>();

			for (Commande co : listeCommande) {
				List<LigneCommande> lcIn = lcService.getAllLigneCommande(co);
				for (LigneCommande lc : lcIn) {
					lc.setCommande(co);
				}
				co.setLignesCommandes(lcIn);
				lcOut.addAll(lcIn);
			}

			clOut.setListeCommandes(listeCommande);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", clOut);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lcSession", lcOut);

			return "accueilClient";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Identifiants invalides"));

			return "loginClient";

		}
	}

	public String addLinkClient() {
		return "addClient";
	}

	public String addClient() {
		Client clOut = clService.addClient(this.client);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �t� effectu�"));
			return "accueilSite";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas pu �tre effectu�"));
			return "addClient";
		}

	}

	public String deleteLinkClient() {

		return "deleteClient";

	}

	public String deleteClient() {
		int verif = clService.deleteClient(this.client);
		if (verif != 0) {

			return "accueilSite";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression n'a pas pu �tre effectu�e"));
			return "accueilSite";
		}
	}

	public String updateLinkClient() {
		return "updateClient";
	}

	public String updateClient() {
		int verif = clService.updateClient(this.client);
		if (verif != 0) {
			Client clOut = clService.getClient(client);
			maSession.setAttribute("clSession", clOut);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La modification n'a pas pu �tre effectu�e"));
			return "updateClient";
		}

	}

	public String getClient(Client cl) {
		Client clOut = clService.getClient(this.client);
		if (clOut != null) {
			this.client = clOut;
			return "getClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La recherche n'a pas pu �tre effectu�e"));
			return "ListeAdmin";
		}
	}

	public String deconnexion() {
		maSession.removeAttribute("clSession");
		maSession.removeAttribute("lcSession");
		return "accueilSite";
	}

}
