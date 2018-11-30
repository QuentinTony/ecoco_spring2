package fr.adaming.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vNbPrix")
public class ValidatorNombrePrix implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent composant, Object valeur) throws ValidatorException {
		
		double saisie1 = (Double) valeur;
		String saisie = Double.toString(saisie1);

		int longueur = saisie.length();
		String nb = "01234565789.";
		
		for(int i = 0;i<longueur;i++){
			String test = Character.toString(saisie.charAt(i));
			
				if(!nb.contains(test)){
					System.out.println(test);
					throw new ValidatorException(new FacesMessage("Le prix est un nombre !"));
			}
		}
		
	}

}
