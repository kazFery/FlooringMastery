package com.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserIO {
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    BigDecimal readBigDecimal(String prompt);

    BigDecimal readBigDecimal(String prompt, BigDecimal min);

    LocalDate readDate(String prompt);

    LocalDate readDate(String prompt, LocalDate min);

    String formatCurrency(BigDecimal amount);

    BigDecimal readBigDecimal(String prompt, int scale);

    BigDecimal readBigDecimal(String s, int i, BigDecimal zero);

    String readState(String prompt);

    String readProduct(String prompt);
}
