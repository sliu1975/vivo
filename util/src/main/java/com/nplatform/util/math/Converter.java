package com.nplatform.util.math;

import java.text.DecimalFormat;

public class Converter {

    public static String Temp_FtoC(String f)
    {
        if (f == "")
        {
            return f;
        }
        double num = Double.parseDouble(f);
        double value = (num - 32.0) * 5.0 / 9.0;
        /*
        String text = Math.round(value*1000)/1000+"";
        if (text.length() > 10)
        {
            text = text.substring(0, 10);
        }
        */
        return new DecimalFormat("#,##0.000").format(value);
    }

    public static String Density_LBtoKG(String Lb)
    {
        if (Lb == "")
        {
            return Lb;
        }
        double num = Double.parseDouble(Lb);
        double value = num * 16.02;
        String text = Math.round(value*1000)/1000+"";
        if (text.length() > 10)
        {
            text = text.substring(0, 10);
        }
        return text;
    }

    public static String Density_KGtoLb(String Kg)
    {
        if (Kg == "")
        {
            return Kg;
        }
        double num = Double.parseDouble(Kg);
        String text = (num / 16.02)+"";
        if (text.length() > 10)
        {
            text = text.substring(0, 10);
        }
        return text;
    }

}
