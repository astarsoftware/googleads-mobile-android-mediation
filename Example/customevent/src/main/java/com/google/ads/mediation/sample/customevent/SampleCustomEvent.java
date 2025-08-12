/*
 * Copyright (C) 2014 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.ads.mediation.sample.customevent;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.ads.mediation.sample.sdk.SampleAdRequest;
import com.google.android.gms.ads.VersionInfo;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdConfiguration;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationAppOpenAd;
import com.google.android.gms.ads.mediation.MediationAppOpenAdCallback;
import com.google.android.gms.ads.mediation.MediationAppOpenAdConfiguration;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationNativeAdCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import java.util.List;

/**
 * A custom event for the Sample ad network. Custom events allow publishers to write their own
 * mediation adapter.
 *
 * <p>Since the custom event is not directly referenced by the Google Mobile Ads SDK and is instead
 * instantiated with reflection, it's possible that ProGuard might remove it. Use the {@link Keep}}
 * annotation to make sure that the adapter is not removed when minifying the project.
 */
@Keep
public class SampleCustomEvent extends Adapter {

  protected static final String TAG = SampleCustomEvent.class.getSimpleName();

  /**
   * Example of an extra field that publishers can use for a Native ad. In this example, the String
   * is added to a {@link Bundle} in {@link SampleNativeAdMapper}.
   */
  public static final String DEGREE_OF_AWESOMENESS = "DegreeOfAwesomeness";

  /**
   * The pixel-to-dpi scale for images downloaded from the sample SDK's URL values. Scale value is
   * set in {@link SampleNativeMappedImage}.
   */
  public static final double SAMPLE_SDK_IMAGE_SCALE = 1.0;

  private SampleAppOpenCustomEventLoader appOpenLoader;

  private SampleBannerCustomEventLoader bannerLoader;

  private SampleInterstitialCustomEventLoader interstitialLoader;

  private SampleRewardedCustomEventLoader rewardedLoader;

  private SampleNativeCustomEventLoader nativeLoader;

  @Override
  public void loadAppOpenAd(
      @NonNull MediationAppOpenAdConfiguration adConfiguration,
      @NonNull MediationAdLoadCallback<MediationAppOpenAd, MediationAppOpenAdCallback> callback) {
    appOpenLoader = new SampleAppOpenCustomEventLoader(adConfiguration, callback);
    appOpenLoader.loadAd();
  }

  @Override
  public void loadBannerAd(
      @NonNull MediationBannerAdConfiguration adConfiguration,
      @NonNull MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> callback) {
    bannerLoader = new SampleBannerCustomEventLoader(adConfiguration, callback);
    bannerLoader.loadAd();
  }

  @Override
  public void loadInterstitialAd(
      @NonNull MediationInterstitialAdConfiguration adConfiguration,
      @NonNull
          MediationAdLoadCallback<MediationInterstitialAd, MediationInterstitialAdCallback>
              callback) {
    interstitialLoader = new SampleInterstitialCustomEventLoader(adConfiguration, callback);
    interstitialLoader.loadAd();
  }

  @Override
  public void loadRewardedAd(
      @NonNull MediationRewardedAdConfiguration mediationRewardedAdConfiguration,
      @NonNull
          MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback>
              mediationAdLoadCallback) {
    rewardedLoader =
        new SampleRewardedCustomEventLoader(
            mediationRewardedAdConfiguration, mediationAdLoadCallback);
    rewardedLoader.loadAd();
  }

  @Override
  public void loadRewardedInterstitialAd(
      @NonNull MediationRewardedAdConfiguration adConfiguration,
      @NonNull MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> callback) {
    // The Sample SDK does not distinguish between rewarded and rewarded interstitial ads.
    // Load a rewarded ad instead.
    loadRewardedAd(adConfiguration, callback);
  }

  @Override
  public void loadNativeAdMapper(
      @NonNull MediationNativeAdConfiguration adConfiguration,
      @NonNull MediationAdLoadCallback<NativeAdMapper, MediationNativeAdCallback> callback) {
    nativeLoader = new SampleNativeCustomEventLoader(adConfiguration, callback);
    nativeLoader.loadAd();
  }

  @Override
  public void initialize(@NonNull Context context,
      @NonNull InitializationCompleteCallback initializationCompleteCallback,
      @NonNull List<MediationConfiguration> list) {
    // The Sample SDK does not have a method to initialize.
    // Therefore, call the success callback immediately.
    initializationCompleteCallback.onInitializationSucceeded();
  }

  @Override
  @NonNull
  public VersionInfo getVersionInfo() {
    String versionString = BuildConfig.ADAPTER_VERSION;
    String[] splits = versionString.split("\\.");

    if (splits.length >= 4) {
      int major = Integer.parseInt(splits[0]);
      int minor = Integer.parseInt(splits[1]);
      int micro = Integer.parseInt(splits[2]) * 100 + Integer.parseInt(splits[3]);
      return new VersionInfo(major, minor, micro);
    }

    return new VersionInfo(0, 0, 0);
  }

  @Override
  @NonNull
  public VersionInfo getSDKVersionInfo() {
    String versionString = SampleAdRequest.getSDKVersion();
    String[] splits = versionString.split("\\.");

    if (splits.length >= 3) {
      int major = Integer.parseInt(splits[0]);
      int minor = Integer.parseInt(splits[1]);
      int micro = Integer.parseInt(splits[2]);
      return new VersionInfo(major, minor, micro);
    }

    return new VersionInfo(0, 0, 0);
  }

  /**
   * Helper method to create a {@link SampleAdRequest}.
   *
   * @param mediationAdConfiguration The mediation request with targeting information.
   * @return The created {@link SampleAdRequest}.
   */
  @NonNull
  public static SampleAdRequest createSampleRequest(
      @NonNull MediationAdConfiguration mediationAdConfiguration) {
    SampleAdRequest request = new SampleAdRequest();
    request.setTestMode(mediationAdConfiguration.isTestRequest());
    request.setKeywords(mediationAdConfiguration.getMediationExtras().keySet());
    return request;
  }
}
