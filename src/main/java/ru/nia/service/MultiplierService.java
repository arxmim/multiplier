package ru.nia.service;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import java.math.BigDecimal;

public class MultiplierService {
    private static final int precision = 80;

    public Apfloat getCos(BigDecimal val) {
        return ApfloatMath.cos(ApfloatMath.toRadians(new Apfloat(val, precision))).precision(precision);
    }

    public Apfloat getSin(BigDecimal val) {
        return ApfloatMath.sin(ApfloatMath.toRadians(new Apfloat(val, precision))).precision(precision);
    }

    private Apfloat getK(BigDecimal step) {
        Apfloat apfloat = getCos(step);
        return apfloat.multiply(new Apfloat(2, precision));
    }

    public Apfloat doRecur(Apfloat second, Apfloat first, BigDecimal step) {
        Apfloat res = getK(step);

        res = res.multiply(second).precision(precision);
        res = res.subtract(first).precision(precision);
        return res;
    }
}
