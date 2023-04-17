
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author CDM-51
 */
public class TestClaseLocalTime {

    LocalDate localDate;

    public static void main(String[] args) {
        LocalDate l = LocalDate.of(2022, Month.JUNE, 22);
        System.out.println(l.getDayOfMonth());
        LocalDate ll = LocalDate.of(2022, Month.JUNE, 23);
        long noDaysBetween = ChronoUnit.DAYS.between(l, ll);
        System.out.println(noDaysBetween);

        /**
         * Calcular dias entre dos fechas
         */ 
    }
}
