package ru.nia.service;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class MultiplierService {
    private static final int precision = 100;

    public Apfloat getCos(int val) {
        return ApfloatMath.cos(ApfloatMath.toRadians(new Apfloat(val, precision)));
    }

    public Apfloat doRecurFunction(Apfloat second, Apfloat first, int step) {
        Apfloat apfloat = getCos(step);
        Apfloat res = apfloat.multiply(new Apfloat(2, precision));
        res = res.multiply(second);
        res = res.subtract(first);
        return res;
    }
}
