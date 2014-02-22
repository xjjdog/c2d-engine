package info.u250.c2d.box2deditor.gdx.support;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class DefaultLabel extends Label{
	private DefaultLabel(CharSequence text, LabelStyle style) {
		super(text, style);
	}

	public static DefaultLabel getDefaultLabel(String text){
		LabelStyle style = new LabelStyle(Engine.getDefaultFont(),Color.WHITE);
		return new DefaultLabel(text, style);
	}
}
