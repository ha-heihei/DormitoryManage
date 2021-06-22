package com.site{
	import flash.display.*;
	import flash.net.URLRequest;
	import flash.events.*;
	import fl.transitions.Tween;
	import fl.transitions.easing.*;
	import fl.transitions.TweenEvent;

	public class Shell extends Sprite {
		private var _loader:Loader = new Loader();
		private var _outTween:Tween;

		public function Shell():void {
			init();
		}
		private function init():void {
			mLoader.mProgress.scaleX=0;
			loadMain();
		}
		private function loadMain():void {
			_loader.load(new URLRequest("main.swf"));
			configureListeners(_loader.contentLoaderInfo);
			addChild(_loader);
		}
		private function configureListeners(dispatcher):void {
			dispatcher.addEventListener(Event.OPEN, openHandler);
			dispatcher.addEventListener(ProgressEvent.PROGRESS, progressHandler);
			dispatcher.addEventListener(Event.COMPLETE, completeHandler);
		}
		private function openHandler(event:Event):void {
			mLoader.mProgress.scaleX=0;
		}
		private function progressHandler(event:ProgressEvent):void {
			mLoader.mProgress.scaleX=event.bytesLoaded/event.bytesTotal;
		}
		private function completeHandler(event:Event):void {
			mLoader.mProgress.scaleX=1;
			//_loader.contentLoaderInfo.content.alpha=0;
			_outTween= new Tween(mLoader, "alpha", Regular.easeOut, 1, 0, 1, true);
			_outTween.addEventListener(TweenEvent.MOTION_FINISH,mLoaderFadeOut);
		}
		private function mLoaderFadeOut(event:Event):void {
			//MovieClip(_loader.contentLoaderInfo.content).init();
		}
	}
}