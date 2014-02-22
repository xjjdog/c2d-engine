package info.u250.c2d.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/** <p>The transitionScene is a Scene itself , So it can be renderable . <br/>
 * it supply a kind of method to switch two scene when you call {@link info.u250.c2d.engine.Engine#setMainScene(Scene)}<br/>
 * This base class supplies some base method and default action when switching . <br/>
 * It simple set the scene2 to mainScene when scene1 is NULL. Also, to ensure the user's input go what it should be , we unfoucs all the stage .<br/>
 * Any user defined transition must extends this class . </p>
 * @author lycying@gmail.com
 */
public abstract class Transition implements Scene{
	/**<p> Really do switch between scene1 and scene2
	 * @param scene1 the first scene 
	 * @param scene2 the incoming scene
	 * @param halfDurationMillis 
	 */
	protected abstract void doTransition(int halfDurationMillis);
	/**if the system is in transition. When its true , new transition is not allowed 
	 */
	private boolean transiting = false;
	/**just want to make this method and {@link info.u250.c2d.engine.Engine#_setMainScene(Scene)} not accessible . 
	 */
	protected void doSetMainScene(Scene scene2){
		Engine._setMainScene(scene2);
	}

	/**the scene2 */
	protected Scene incoming ;
	/**the scene1 */
	protected Scene outgoing;
	/**reset the transition to wait for next transition action */
	public void reset(){
		setTransiting(false);
		incoming = null;
		outgoing = null;
	}
	/**do transition */
	public void transition(final Scene scene1,final Scene scene2,final int halfDurationMillis ){
		this.incoming = scene2;
		this.outgoing = scene1;

		
		transiting = true;
		//user input is not allowed during transiting 
		Gdx.input.setInputProcessor(null);
				
		if(null==scene1){
			Engine._setMainScene(scene2);
			Gdx.input.setInputProcessor(scene2.getInputProcessor());
			scene2.show();
			transiting = false;
		}else{
			this.doTransition(halfDurationMillis);
		}
	}
	public void setTransiting(boolean transiting) {
		this.transiting = transiting;
	}
	public boolean isTransiting() {
		return transiting;
	}
	
	@Override
	public void update(float delta) {}
	@Override
	public void show() {}
	@Override
	public void hide() {}
	@Override
	public InputProcessor getInputProcessor() {
		return null;
	}
}
