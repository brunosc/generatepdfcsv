package br.com.tests.report;

import java.io.IOException;
import java.lang.reflect.Field;

public abstract class ReportGeneratorAbstract implements ReportGenerator {

    public void generator(ReportInfo info) throws IOException {

        // fazer validacoes validar se caminho existe
        // file.getParentFile() (para ver o diretorio do arquivo

        criaArquivo(info);
    }

    protected String getValueFromObject(Object obj, String attributeName) {

        for (Field field: obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.getName().equalsIgnoreCase(attributeName))
                    return field.get(obj).toString();
            } catch (IllegalAccessException e) {
                return "";
            }
        }

        return "";
    }

    public abstract void criaArquivo(ReportInfo info) throws IOException;

    public abstract String getPathFile();
}
