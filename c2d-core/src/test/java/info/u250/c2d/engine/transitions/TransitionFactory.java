package info.u250.c2d.engine.transitions;

import info.u250.c2d.engine.CoreProvider.TransitionType;
import info.u250.c2d.engine.Transition;

public class TransitionFactory {
	static Transition RotateWithZoomIn,FlipHorizontal,FlipVertical,ZoomIn,Rotate,ZoomOut,Fade,
		SlideInLeft,SlideInRight,SlideInTop,SlideInBottom,MoveInLeft,MoveInRight,MoveInTop,MoveInBottom,FadeWhite;
	public static Transition getTransitionScene(TransitionType type){
		switch(type){
		case RotateWithZoomIn:
			if(null == RotateWithZoomIn){
				RotateWithZoomIn = new TransitionSceneRotateWithZoomIn();
			}
			return RotateWithZoomIn;
		case FlipHorizontal:
			if(null == FlipHorizontal){
				FlipHorizontal = new TransitionSceneFlipHorizontal();
			}
			return FlipHorizontal;
		case FlipVertical:
			if(null == FlipVertical){
				FlipVertical = new TransitionSceneFlipVertical();
			}
			return FlipVertical;
		case ZoomIn:
			if(null == ZoomIn){
				ZoomIn = new TransitionSceneZoomIn();
			}
			return ZoomIn;
		case Rotate:
			if(null == Rotate){
				Rotate = new TransitionSceneRotate();
			}
			return Rotate;
		case ZoomOut:
			if(null == ZoomOut){
				ZoomOut = new TransitionSceneZoomOut();
			}
			return ZoomOut;
		case Fade:
			if(null == Fade){
				Fade = new TransitionSceneFade();
			}
			return Fade;
		case SlideInLeft:
			if(null == SlideInLeft){
				SlideInLeft = new TransitionSceneSlideInLeft();
			}
			return SlideInLeft;
		case SlideInRight:
			if(null == SlideInRight){
				SlideInRight = new TransitionSceneSlideInRight();
			}
			return SlideInRight;
		case SlideInTop:
			if(null == SlideInTop){
				SlideInTop = new TransitionSceneSlideInTop();
			}
			return SlideInTop;
		case SlideInBottom:
			if(null == SlideInBottom){
				SlideInBottom = new TransitionSceneSlideInBottom();
			}
			return SlideInBottom;
		case MoveInLeft:
			if(null == MoveInLeft){
				MoveInLeft = new TransitionSceneMoveInLeft();
			}
			return MoveInLeft;
		case MoveInRight:
			if(null == MoveInRight){
				MoveInRight = new TransitionSceneMoveInRight();
			}
			return MoveInRight;
		case MoveInTop:
			if(null == MoveInTop){
				MoveInTop = new TransitionSceneMoveInTop();
			}
			return MoveInTop;
		case MoveInBottom:
			if(null == MoveInBottom){
				MoveInBottom = new TransitionSceneMoveInBottom();
			}
			return MoveInBottom;
		case FadeWhite:
			if(null == FadeWhite){
				FadeWhite = new TransitionSceneFadeWhite();
			}
			return FadeWhite;
		default:
			if(null == Fade){
				Fade = new TransitionSceneFade();
			}
			return Fade;
		}
	}
}
