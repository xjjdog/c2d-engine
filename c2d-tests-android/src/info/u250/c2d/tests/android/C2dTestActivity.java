package info.u250.c2d.tests.android;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.tests.C2dTests;
import android.os.Bundle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class C2dTestActivity extends AndroidApplication {

	public void onCreate (Bundle bundle) {
		super.onCreate(bundle);

		Bundle extras = getIntent().getExtras();
		String testName = (String)extras.get("test");

		ApplicationListener test = C2dTests.newTestFullName(testName);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGL20 = Engine.useGL20();
		config.numSamples = 2;
		initialize(test, config);
	}
}
