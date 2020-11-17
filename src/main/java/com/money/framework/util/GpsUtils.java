package com.money.framework.util; /**
 * Created by sunshangqiang on 2/4/16
 * 火星坐标系统 GCJ-02 (加偏移量的）
 * 大地坐标系统 WGS-84 （原始的）
 * 百度坐标系统 BD-09
 * 目前使用火星坐标系的地图商:
 * 腾讯搜搜地图
 * 搜狐搜狗地图
 * 阿里云地图
 * 高德MapABC地图
 * 灵图51ditu地图
 */

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GpsUtils {

	public final static String OUT_LAT = "outlat";
	public final static String OUT_LNG = "outlng";

	private static double pi = 3.14159265358979323;
	private static double a = 6378245.0;
	private static double ee = 0.00669342162296594323;
	private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	public static Map<String, Double> Wgs2Gcj(double lng, double lat) {
		double dLat = TransformLat(lng - 105.0, lat - 35.0);
		double dlng = Transformlng(lng - 105.0, lat - 35.0);
		double radLat = lat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dlng = (dlng * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		double outlat = lat + dLat;
		double outlng = lng + dlng;
		BigDecimal b = BigDecimal.valueOf(outlng);
		outlng = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		b = BigDecimal.valueOf(outlat);
		outlat = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		Map<String, Double> ret = new HashMap<>();
		ret.put(OUT_LNG, outlng);
		ret.put(OUT_LAT, outlat);
		return ret;
	}

	public static Map<String, Double> Gcj2Wgs(double lng, double lat) {
		Map<String, Double> dataMap = Wgs2Gcj(lng, lat);
		double g_lng = dataMap.get(OUT_LNG);
		double g_lat = dataMap.get(OUT_LAT);
		double outlat = 2 * lat - g_lat;
		double outlng = 2 * lng - g_lng;
		BigDecimal b = BigDecimal.valueOf(outlng);
		outlng = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		b = BigDecimal.valueOf(outlat);
		outlat = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		Map<String, Double> ret = new HashMap<>();
		ret.put(OUT_LNG, outlng);
		ret.put(OUT_LAT, outlat);
		return ret;
	}

	private static double TransformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	private static double Transformlng(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
		return ret;
	}

	public static Map<String, Double> Gcj2BD(double lng, double lat) {
		double x = lng, y = lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		double outlng = z * Math.cos(theta) + 0.0065;
		double outlat = z * Math.sin(theta) + 0.006;
		BigDecimal b = BigDecimal.valueOf(outlng);
		outlng = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		b = BigDecimal.valueOf(outlat);
		outlat = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		Map<String, Double> ret = new HashMap<>();
		ret.put(OUT_LNG, outlng);
		ret.put(OUT_LAT, outlat);
		return ret;
	}

	public static Map<String, Double> BD2Gcj(double lng, double lat) {
		double x = lng - 0.0065, y = lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		double outlng = z * Math.cos(theta);
		double outlat = z * Math.sin(theta);
		BigDecimal b = BigDecimal.valueOf(outlng);
		outlng = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		b = BigDecimal.valueOf(outlat);
		outlat = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		Map<String, Double> ret = new HashMap<>();
		ret.put(OUT_LNG, outlng);
		ret.put(OUT_LAT, outlat);
		return ret;
	}

	public static boolean OutOfChina(double lng, double lat) {
		if (lng < 72.004 || lng > 137.8347) {
            return true;
        }
		if (lat < 0.8293 || lat > 55.8271) {
            return true;
        }
		return false;
	}


	public static Map<String, Double> Wgs2MB(double lng, double lat) {
		lng = lng * 100000 % 36000000;
		lat = lat * 100000 % 36000000;
		double outlng = (((Math.cos(lat / 100000)) * (lng / 18000)) + ((Math.sin(lng / 100000)) * (lat / 9000)) + lng) / 100000.0;
		double outlat = (((Math.sin(lat / 100000)) * (lng / 18000)) + ((Math.cos(lng / 100000)) * (lat / 9000)) + lat) / 100000.0;
		BigDecimal b = BigDecimal.valueOf(outlng);
		outlng = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		b = BigDecimal.valueOf(outlat);
		outlat = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		Map<String, Double> ret = new HashMap<>();
		ret.put(OUT_LNG, outlng);
		ret.put(OUT_LAT, outlat);
		return ret;
	}

	public static Map<String, Double> MB2Wgs(double lng, double lat) {
		lng = lng * 100000 % 36000000;
		lat = lat * 100000 % 36000000;
		double x = ((Math.cos(lat / 100000)) * (lng / 18000)) + ((Math.sin(lng / 100000)) * (lat / 9000)) + lng;
		double y = ((Math.sin(lat / 100000)) * (lng / 18000)) + ((Math.cos(lng / 100000)) * (lat / 9000)) + lat;
		double outlng = (-(((Math.cos(y / 100000)) * (x / 18000)) + ((Math.sin(x / 100000)) * (y / 9000))) + lng + ((lng > 0) ? 1 : -1)) / 100000.0;
		double outlat = (-(((Math.sin(y / 100000)) * (x / 18000)) + ((Math.cos(x / 100000)) * (y / 9000))) + lat + ((lat > 0) ? 1 : -1)) / 100000.0;
		BigDecimal b = BigDecimal.valueOf(outlng);
		outlng = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		b = BigDecimal.valueOf(outlat);
		outlat = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		Map<String, Double> ret = new HashMap<>();
		ret.put(OUT_LNG, outlng);
		ret.put(OUT_LAT, outlat);
		return ret;
	}
}