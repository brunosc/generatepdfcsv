package br.com.tests.dao;

import br.com.tests.domain.NotaFiscal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotaFiscalDAO {

    private final static List<NotaFiscal> list;

    static {
        list = new ArrayList<NotaFiscal>();

        for (int i=1; i<=200; i++)
            list.add(new NotaFiscal(i, "Nota "+i, "Complemento "+i, 100.0 * i));
    }

    public static List<NotaFiscal> findAll() {
        return Collections.unmodifiableList(list);
    }
}
