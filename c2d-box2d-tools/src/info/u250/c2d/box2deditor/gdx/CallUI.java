package info.u250.c2d.box2deditor.gdx;


public interface CallUI {
	void updateToUI(Object model);
	void updateCameraInfo();
	void setupModel();
	void addModelToLeft(Object model);
	boolean isDebug();
}
