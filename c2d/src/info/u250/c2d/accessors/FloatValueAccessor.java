package info.u250.c2d.accessors;

import info.u250.c2d.accessors.FloatValueAccessor.FloatValue;
import aurelienribon.tweenengine.TweenAccessor;


public class FloatValueAccessor  implements TweenAccessor<FloatValue>{
	public static int VALUE = 1;
	public static class FloatValue{
		float value;

		public float getValue() {
			return value;
		}

		public void setValue(float value) {
			this.value = value;
		}
	}
	@Override
	public int getValues(FloatValue target, int tweenType, float[] returnValues) {
		if(VALUE == tweenType){
			returnValues[0] = target.getValue();
			return 1;
		}
		return -1;
	}

	@Override
	public void setValues(FloatValue target, int tweenType, float[] newValues) {
		if(VALUE == tweenType){
			target.setValue(newValues[0]);
		}
	}
}
