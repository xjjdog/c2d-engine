package info.u250.c2d.tests.transitions;

import info.u250.c2d.engine.CoreProvider.TransitionType;

import java.util.Random;


public class SlideInTest extends AbstractTransiton {

    @Override
    TransitionType a2b() {
        return new Random().nextBoolean() ? TransitionType.SlideInLeft : TransitionType.SlideInRight;
    }

    @Override
    TransitionType b2a() {
        return new Random().nextBoolean() ? TransitionType.SlideInTop : TransitionType.SlideInBottom;
    }

}
