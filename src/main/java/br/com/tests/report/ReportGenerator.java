package br.com.tests.report;

import java.io.IOException;

public interface ReportGenerator {

    void generator(ReportInfo info) throws IOException;

}
