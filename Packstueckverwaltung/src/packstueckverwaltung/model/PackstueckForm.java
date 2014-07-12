package packstueckverwaltung.model;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class PackstueckForm
{
	public PackstueckForm()
	{
		
	}
	public PackstueckForm(HttpServletRequest request) {
//		id = request.getParameter("id");
//		vorname = request.getParameter("vorname");
//		nachname = request.getParameter("nachname");
//		email = request.getParameter("email");
//		gewicht = request.getParameter("gewicht");
//		geburtsdatum = request.getParameter("geburtsdatum");
//		lieblingsfarbe = request.getParameter("lieblingsfarbe");
//		lebenslauf = request.getParameter("lebenslauf");
//		if (StringUtils.isNotBlank(request.getParameter("geschlecht"))) {
//			geschlecht = Geschlecht.valueOf(request.getParameter("geschlecht"));
//		}
//		if (StringUtils.isNotBlank(request.getParameter("muttersprache"))) {
//			muttersprache = Locale.forLanguageTag(request.getParameter("muttersprache"));
//		}
//		if (StringUtils.isNotBlank(request.getParameter("newsletter"))) {
//			newsletter = true;
//		}
		
	}

	public PackstueckForm(Packstueck Packstueck, HttpServletRequest request) {
//		if (person.getId() != null) {
//			id = person.getId().toString();
//		}
//		vorname = person.getVorname();
//		nachname = person.getNachname();
//		email = person.getEmail();
//		if (person.getGewicht() != null) {
//			gewicht = Helper.getEineNachkommastelle(request.getLocale()).format(person.getGewicht());
//		}
//		if (person.getGeburtsdatum() != null) {
//			geburtsdatum = Helper.getDateFormat(request.getLocale()).format(person.getGeburtsdatum());
//		}
//		geschlecht = person.getGeschlecht();
//		muttersprache = person.getMuttersprache();
//		lieblingsfarbe = person.getLieblingsfarbe();
//		newsletter = person.isNewsletter();
//		lebenslauf = person.getLebenslauf();
	}
}
