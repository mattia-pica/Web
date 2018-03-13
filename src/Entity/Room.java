package Entity;

public class Room {

    public Room(String nome, String tipopr, String datapr, String inizio, String fine, String fromp) {
        this.nome = nome;
        this.tipopr = tipopr;
        this.datapr = datapr;
        this.inizio = inizio;
        this.fine = fine;
        this.fromp = fromp;
    }

    private String nome;
    private String tipopr;
    private String datapr;
    private String inizio;
    private String fine;
    private String fromp;
}
