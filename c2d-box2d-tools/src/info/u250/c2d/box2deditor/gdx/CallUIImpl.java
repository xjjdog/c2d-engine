package info.u250.c2d.box2deditor.gdx;

import info.u250.c2d.box2deditor.Main;

import java.awt.EventQueue;

public class CallUIImpl implements CallUI {

	/**
	 * We put our request to the event queue so no error properm again
	 */
	@Override
	public void updateToUI(final Object model) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Main.bindUI(model);
            }
        });
	}

	@Override
	public void updateCameraInfo() {
		Main.updateCameraInfo();
	}

	@Override
	public void setupModel() {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Main.setupModel();
            }
        });
	}


	@Override
	public void addModelToLeft(final Object model) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Main.addModel(model);
            }
        });
	}

	@Override
	public boolean isDebug() {
		return Main.isDebug();
	}

}
