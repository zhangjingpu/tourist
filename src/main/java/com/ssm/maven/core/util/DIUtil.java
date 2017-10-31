package com.ssm.maven.core.util;

public class DIUtil {

    public static Double returnDI(Double Celsius, Double relative_Humidity) {
        Double DI = 0.00;
        Double Fahrenheit = Celsius * 9.00 / 5.00 + 32.00;
        DI = Fahrenheit - (0.55 - 0.55 * relative_Humidity) * (Fahrenheit - 58);
        return DI;
    }

    public static double retrunCD(double DI, double HLC, double VLC) {
        double CD = 0.00;
        if (DI <= 1) {
            DI = DI / 62.5;
        }
        if (DI > 1) {
            DI = 2 - DI;
        }
        if (HLC <= 1) {
            HLC = 1 - HLC;
        }
        if (HLC > 1) {
            HLC = 0;
        }
        if (VLC <= 1) {
            VLC = 1 - VLC;
        }
        if (VLC > 1) {
            VLC = 0;
        }
        CD = (0.6 * DI + HLC * 0.2 + VLC * 0.2);
        return CD;
    }
}
