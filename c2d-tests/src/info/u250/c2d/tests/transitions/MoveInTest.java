package info.u250.c2d.tests.transitions;

import info.u250.c2d.engine.CoreProvider.TransitionType;

import java.util.Random;


public class MoveInTest extends AbstractTransiton {

    @Override
    TransitionType a2b() {
        return new Random().nextBoolean() ? TransitionType.MoveInLeft : TransitionType.MoveInRight;
    }

    @Override
    TransitionType b2a() {
        return new Random().nextBoolean() ? TransitionType.MoveInTop : TransitionType.MoveInBottom;
    }

}
