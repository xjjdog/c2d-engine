package info.u250.c2d.graphic;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V4;
import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
/**
 * Used to achieve animation. 
 * Including the specified uniform frame and rate animation and animation. 
 * To use this animation, you have to provide a time series-time and animation sequences.
 * The sequences can be NULL if you need , but the first frame can not be NULL
 * @author lycying@gmail.com
 */
public class AnimationSprite extends AdvanceSprite{
	/**
	 * the animation data
	 * @author lycying@gmail.com
	 */
	public final static class AnimationSpriteData{
		/**
		 * the loop times , if set . will switch to the loopwithtimes mode
		 */
		public int loopTimes;
		/**
		 * after play some times with loopwithtimes mode , the last image will be show .This is the
		 * index of the image
		 */
		public int waitingIndex = 0;
		/**
		 * a flag to jude if the animation is muti duration
		 */
		public boolean mutiDuration = false;
		/**
		 * if set this attribute , animation will loop flower the frame Durations
		 */
		public float[] frameDurations;
		/**
		 * An attribute to record the time of once loop
		 */
		public float timeLoopOnceDuration = 0.000000f;
		/**
		 * sync time , use for append delta time
		 */
		public float sync_frame_stateTime = 0;
		/**
		 * main key frames
		 */
		public TextureRegion[] keyFrames;
		/**
		 * duration of per frames
		 */
		public float frameDuration;
		/**
		 * the mode
		 */
		public AnimationMode mode = AnimationMode.JUSTLOOP;
		/**
		 * the alpha value
		 */
		public float alphaModulation = 1;
		
		protected boolean lastFrame = false;
	}
	/**
	 * if stop or not
	 */
	private boolean stop = false;
	/**
	 * AnimationSpriteData
	 */
	private AnimationSpriteData data ;
	@Override
	public void flip(boolean x, boolean y) {
		for(TextureRegion region:data.keyFrames){
			if(null!=region){
				region.flip(x, y);
			}
		}
		float[] vertices = this.getVertices();
		if (x) {
			float temp = vertices[U1];
			vertices[U1] = vertices[U3];
			vertices[U3] = temp;
			temp = vertices[U2];
			vertices[U2] = vertices[U4];
			vertices[U4] = temp;
		}
		if (y) {
			float temp = vertices[V1];
			vertices[V1] = vertices[V3];
			vertices[V3] = temp;
			temp = vertices[V2];
			vertices[V2] = vertices[V4];
			vertices[V4] = temp;
		}
	}
	/**
	 * make a new animation sprite from the atlas file . if your textureRegions's names are abc. then we will try to load abc1,abc2,abc3,abc4....until its null
	 * @param frameDuration
	 * @param atlasFile
	 * @param prefix
	 */
	public AnimationSprite(float frameDuration, final TextureAtlas ta,final String prefix){
		this(frameDuration,keyFramesFromTextureAtlas(ta,prefix));
	}
	private static TextureRegion[] keyFramesFromTextureAtlas(final TextureAtlas ta,final String prefix){
		final Array<TextureRegion> array = new Array<TextureRegion>();
		for(int count=1;;count++){
			TextureRegion tr = ta.findRegion(prefix+count);
			if(null==tr){
				return array.toArray(TextureRegion.class);
			}else{
				array.add(tr);
			}
		}
	}
	/**
	 * Create a new animation, the fiSrst parameter is the time interval between frame, keyFrames
	 * refers to all of the frame
	 */
	public AnimationSprite(float frameDuration, TextureRegion[] keyFrames) {
		// Initialize use the first textureRegion
		super(keyFrames[0]);
		data = new AnimationSpriteData();
		data.keyFrames = keyFrames;
		data.frameDuration = frameDuration;
		data.timeLoopOnceDuration = data.frameDuration * data.keyFrames.length;
	}

	/**
	 * Create a new animation, the first parameter is the time interval of every frame, keyFrames
	 * refers to all of the frame The frameDurations.length should equals the keyFrames.length , all
	 * is your choose
	 */
	public AnimationSprite(float[] frameDurations, TextureRegion[] keyFrames) {
		// Initialize use the first textureRegion
		super(keyFrames[0]);
		data = new AnimationSpriteData();
		data.keyFrames = keyFrames;
		data.frameDurations = frameDurations;
		for (float f : frameDurations) {
			data.timeLoopOnceDuration += f;
		}
		data.mutiDuration = true;
	}
	

	/**
	 * After you call the method
	 * {@link AnimationSprite#setAnimationSpriteListener(AnimationSpriteListener)} Every time when
	 * the animation sprite reach the lastest frame , will call the onlastFrame method
	 */
	public interface AnimationSpriteListener {
		public void onLastFrame();
	}

	private AnimationSpriteListener l;

	/**
	 * @see AnimationSpriteListener
	 * @param l
	 */
	public void setAnimationSpriteListener(AnimationSpriteListener l) {
		this.l = l;
	}

	/**
	 * inner sync attributes
	 */
	private enum AnimationMode {JUSTLOOP, LOOPWITHTIMES,}

	/**
	 * Use alphaModulation , Can draw a transparent or translucent images
	 */
	public void setAlphaModulation(float alphaModulation) {
		data.alphaModulation = alphaModulation;
	}

	/**
	 * Construct a wizard animation, you can set a number of cycles, when the number of cycles is
	 * reached, will stop the animation
	 */
	public void setLoopTimes(int loopTimes) {
		data.mode = AnimationMode.LOOPWITHTIMES;
		data.loopTimes = loopTimes;
	}

	/**
	 * go on to action the animation
	 */
	public void play() {
		this.stop = false;
	}

	/**
	 * stop the animation now !
	 */
	public void stop() {
		this.stop = true;
	}

	/**
	 * Re-start the animation, if you set the number of looopTimes, will be re-played until the
	 * number reaches
	 */
	public void replay() {
		data.sync_frame_stateTime = 0l;
		this.play();
	}

	/**
	 * This will set the wait image if the animation stop
	 */
	public void setWaitingIndex(int index) {
		if (index <= data.keyFrames.length - 1)
			data.waitingIndex = index;
		else
			data.waitingIndex = 0;
	}

	/**
	 * If you want to change the speed after playing the animation, then use this method
	 */
	public void changeFrameDuration(float frameDuration) {
		data.frameDuration = frameDuration;
		data.timeLoopOnceDuration = data.frameDuration * data.keyFrames.length;
	}

	/**
	 * see {@link #changeFrameDuration(float)} the frameDurations.length must equals the number of
	 * the keyframes
	 */
	public void changeFrameDuration(float[] frameDurations) {
		data.frameDurations = frameDurations;
		for (float f : data.frameDurations) {
			data.timeLoopOnceDuration += f;
		}
		data.mutiDuration = true;
	}
	/**
	 * Draw the sprite and its animation.
	 * do not call the 'draw' method if you want the animation came true<br/>
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		data.sync_frame_stateTime += delta;
		if (stop)
			this.drawWaitFrame();
		else {
			switch (data.mode) {
			case JUSTLOOP:
				this.justDraw();
				break;
			case LOOPWITHTIMES:
				if ((int) (data.sync_frame_stateTime / data.timeLoopOnceDuration) < data.loopTimes) {
					this.justDraw();
				} else {
					this.drawWaitFrame();
				}
				break;
			}
		}
	}

	/**
	 * Draw wait frame
	 */
	private void drawWaitFrame() {
		TextureRegion region = data.keyFrames[data.waitingIndex];
		if (null != region) {
			this.setRegion(region);
			if (1 == data.alphaModulation) {
				this.draw(Engine.getSpriteBatch());
			} else {
				this.draw(Engine.getSpriteBatch(), data.alphaModulation);
			}

		}
	}
	/**
	 * copy one sprite's animation information to another . and the position such as x , y is not copy
	 * @param sprite
	 */
	public void set(AnimationSprite sprite){
		this.stop = sprite.stop;
		this.data = sprite.data;
	}

	private void justDraw() {
		TextureRegion region;
		if (data.mutiDuration) {
			region = getKeyFrameNorEqualDuration();
		} else {
			region = getKeyFrameEqualDuration();
		}

		if(region == data.keyFrames[data.keyFrames.length-1]){
			if (null != l){
				if(!data.lastFrame){
					this.l.onLastFrame();
					data.lastFrame = true;
				}
			}
		}else{
			data.lastFrame = false;
		}
		if (null != region) {
			this.setRegion(region);
			if (1 == data.alphaModulation) {
				super.draw(Engine.getSpriteBatch());
			} else {
				super.draw(Engine.getSpriteBatch(), data.alphaModulation);
			}
		}
	}

	private TextureRegion getKeyFrameEqualDuration() {
		int frameNumber = (int) (data.sync_frame_stateTime / data.frameDuration);
		frameNumber = frameNumber % data.keyFrames.length;
		return data.keyFrames[frameNumber];
	}

	private TextureRegion getKeyFrameNorEqualDuration() {
		int result = 0;
		float current = data.sync_frame_stateTime % data.timeLoopOnceDuration;
		float cursor = 0;
		for (int i = 0; i < data.frameDurations.length; i++) {
			if (cursor >= current) {
				result = i;
				// FIX BUG : should break first .
				break;
			} else {
				cursor += data.frameDurations[i];
			}
		}
		return data.keyFrames[result];
	}
	/**
	 * @return the animation sprite data 
	 */
	public AnimationSpriteData getAnimationSpriteData(){
		return this.data;
	}
}
 