package com.stolk.alecsandro.obra.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    private static DateTimeFormatter en = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter br = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate dataBr(String data) {
        return LocalDate.parse(data, br);
    }

    public static LocalDate dataEn(String data) {
        return LocalDate.parse(data, en);
    }

    public static String dataTxtEn(LocalDate data) {
        return en.format(data);
    }

    public static String dataTxtBr(LocalDate data) {
        return br.format(data);
    }
}
