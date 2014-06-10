package info.u250.c2d.tests.android;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.tests.C2dTests;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class C2dTestActivity extends AndroidApplication {
	protected AdView adView;
	RelativeLayout layout;
	public void onCreate (Bundle bundle) {
		super.onCreate(bundle);
		
		layout = new RelativeLayout(this);
		adView = new AdView(this); // Put in your secret key here
		adView.setAdUnitId(getString(R.string.admob_id));
		adView.setAdSize(AdSize.BANNER);

		Bundle extras = getIntent().getExtras();
		String testName = (String)extras.get("test");
		String testLabel = (String)extras.get("name");
		setTitle("C2d Test-"+testLabel);

		ApplicationListener test = C2dTests.newTestFullName(testName);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.resolutionStrategy = new RatioResolutionStrategy(Engine.getWidth(), Engine.getHeight());
		config.numSamples = 2;
		View main = initializeForView(test, config);
		layout.addView(main);
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		layout.addView(adView, adParams);
		setContentView(layout);

		// InterstitialAd mInterstitial = new InterstitialAd(this);
		// mInterstitial.setAdUnitId(XID);
		// mInterstitial.loadAd(new AdRequest.Builder().build());
		// mInterstitial.show();

		adView.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
	}
	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	@Override
	public void onDestroy() {
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
