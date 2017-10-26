package br.com.tests.report;

import java.util.List;

public class ReportInfo<T> {

    private ReportGenerator generator = new ReportGeneratorPDF();

    private String title;
    private float[] columnWidths;
    private String[] columnHeaders;
    private String[] attributes;
    private List<T> list;
    private String[] footers;
    // TIPO PDF OU EXCEL

    private ReportInfo(Builder builder) {
        title = builder.title;
        columnWidths = builder.columnWidths;
        columnHeaders = builder.columnHeaders;
        attributes = builder.attributes;
        list = builder.list;
        footers = builder.footers;
    }

    public static class Builder<T> {

        private String title;
        private float[] columnWidths;
        private String[] columnHeaders;
        private String[] attributes;
        private List<T> list;
        private String[] footers;

        public Builder<T> title(String title) {
            this.title = title;
            return this;
        }

        public Builder<T> columnWidths(float[] columnWidths) {
            this.columnWidths = columnWidths;
            return this;
        }

        public Builder<T> columnHeaders(String[] columnHeaders) {
            this.columnHeaders = columnHeaders;
            return this;
        }

        public Builder<T> attributes(String[] attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder<T> list(List<T> list) {
            this.list = list;
            return this;
        }

        public Builder<T> footers(String[] footers) {
            this.footers = footers;
            return this;
        }

        public ReportInfo<T> build() {
            return new ReportInfo<T>(this);
        }

    }

    public String getTitle() {
        return title;
    }

    public float[] getColumnWidths() {
        return columnWidths;
    }

    public String[] getColumnHeaders() {
        return columnHeaders;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public List<T> getList() {
        return list;
    }

    public String[] getFooters() {
        return footers;
    }
}
