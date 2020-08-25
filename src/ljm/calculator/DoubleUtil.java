package ljm.calculator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

//  BigDecimal的Double精确计算
class DoubleUtil implements Serializable {
    private static final long serialVersionUID = -3345205828566485102L;
    private static final Integer DEF_DIV_SCALE = 40;    //  除法精度

    //  精确加
    public static Double add(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
    }

    //  精确减
    public static double sub(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }

    //  精确乘
    public static Double mul(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).doubleValue();
    }

    //  精确除 精度为 DEF_DIV_SCALE
    public static Double divide(Double dividend, Double divisor) {
        return divide(dividend, divisor, DEF_DIV_SCALE);
    }

    //  精确除
    public static double divide(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            return -1;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

}