package br.com.tests;

import br.com.tests.dao.NotaFiscalDAO;
import br.com.tests.domain.NotaFiscal;
import br.com.tests.report.ReportGenerator;
import br.com.tests.report.ReportGeneratorCSV;
import br.com.tests.report.ReportGeneratorPDF;
import br.com.tests.report.ReportInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String DEST = "/home/bruno/dev/docs/aui.pdf";

    public static void main( String[] args ) throws Exception {

        List<NotaFiscal> list = NotaFiscalDAO.findAll();

        float[] columnWidths = {1, 5, 5, 5};
        String[] columnHeaders = {"ID", "Descricao", "Complemento", "Valor"};
        String[] attributes = {"id", "descricao", "complemento", "valor"};
        String[] footers = {"-", "-", "footer 1", "footer 2"};

        ReportInfo<NotaFiscal> info = new ReportInfo.Builder<NotaFiscal>()
                                                    .title("Titulo")
                                                    .columnWidths(columnWidths)
                                                    .columnHeaders(columnHeaders)
                                                    .attributes(attributes)
                                                    .list(list)
                                                    .footers(footers)
                                                    .build();

//        ReportGenerator gen = new ReportGeneratorPDF();
        ReportGenerator gen = new ReportGeneratorCSV();

        gen.generator(info);


    }

    private static void print(List<?> list) {
        Object obj = list.get(2);

        for (Field field: obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.print("Atributo: " + field.getName());
            try {
                System.out.println(", Valor: " + field.get(obj) );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("\n");
        }

    }
}
