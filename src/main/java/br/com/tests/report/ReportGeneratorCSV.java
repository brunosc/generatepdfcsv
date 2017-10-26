package br.com.tests.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class ReportGeneratorCSV extends ReportGeneratorAbstract {

    private static final String NEW_LINE_SEPARATOR = "\n";

    @Override
    public void criaArquivo(ReportInfo info) throws IOException {

        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {

            fileWriter = new FileWriter(getPathFile());

            csvPrinter = new CSVPrinter(fileWriter, csvFileFormat);

            // Header
            csvPrinter.printRecord(info.getColumnHeaders());

            for (Object obj: info.getList()) {

                List record = new ArrayList();

                for (String attribute: info.getAttributes())
                    record.add(getValueFromObject(obj, attribute));

                // printa uma linha
                csvPrinter.printRecord(record);

            }

        } catch (Exception e) {
            throw new IOException(e.getMessage());
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
                csvPrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }

        }

    }

    @Override
    public String getPathFile() {
        return "/home/bruno/dev/docs/doc.csv";
    }
}
