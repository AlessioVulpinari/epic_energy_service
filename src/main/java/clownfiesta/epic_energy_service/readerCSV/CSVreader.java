package clownfiesta.epic_energy_service.readerCSV;

import org.springframework.stereotype.Service;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Service
public class CSVreader {
    public List<String[]> readCsv(Path path) throws Exception {
        try (Reader reader = Files.newBufferedReader(path);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1)
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(';').withIgnoreLeadingWhiteSpace(true).withIgnoreQuotations(true)
                             .build())
                     .build()) {
            return csvReader.readAll();
        }
    }
}
