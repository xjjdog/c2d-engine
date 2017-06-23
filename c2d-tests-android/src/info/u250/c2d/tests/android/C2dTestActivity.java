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

public class C2dTestActivity extends AndroidApplication {
    RelativeLayout layout;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        layout = new RelativeLayout(this);
        Bundle extras = getIntent().getExtras();
        String testName = (String) extras.get("test");
        String testLabel = (String) extras.get("name");
        setTitle("C2d Test-" + testLabel);

        ApplicationListener test = C2dTests.newTestFullName(testName);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.resolutionStrategy = new RatioResolutionStrategy(Engine.getWidth(), Engine.getHeight());
        config.numSamples = 2;
        View main = initializeForView(test, config);
        layout.addView(main);
        setContentView(layout);

        // InterstitialAd mInterstitial = new InterstitialAd(this);
        // mInterstitial.setAdUnitId(XID);
        // mInterstitial.loadAd(new AdRequest.Builder().build());
        // mInterstitial.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
