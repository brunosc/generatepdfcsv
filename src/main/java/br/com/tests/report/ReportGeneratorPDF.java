package br.com.tests.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportGeneratorPDF extends ReportGeneratorAbstract {

    private Document document;
    private PdfPTable table;

    public class Rotate extends PdfPageEventHelper {
        protected PdfNumber orientation = PdfPage.PORTRAIT;
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }

    public void criaArquivo(ReportInfo info) throws IOException {

        try {

            openFile();

            createTable(info.getColumnWidths());

            createTitle(info.getTitle(), info.getColumnWidths().length);

            createHeaders(info.getColumnHeaders());

            createBody(info.getList(), info.getAttributes());

            createFooter(info.getFooters());

            document.add(table);
            document.close();

        } catch (FileNotFoundException | DocumentException e) {
            throw new IOException(e.getMessage());
        }

    }

    private void openFile() throws FileNotFoundException, DocumentException {
        document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getPathFile()));

        Rotate event = new Rotate();

        // Se quiser landscape
        //event.setOrientation(PdfPage.LANDSCAPE);

        writer.setPageEvent(event);

        document.open();
    }

    private void createTable(float[] columnWidths) {
        table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
    }

    private void createTitle(String title, int colspan) {
        Font fontTitle = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
        PdfPCell cell = new PdfPCell(new Phrase(title, fontTitle));
        cell.setBackgroundColor(GrayColor.GRAYBLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(colspan);
        table.addCell(cell);
    }

    private void setColorHeaderColumns() {
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
    }

    private void createHeaders(String[] headers) {

        setColorHeaderColumns();

        for (String header: headers) {
            table.addCell(header);
        }
    }

    private void setColorBody() {
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    }

    private void createBody(List<?> list, String[] attributes) {

        table.setHeaderRows(2);
        table.setFooterRows(0);

        setColorBody();

        for (Object item: list) {

            for (String attribute: attributes) {
                table.addCell(getValueFromObject(item, attribute));
            }

        }

    }

    private void createFooter(String[] footers) {

        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

        for (String footer: footers)
            table.addCell(footer);
    }

    public String getPathFile() {
        return "/home/bruno/dev/docs/doc.pdf";
    }

}
