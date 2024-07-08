package clownfiesta.epic_energy_service.excepitions;

public class CSVReadingException extends RuntimeException {
    public CSVReadingException( Exception e) {
        super("ERROR READING CSV FILE", e);
    }
}