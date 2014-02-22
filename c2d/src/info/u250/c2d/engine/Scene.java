package info.u250.c2d.engine;

import com.badlogic.gdx.InputProcessor;
/**Scene is the main render loop drawable . Such as : main scene, game scene, help scene etc.
 * Many time , only one mainScene allowed besides the transitionScene.
 * The game logic can be put in {@link #update(float)} and draw the game in {@link #render(float)} so when you pause the game, the logic is no effect to the game.
 * another important method is {@link #getInputProcessor()} , which supply a user defined input process .
 * @author lycying@gmail.com
 */
public abstract interface Scene  {
	/**all the game scene logic show be here if you want */
	public abstract void update(float delta);
	/**draw the game objects that on this scene */
	public abstract void render(float delta);
	/**the show method was called when the transition scene reach the screen */
	public abstract void show();
	/**the hide method was called when the transition scene  hide the scene1 , it can be used to dispose some resources if you want */
	public abstract void hide();
	/**supply a user defined inputProcess to handle all user events in the scene */
	public abstract InputProcessor getInputProcessor();
}