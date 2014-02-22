package info.u250.c2d.tests.transitions;

import java.util.Random;

import info.u250.c2d.engine.CoreProvider.TransitionType;


public class SplitTest extends AbstractTransiton{

	@Override
	TransitionType a2b() {
		return new Random().nextBoolean()?TransitionType.SplitCols:TransitionType.SplitRows;
	}

	@Override
	TransitionType b2a() {
		return new Random().nextBoolean()?TransitionType.SplitCols:TransitionType.SplitRows;
	}
	
}
