package com.money.framework.util;

public class DistanceUtils {

    private static final double EARTH_RADIUS = 6378137.0;

    public static double getDistancePrecise(double lat1, double lng1, double lat2, double lng2) {

        double radLat1 = lat1 * Math.PI / 180;
        double radLat2 = lat2 * Math.PI / 180;
        double a = radLat1 - radLat2;
        double b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        return s;
    }

}
