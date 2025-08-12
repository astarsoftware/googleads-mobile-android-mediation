package com.google.ads.mediation.vungle

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.gms.ads.AdSize
import com.google.common.truth.Truth.assertThat
import com.vungle.ads.VungleAdSize
import com.vungle.mediation.VungleInterstitialAdapter
import org.junit.Test
import org.junit.runner.RunWith

/** Unit tests for the utility function getVungleBannerAdSizeFromGoogleAdSize() */
@RunWith(AndroidJUnit4::class)
class VungleBannerSizeAdapterTest {

  private val context = ApplicationProvider.getApplicationContext<Context>()

  @Test
  fun getVungleBannerAdSize_forGoogleSize300By50_returnsLiftoffSizeBannerShort() {
    val liftoffBannerSize =
      VungleInterstitialAdapter.getVungleBannerAdSizeFromGoogleAdSize(AdSize(300, 50), PLACEMENT_ID)

    assertThat(liftoffBannerSize).isEqualTo(VungleAdSize.BANNER_SHORT)
  }

  @Test
  fun getVungleBannerAdSize_forGoogleSizeRegularBanner_returnsLiftoffSizeRegularBanner() {
    val liftoffBannerSize =
      VungleInterstitialAdapter.getVungleBannerAdSizeFromGoogleAdSize(AdSize.BANNER, PLACEMENT_ID)

    assertThat(liftoffBannerSize).isEqualTo(VungleAdSize.BANNER)
  }

  @Test
  fun getVungleBannerAdSize_forGoogleSizeLeaderboard_returnsLiftoffSizeLeaderboard() {
    val liftoffBannerSize =
      VungleInterstitialAdapter.getVungleBannerAdSizeFromGoogleAdSize(
        AdSize.LEADERBOARD,
        PLACEMENT_ID,
      )

    assertThat(liftoffBannerSize).isEqualTo(VungleAdSize.BANNER_LEADERBOARD)
  }

  @Test
  fun getVungleBannerAdSize_forGoogleSizeMediumRectangle_returnsLiftoffSizeMediumRectangle() {
    val liftoffBannerSize =
      VungleInterstitialAdapter.getVungleBannerAdSizeFromGoogleAdSize(
        AdSize.MEDIUM_RECTANGLE,
        PLACEMENT_ID,
      )

    assertThat(liftoffBannerSize).isEqualTo(VungleAdSize.MREC)
  }

  @Test
  fun getVungleBannerAdSize_forNonStandardGoogleBannerSize_returnsCustomSize() {
    val liftoffBannerSize =
      VungleInterstitialAdapter.getVungleBannerAdSizeFromGoogleAdSize(
        AdSize.WIDE_SKYSCRAPER,
        PLACEMENT_ID,
      )

    assertThat(liftoffBannerSize).isNotNull()
    assertThat(liftoffBannerSize.width).isEqualTo(AdSize.WIDE_SKYSCRAPER.width)
    assertThat(liftoffBannerSize.height).isEqualTo(AdSize.WIDE_SKYSCRAPER.height)
  }

  private companion object {
    const val PLACEMENT_ID = "testPlacementId"
  }
}
