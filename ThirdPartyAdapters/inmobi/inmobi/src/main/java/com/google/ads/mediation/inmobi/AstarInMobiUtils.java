package com.google.ads.mediation.inmobi;

import com.inmobi.ads.AdMetaInfo;

import java.util.HashMap;
import java.util.Map;

public class AstarInMobiUtils {

	public static Map<String, Object> getNetworkInfo(AdMetaInfo adMetaInfo) {
		Map<String, Object> networkInfo = new HashMap<>();
		if(adMetaInfo.getCreativeID() != null) {
			networkInfo.put("creative_id", adMetaInfo.getCreativeID());
		}
		return networkInfo;
	}
}
