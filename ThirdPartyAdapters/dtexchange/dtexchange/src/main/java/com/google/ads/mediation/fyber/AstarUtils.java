package com.google.ads.mediation.fyber;

import android.text.TextUtils;

import com.fyber.inneractive.sdk.external.ImpressionData;

import java.util.HashMap;
import java.util.Map;

public class AstarUtils {

	// astar
	public static Map<String, Object> getNetworkInfoFromImpressionData(ImpressionData impressionData) {
		Map<String, Object> networkInfo = new HashMap<>();
		if(!TextUtils.isEmpty(impressionData.getCreativeId())) {
			networkInfo.put("creative_id",impressionData.getCreativeId());
		}
		if(!TextUtils.isEmpty(impressionData.getAdvertiserDomain())) {
			networkInfo.put("advertiser_domain",impressionData.getAdvertiserDomain());
		}
		if(!TextUtils.isEmpty(impressionData.getCampaignId())) {
			networkInfo.put("campaign_id",impressionData.getCampaignId());
		}
		if(!TextUtils.isEmpty(impressionData.getImpressionId())) {
			networkInfo.put("impression_id",impressionData.getImpressionId());
		}
		if(!TextUtils.isEmpty(impressionData.getDemandSource())) {
			networkInfo.put("demand_source",impressionData.getDemandSource());
		}
		return networkInfo;
	}
}
