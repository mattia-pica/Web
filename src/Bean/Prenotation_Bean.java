package Bean;

import java.time.LocalTime;

public class Prenotation_Bean {

    private LocalTime inizio;
    private LocalTime fine;
    private String date;

    public Prenotation_Bean(LocalTime inizio, LocalTime fine, String date) {
        this.inizio = inizio;
        this.fine = fine;
        this.date = date;
    }


    public LocalTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalTime inizio) {
        this.inizio = inizio;
    }

    public LocalTime getFine() {
        return fine;
    }

    public void setFine(LocalTime fine) {
        this.fine = fine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}