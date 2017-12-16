import org.json.JSONException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, JSONException {
        WikidataAPI wikidataAPI = new WikidataAPI();
        //Test des méthodes

        System.out.println(wikidataAPI.searchEntity("Paris"));
        System.out.println(wikidataAPI.getEntity("Q90"));

        //Test avec un fichier de configuration
        String[] temp;
        String delimiter = ",";
        List<String> lObj = new ArrayList<String>();

        try{
            InputStream flux = new FileInputStream("MatrixSynthesizerWikipedia/File/FichierConfig.txt");
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                System.out.println(ligne);
                temp = ligne.split(delimiter);
                for(int i =0; i < temp.length ; i++){
                    int p1 = temp[i].indexOf('(');
                    int p2 = temp[i].indexOf(')');
                    lObj.add(temp[i].substring(p1+1, p2));
                }
            }
            buff.close();
        }	catch (Exception e) {
            System.out.println(e.toString());
        }

        //Récupération des données à partir des clés obtenues dans le fichier de configuration
        String dataCsv ="";
        for (String str : lObj){
            //System.out.println(wikidataAPI.getEntity(str));
            dataCsv += str +"\n";
        }

       //System.out.println("GetPorperty : "+ wikidataAPI.getProperty("Q90"));

        WikipediaAPI wikipediaAPI = new WikipediaAPI();

        System.out.println("WIKIPEDIA !! ");
        System.out.println(wikipediaAPI.searchEntity("paris"));
        System.out.println(wikipediaAPI.getEntity("Paris"));

        System.out.println("{{About|the capital of France}}\n{{pp-pc1}}\n{{Good article}}\n{{Use dmy dates|date=February 2015}}\n{{EngvarB|date=November 2016}}\n{{Infobox French commune\n|name = Paris\n|commune status = [[Communes of France|Commune]] and [[Departments of France|department]]\n|image = <center>{{Photomontage |border=0  |size=275 |spacing=2 |color=none |photo1a=Seine and Eiffel Tower from Tour Saint Jacques 2013-08.JPG |photo2a=Arc Triomphe (square).jpg |photo2b=Notre Dame dalla Senna.jpg |photo3a=Louvre Museum Wikimedia Commons.jpg}}</center>\n|caption = Clockwise from top: skyline of Paris on the [[Seine|River Seine]] with the [[Eiffel Tower]], [[Notre-Dame de Paris]], the [[Louvre]] and its [[Louvre Pyramid|large pyramid]], and the [[Arc de Triomphe]]\n|image flag = Flag of Paris.svg\n|image flag size = x95px\n|image coat of arms = Grandes_Armes_de_Paris.svg\n|image coat of arms size = x95px\n|city motto = {{lang|fr|''[[Fluctuat nec mergitur]]''}}<br /> {{smaller|\\\"Tossed but never sunk\\\"}}\n|coordinates = {{coord|48.8567|2.3508|type:city(2200000)_region:FR|format=dms|display=inline,title}}\n|mayor = [[Anne Hidalgo]]\n|party = [[Socialist Party (France)|PS]]\n|term = {{nowrap|2014\\u20132020}}\n|subdivisions entry = [[Administrative division|Subdivisions]]\n|subdivisions = [[Arrondissements of Paris|20 arrondissements]]\n|total type = City\n|area km2 = 105.4\n|area footnotes = <ref name=\\\"area\\\">[http://www.statistiques-locales.insee.fr/Fiches%5CRS%5CDEP%5C75%5CCOM%5CRS_COM75056.pdf INSEE local statistics], including [[Bois de Boulogne]] and [[Bois de Vincennes]].</ref>\n|INSEE=75056\n|population date = 2013\n|population footnotes = <ref name=\\\"insee.fr\\\">{{cite web|url=http://www.insee.fr/fr/themes/comparateur.asp?codgeo=dep-75 |title=R\\u00e9sum\\u00e9 statistique \\u2013 D\\u00e9partement de Paris (75)|author=[[INSEE]]|accessdate=2016-08-11|language=fr}}</ref><ref name=pop_UU/><ref>{{cite web|url=http://www.insee.fr/fr/themes/tableau.asp?reg_id=0&ref_id=nattef01203|title=Insee \\u2013 Territoire \\u2013 Les 60 premi\\u00e8res aires urbaines en 2013|author=|date=|work=insee.fr}}</ref>\n|population = 2,229,621\n|population ranking = [[List of the 75 largest cities in France (2012 census)|1st in France]]\n|urban area km2 = 2845\n|urban area date = <!--Note: Unneeded as same as adjacent to \\\"Population\\\" heading (2013).-->\n|urban pop = 10601122\n|metro area km2 = 17174.4\n|metro area date = <!--Note: Unneeded as same as adjacent to \\\"Population\\\" heading (2013).-->\n|metro area pop = 12,405,426\n|metro area pop date = <!--Note: Unneeded as same as adjacent to \\\"Population\\\" heading (2013).-->\n|population_demonym= Parisian<br /> {{lang|fr|''Parisien(ne)''}} ([[French language|fr]])\n|postal code= 75001\\u201375020, 75116\n|website = [http://www.paris.fr/ www.paris.fr]\n}}\n\n'''Paris''' ({{IPA-fr|pa\\u0281i|3=Paris1.ogg}})<!--Do not add English pronunciation per MOS:LEAD--> is the [[Capital city|capital]] and most populous [[city]] of [[France]], with an administrative-limits area of {{convert|105|km2|abbr=off}} and a 2015 population of 2,229,621.<ref name=\\\"insee.fr\\\"/> The city is a [[Communes of France|commune]] and [[Departments of France|department]], and the capital-heart of the {{convert|12,012|km2|abbr=off|adj=on}} [[\\u00cele-de-France]] [[Region in France|region]] (colloquially known as the 'Paris Region'), whose 2016 population of 12,142,802 represents roughly 18 percent of the population of France.<ref name=pop>{{cite web|url=https://www.insee.fr/fr/statistiques/1893198|title=Estimation de population au 1er janvier, par r\\u00e9gion, sexe et grande classe d'\\u00e2ge \\u2013 Ann\\u00e9e 2016|author=[[INSEE]]|accessdate=4 April 2017|language=fr}}</ref> Since the 17th century, Paris has been one of Europe's major centres of finance, commerce, fashion, science, and the arts. The Paris Region had a [[GDP]] of \\u20ac649.6 billion (US $763.4 billion) in 2014, accounting for 30.4 percent of the GDP of France.<ref name=\\\"auto\\\">INSEE.fr statistics, retrieved July 2017</ref>  According to official estimates, in 2013-14 the Paris Region had [[List of cities by GDP|the third-highest GDP in the world and the largest regional GDP in the EU]].\n\nThe City of Paris's administrative limits form a horizontal oval centred on its historical-heart [[\\u00cele de la Cit\\u00e9]] island; this island is near the peak of an arc of [[Seine]] river that divides the city into southern [[Rive Gauche]] (Left Bank) and northern [[Rive Droite]] regions. Paris is but the core of a built-up area that extends well beyond its limits: commonly referred to as the ''agglom\\u00e9ration Parisienne'', and statistically as a ''[[unit\\u00e9 urbaine]]'' (a measure of [[urban area]]), the Paris agglomeration's 2013 population of 10,601,122 makes it the [[List of urban areas in the European Union|largest urban area in the European Union]].<ref name=pop_UU>{{cite web|url=http://www.insee.fr/fr/themes/tableau_local.asp?ref_id=&millesime=2013&typgeo=UU2010&typesearch=territoire&codgeo=Paris+%2800851%29&territoire |title=S\\u00e9ries historiques des r\\u00e9sultats du recensement \\u2013 Unit\\u00e9 urbaine de Paris (00851)|author=[[INSEE]]|accessdate=2016-09-25|language=fr}}</ref> City-influenced commuter activity reaches well beyond even this in a statistical [[Paris Metropolitan Area|''aire urbaine'' de Paris]] (a measure of [[metropolitan area]]), that had a 2013 population of 12,405,426,<ref name=pop_AU>{{cite web|url=http://www.insee.fr/fr/themes/tableau_local.asp?ref_id=&millesime=2013&typgeo=AU2010&typesearch=territoire&codgeo=Paris+%28001%29&territoire |title=S\\u00e9ries historiques des r\\u00e9sultats du recensement \\u2013 Aire urbaine de Paris (001)|author=[[INSEE]]|accessdate=2016-09-25|language=fr}}</ref> a number one-fifth the population of France,<ref>{{cite web|url=http://www.insee.fr/fr/themes/tableau_local.asp?ref_id=&millesime=2013&typgeo=]].FE&typesearch=territoire&codgeo=&territoire= |title=S\\u00e9ries historiques des r\\u00e9sultats du recensement \\u2013 France|author=[[INSEE]]|accessdate=2016-09-25|language=fr}}</ref> and one that makes it, after [[London]], the [[List of metropolitan areas in Europe|second largest]] metropolitan area in the [[European Union]].  The 2016 Metropole of [[Grand Paris]] initiative, encompassing the City of Paris and its surrounding ''[[\\u00cele-de-France#Petite Couronne|petite couronne]]'' department communes, or area covering 814 square kilometers and representing a population of 7 million,<ref>{{cite web|url=http://www.leparisien.fr/paris-75/la-metropole-du-grand-paris-un-mastodonte-de-7-millions-d-habitants-17-12-2015-5382031.php|title=The Metropole of Grand Paris a mastodon of 7 million persons|last=|first=|date=17 December 2015|website=|publisher=Le Parisien|language=fr|accessdate=3 December 2015}}</ref><ref name=BANATIC>[https://www.banatic.interieur.gouv.fr/V5/fichiers-en-telechargement/fichiers-telech.php BANATIC], P\\u00e9rim\\u00e8tre des EPCI \\u00e0 fiscalit\\u00e9 propre. Accessed 2017-06-28.</ref> aims to improve city-suburb cooperation through a unique governing body.<ref name=decret>{{cite web|url=http://www.legifrance.gouv.fr/affichTexte.do?cidTexte=JORFTEXT000031255615|title=D\\u00e9cret n\\u00b0 2015-1212 du 30 septembre 2015 constatant le p\\u00e9rim\\u00e8tre fixant le si\\u00e8ge et d\\u00e9signant le comptable public de la m\\u00e9tropole du Grand Paris &#124; Legifrance|accessdate=2017-06-28}}</ref>\n\nThe city is a major rail, highway, and air-transport hub served by two international airports: [[Paris-Charles de Gaulle Airport|Paris-Charles de Gaulle]] (the second busiest airport in Europe after London Heathrow Airport with 63.8 million passengers in 2014) and [[Paris-Orly Airport|Paris-Orly]]. Opened in 1900, the city's subway system, the [[Paris M\\u00e9tro]], serves 5.23 million passengers daily,<ref>{{cite web|title = M\\u00e9tro2030|url = http://www.ratp.fr/en/ratp/r_108501/metro2030-our-new-paris-metro/|website = RATP (Paris metro operator)|accessdate = 25 September 2016|deadurl = yes|archiveurl = https://web.archive.org/web/20161221051116/http://www.ratp.fr/en/ratp/r_108501/metro2030-our-new-paris-metro/|archivedate = 21 December 2016|df = dmy-all}}</ref> and is the second busiest metro system in Europe after [[Moscow Metro]]. [[Paris Gare du Nord|Paris's Gare du Nord]] is one of the ten busiest railway stations in the world, with 262 million passengers in 2015.<ref>{{Cite news|url=https://japantoday.com/category/features/travel/the-51-busiest-train-stations-in-the-world-all-but-6-located-in-japan|title=The 51 busiest train stations in the world\\u2013 All but 6 located in Japan|work=Japan Today|date=2013-02-06|access-date=2017-04-22}}</ref>\n\nParis is especially known for its museums and architectural landmarks: the [[Louvre]] was the [[List of most visited art museums|most visited art museum in the world]] in 2016, with 7.4 million visitors.<ref>''The Art Newspaper'' Visitor Figures 2016, 29 March 2016</ref> The [[Mus\\u00e9e d'Orsay]] and [[Mus\\u00e9e de l'Orangerie]] are noted for their collections of French [[Impressionist]] art, and the Pompidou-center [[Mus\\u00e9e National d'Art Moderne]] has the largest collection of modern and contemporary art in Europe. The historical district along Seine River in the city center is classified as a [[UNESCO Heritage Site]]. Popular landmarks in the center of the city include [[Notre Dame de Paris|the Cathedral of Notre Dame de Paris]] and The Gothic royal chapel of [[Sainte-Chapelle]], both on the [[\\u00cele de la Cit\\u00e9]];  the [[Eiffel Tower]], constructed for the [[Exposition Universelle (1889)|Paris Universal Exposition of 1889]]; the [[Grand Palais]] and [[Petit Palais]], built for the [[Exposition Universelle (1900)|Paris Universal Exposition of 1900]]; the [[Arc de Triomphe]] on the [[Champs Elysees]], and the [[Sacr\\u00e9-C\\u0153ur, Paris|Basilica of Sacr\\u00e9-Coeur]] on the hill of [[Montmartre]]. Paris received 22.2 million visitors in 2015, making it one of the world's top tourist destinations, but the number of greater Paris visitors dropped by 11.5 percent following the terrorist attacks the following year.<ref>''Tourism in Paris Key Figures (2017)'', Paris Convention and Visitors Bureau</ref>\n\nThe [[association football]] club [[Paris Saint-Germain F.C.|Paris Saint-Germain]] and the [[rugby union]] club [[Stade Fran\\u00e7ais]] are based in Paris. The 80,000-seat [[Stade de France]], built for the [[1998 FIFA World Cup]], is located just north of Paris in the neighbouring commune of [[Saint-Denis, Seine-Saint-Denis|Saint-Denis]]. Paris hosts the annual [[French Open]] [[Grand Slam (tennis)|Grand Slam]] [[tennis]] tournament on the red clay of [[Stade Roland Garros|Roland Garros]]. Paris hosted the Olympic Games in [[1900 Summer Olympics|1900]], [[1924 Summer Olympics|1924]] and will host the [[2024 Summer Olympics]]. The [[1938 FIFA World Cup|1938]] and [[1998 FIFA World Cup]]s, the [[2007 Rugby World Cup]], and the [[1960 European Nations' Cup|1960]], [[UEFA Euro 1984|1984]], and [[UEFA Euro 2016|2016]] [[UEFA European Championship]]s were also held in the city, and every July, the [[Tour de France]] [[road bicycle racing|bicycle race]] finishes in the city.\"");

        Map<String,String> map =  wikipediaAPI.search(wikipediaAPI.getEntity("Paris"));
        String header = "";
        String data = "";
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            Object cle = it.next();
            cle = cle.toString().replace("\"","\'");
            header += "\"" + cle + "\"" +",";
            Object valeur = map.get(cle);
            valeur = valeur.toString().replace("\"","\'");
            data +=  "\"" +valeur + "\""+",";

        }

        header += "\n";
        data += "\n";

        GestionnaireCSV gestionnaire = new GestionnaireCSV();
        try{
            gestionnaire.addHeader("text", header, data);
            System.out.println("##################################################");
            System.out.println("Génération du csv '" + "text .csv' OK");
        } catch (Exception e){
            System.out.println("\nErreur lors de la génération du CSV");
        }


    }
}
