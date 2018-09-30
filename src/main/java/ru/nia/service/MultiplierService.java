package ru.nia.service;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class MultiplierService {
    private static final int precision = 80;

    public Apfloat getCos(int val) {
        return ApfloatMath.cos(ApfloatMath.toRadians(new Apfloat(val, precision)));
    }

    public Apfloat getSin(int val) {
        return ApfloatMath.sin(ApfloatMath.toRadians(new Apfloat(val, precision)));
    }

    private Apfloat getK(int step) {
        Apfloat apfloat = getCos(step);
        return apfloat.multiply(new Apfloat(2, precision));
    }

    public Apfloat doRecur(Apfloat second, Apfloat first, int step) {
        Apfloat res = getK(step);
        res = res.multiply(second);
        res = res.subtract(first);
        return res;
    }
}
