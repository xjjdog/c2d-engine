package info.u250.c2d.tests.transitions;

import info.u250.c2d.engine.CoreProvider.TransitionType;


public class FlipHTest extends AbstractTransiton{

	@Override
	TransitionType a2b() {
		return TransitionType.FlipHorizontal;
	}

	@Override
	TransitionType b2a() {
		return TransitionType.FlipHorizontal;
	}
	
}
