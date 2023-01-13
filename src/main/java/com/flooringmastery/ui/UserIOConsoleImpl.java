package com.flooringmastery.ui;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_UP;

@Component
public class UserIOConsoleImpl implements  UserIO {
    private Scanner input = new Scanner((System.in));


    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt, double min) {
        double num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Double.parseDouble(stringValue);
                if (num >= min )
                    break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public double readDouble(String prompt) {
        double num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Double.parseDouble(stringValue);
                break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);
                if (num > min && num < max)
                    break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt) {
        int num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);
                break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Float.parseFloat(stringValue);
                if (num > min && num < max)
                    break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        float num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Float.parseFloat(stringValue);
                break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Long.parseLong(stringValue);
                if (num > min && num < max)
                    break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        long num =0 ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = Long.parseLong(stringValue);
                break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public String readString(String prompt) {
        this.print(prompt);
        return input.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal num;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = new BigDecimal(stringValue);
                break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    @Override
    public String readState(String prompt) {
        boolean valid = false;
        String result = "";
        do {
            String value = null;
            value = readString(prompt);
            if (value.equals("TX") || value.equals("WA") || value.equals("KY") || value.equals("CA")) {
                result = value;
                valid = true;
            } else {
                System.out.printf("You Must Select One Of The Options Provided!  ");
            }

        } while (!valid);
        return result;
    }

    @Override
    public String readProduct(String prompt) {
        boolean valid = false;
        String result = "";
        do {
            String value = null;

            value = readString(prompt);
            if (value.equals("Carpet") || value.equals("Laminate") || value.equals("Tile") || value.equals("Wood")) {
                result = value;
                valid = true;
            } else {
                System.out.printf("You Must Select One Of The Options Provided!  ");
            }

        } while (!valid);
        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min) {
        BigDecimal num ;
        while(true){
            try {
                String stringValue = this.readString(prompt);
                num = new BigDecimal(stringValue);
                if (num.compareTo(min) >=0 )
                    break;
            }
            catch (NumberFormatException e){
                this.print("Input Error, please try again.");
            }
        }
        return num;
    }

    public LocalDate readDate(String prompt){
        LocalDate date;
        this.print(prompt);
        while (true) {
            String stringDate = input.nextLine();
            try {
                date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                break;
            } catch (DateTimeParseException e) {
                this.print("Date input format is MM/dd/yyyy, Please try again");
            }
        }
        return date;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min) {
        boolean valid = false;
        LocalDate result = null;
        do {
            result = readDate(prompt);
            if (result.isAfter(min)) {
                valid = true;
            } else {
                System.out.printf("Your date must be after. (%s)\n", min);
            }
        } while (!valid);

        return result;
    }

    @Override
    public String formatCurrency(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale) {
        boolean valid = false;
        BigDecimal result = null;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = new BigDecimal(value).setScale(scale, ROUND_UP);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value '%s' is not a number.\n", value);
            }
        } while (!valid);
        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale, BigDecimal min) {
        boolean valid = false;
        BigDecimal result = null;
        do {
            result = readBigDecimal(prompt, scale);
            if (result.compareTo(min) >= 0) {
                valid = true;
            } else {
                String minString = String.valueOf(min);
                System.out.printf("The value must be greater than %s.\n", minString);
            }
        } while (!valid);

        return result;
    }
}
