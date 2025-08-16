package com.google.ads.mediation.applovin;

import android.os.Bundle;

import com.applovin.impl.sdk.AppLovinAdBase;

import java.util.HashMap;
import java.util.Map;

public class AstarAppLovinUtils {

	public static Map<String, Object> getNetworkInfo(AppLovinAdBase adBase) {
		Map<String, Object> networkInfo = new HashMap<>();
		if(adBase.getAdDomain() != null) {
			networkInfo.put("ad_domain", adBase.getAdDomain());
		}
		networkInfo.put("ad_id_number", adBase.getAdIdNumber());
		if(adBase.getClCode() != null) {
			networkInfo.put("cl_code", adBase.getClCode());
		}
		if(adBase.getDspId() != null) {
			networkInfo.put("dsp_id", adBase.getDspId());
		}
		if(adBase.getDspName() != null) {
			networkInfo.put("dsp_name", adBase.getDspName());
		}
		if(adBase.getDirectDownloadToken() != null) {
			networkInfo.put("direct_download_token", adBase.getDirectDownloadToken());
		}
		if(adBase.getDirectDownloadParameters() != null) {
			networkInfo.put("direct_download_params", bundleToSerializableMap(adBase.getDirectDownloadParameters()));
		}
		return networkInfo;
	}

	public static Map<String, Object> bundleToSerializableMap(Bundle bundle) {
		Map<String, Object> map = new HashMap<>();
		for (String key : bundle.keySet()) {
			Object value = bundle.get(key);
			Object jsonValue = null;

			if (value instanceof Bundle) {
				// Recursively convert nested Bundles
				jsonValue = bundleToSerializableMap((Bundle) value);
			} else if (value instanceof CharSequence) {
				jsonValue = value.toString(); // normalize to String
			} else if (value instanceof Number) {
				jsonValue = value;
			}

			if(jsonValue != null) {
				map.put(key, jsonValue);
			}
		}
		return map;
	}

}
