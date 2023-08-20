package org.example;

/**
 Esempio di binding dinamico.
 */
public class BindingDinamicoDemo {

    public static void main(String[] args) {
        Vendita semplice = new Vendita("tappetino auto", 10.00);  //un prodotto a E10.00.
        VenditaScontata scontato = new VenditaScontata("tappetino auto", 11.00, 10);
        //un prodotto a E11.00 con il 10% di sconto.
        System.out.println(methodPrezzoMinore(semplice,scontato));


        Vendita prezzoNormale = new Vendita("porta bicchiere", 9.90); //un prodotto a E9.90.
        VenditaScontata prezzoSpeciale = new VenditaScontata("porta bicchiere", 11.00, 10);
        //un prodotto a E11.00 con il 10% di sconto.
        System.out.println(methodPrezzoUguale(prezzoNormale,prezzoSpeciale));

        double i = 12.33;
        System.out.println(i);

    }

    public static String methodPrezzoMinore (Vendita semplice, VenditaScontata scontato){
        System.out.println(semplice.toString());
        System.out.println(scontato.toString());

        if (scontato.minoreDi(semplice))
            return "Il prodotto scontato costa meno.";
        else
            return"Il prodotto scontato non costa meno.";
    }

    public static String methodPrezzoUguale (Vendita prezzoNormale, VenditaScontata prezzoSpeciale){
        System.out.println(prezzoNormale.toString());
        System.out.println(prezzoSpeciale.toString());

        if (prezzoSpeciale.uguaglianzaVendite(prezzoNormale))
            return "Il costo totale e' lo stesso.";
        else
            return "Il costo totale e' diverso.";
    }

}


