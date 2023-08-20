package org.example;

/**
 Classe per la vendita di un componente con lo sconto espresso
 come una percentuale del prezzo, ma senza altri aggiustamenti.
 Il prezzo e lo sconto assumono valori non negativi;
 il nome � una stringa non vuota.
 */
public class VenditaScontata extends Vendita {

    private double sconto; 	//Una percentuale del prezzo.
    //Non pu� essere negativa.

    public VenditaScontata() {
        super();
        sconto = 0;
    }

    /**
     Precondizione: ilNome � una stringa non vuota; ilPrezzo � non negativo;
     loSconto � espresso come una percentuale del prezzo ed � non negativo.
     */
    public VenditaScontata(String ilNome, double ilPrezzo, double loSconto) {
        super(ilNome, ilPrezzo);
        setSconto(loSconto);
    }


    public double totale() {
        double frazione = sconto / 100;
        return (1 - frazione) * getPrezzo();
    }

    public double getSconto() {
        return sconto;
    }

    /**
     Precondizione: nuovoSconto � non negativo.
     */
    public boolean setSconto(double nuovoSconto) {
        if(nuovoSconto >= 0) {
            sconto = nuovoSconto;
            return true;
        }
        else {
            System.out.println("Errore: sconto negativo.");
            return false;
        }
    }



    public String toString() {
        return ("Componente = " + getNome() +
                ", Prezzo = E" + getPrezzo() +
                " Sconto = " + sconto + "%\n" +
                "Costo totale = E" + totale());
    }

    public boolean equals(Object altroOggetto) {
        if (altroOggetto == null)
            return false;
        else if (!(altroOggetto instanceof VenditaScontata))
            return false;
        else {
            VenditaScontata altraVenditaScontata = (VenditaScontata)altroOggetto;
            return (super.equals(altraVenditaScontata) && sconto == altraVenditaScontata.sconto);
        }
    }
}
