package ru.nia.service;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class MultiplierService {

    public Apfloat getCos(int val) {
        return ApfloatMath.cos(new Apfloat(val, 50));
    }

    public Apfloat doRecurFunction(Apfloat second, Apfloat first, int step) {
        Apfloat apfloat = ApfloatMath.cos(new Apfloat(step, 50));
        Apfloat res = apfloat.multiply(new Apfloat(2, 50));
        res = res.multiply(second);
        res = res.subtract(first);
        return res;
    }
}
