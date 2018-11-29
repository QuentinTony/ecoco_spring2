package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.DocumentException;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;
import fr.adaming.util.MailClass;
import fr.adaming.util.PdfClass;

@ManagedBean(name = "coMB")
public class CommandeManagedBean implements Serializable {

	// association UML

	@ManagedProperty(value = "#{clService}")
	private IClientService clService;

	@ManagedProperty(value = "#{coService}")
	private ICommandeService coService;

	@ManagedProperty(value = "#{lcService}")
	private ILigneCommandeService lcService;

	@ManagedProperty(value = "#{prService}")
	private IProduitService prService;

	// setters obligatoires

	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	public void setCoService(ICommandeService coService) {
		this.coService = coService;
	}

	public void setLcService(ILigneCommandeService lcService) {
		this.lcService = lcService;
	}

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}

	// attribut

	private Commande commande;

	private Client client;

	private Panier panier;

	private List<LigneCommande> listeLigneCommande;

	HttpSession maSession;

	// constructeur

	public CommandeManagedBean() {

	}

	@PostConstruct
	public void initPanier() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		panier = (Panier) maSession.getAttribute("paSession");

		listeLigneCommande = panier.getListeLigneCommandes();

		client = new Client();

		commande = new Commande();
	}

	// getter setter

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	// m�thodes

	public String annulerCommande() {
		return "accueilSite";
	}

	public String passerCommande1() {

		Client clOut = (Client) maSession.getAttribute("clSession");

		if (clOut != null) {
			return "afficherRecapCommande";
		}

		return "loginClientAchat";
	}

	public String passerCommande2() {

		// test si client est inscrit
		Client clOut = clService.isExist(client);

		if (clOut != null) {
			maSession.setAttribute("clSession", clOut);
			return "afficherRecapCommande";
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Identifiants invalides"));

			return "loginClientAchat";
		}
	}

	public String passerCommande() {

		Client clOut = (Client) maSession.getAttribute("clSession");

		// instanciation de la nouvelle commande ac la date du jour
		commande = new Commande(new Date());
		// ajout de la commande � la BD
		Commande coIn = coService.addCommande(commande, clOut);

		if (coIn != null) {
			// boucle pour ajouter chaque ligneCommande � la DB et mettre � jour le stock
			// produit
			int[] tabVerif = new int[listeLigneCommande.size()];
			int i = 0;
			int verif = 0;
			for (LigneCommande lc : listeLigneCommande) {
				LigneCommande lcIn = lcService.addLigneCommande(lc, coIn);
				if (lcIn != null) {
					Produit pIn = lcIn.getProduit();
					pIn.setQuantite(pIn.getQuantite() - lcIn.getQuantite());
					tabVerif[i] = prService.updateProduit(pIn);

					if (tabVerif[i] != 0) {
						verif = 1;
					} else {
						verif = 0;
						break;
					}

					i++;
				} else {

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Erreur d'ajout de la ligne de commande"));

					return "loginClientAchat";
				}
			}

			if (verif != 0) {

				try {
					PdfClass.createPdf(coIn, clOut, panier);
				} catch (IOException | DocumentException e) {

					e.printStackTrace();
				}

				MailClass.sendMailToCl(coIn, clOut, panier);

				for (LigneCommande lc : listeLigneCommande) {
					Client clIn = lc.getProduit().getClient();
					try {
						PdfClass.createPdf(coIn, clOut, lc);
					} catch (IOException | DocumentException e) {

						e.printStackTrace();
					}
					MailClass.sendMailToClVente(coIn, clIn, lc);
				}

				Panier paIn = new Panier();
				List<LigneCommande> listeIn = new ArrayList<LigneCommande>();
				paIn.setPrixTotal(0);
				paIn.setListeLigneCommandes(listeIn);

				maSession.setAttribute("paSession", paIn);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande a �t� envoy�e"));

				return "accueilSite";

			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Erreur d'ajout de la ligne de commande"));

				return "loginClientAchat";
			}

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur d'ajout de la commande"));

			return "loginClientAchat";
		}

	}

}
