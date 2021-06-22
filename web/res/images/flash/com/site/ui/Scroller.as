package com.site.ui{
	import flash.display.*;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	import flash.geom.Rectangle;
	import flash.events.*;

	public class Scroller extends Sprite {
		private var _scroll_rect:Rectangle;
		public var content:String;

		public function Scroller():void {
			
		}
		public function init():void {
			initContent(content)
			mContent.mask=mMasker;
			initScrollBar();
		}
		private function initContent(str:String):void {
			mContent.txt.autoSize="left";
			mContent.txt.htmlText=str;
		}
		private function initScrollBar():void {
			_scroll_rect = new Rectangle( mScrollHandler.mScrollArea.x, mScrollHandler.mScrollArea.y, 0, mScrollHandler.mScrollArea.height - mScrollHandler.mScrollFace.height );
			mScrollHandler.mScrollFace.addEventListener( MouseEvent.MOUSE_DOWN, press_drag );
			mScrollHandler.mScrollFace.stage.addEventListener( MouseEvent.MOUSE_UP, release_drag);
			mContent.addEventListener(MouseEvent.MOUSE_WHEEL, mouseWheel);
		}
		private function mouseWheel(event : MouseEvent):void {
			mScrollHandler.mScrollFace.y-=event.delta*3;
			if (mScrollHandler.mScrollFace.y>mScrollHandler.mScrollArea.height-mScrollHandler.mScrollFace.height) {
				mScrollHandler.mScrollFace.y=mScrollHandler.mScrollArea.height-mScrollHandler.mScrollFace.height;
			} else if (mScrollHandler.mScrollFace.y<0) {
				mScrollHandler.mScrollFace.y=0;
			}
			var scrollFacePos:Number = mScrollHandler.mScrollFace.y/(mScrollHandler.mScrollArea.height-mScrollHandler.mScrollFace.height);
			mContent.y=(mMasker.height-mContent.height)*scrollFacePos;
		}
		private function press_drag(event:MouseEvent ):void {
			mScrollHandler.mScrollFace.startDrag( false, _scroll_rect );
			mScrollHandler.mScrollFace.addEventListener( Event.ENTER_FRAME, drag );
		}
		private function release_drag( event:MouseEvent ):void {
			mScrollHandler.mScrollFace.removeEventListener( Event.ENTER_FRAME, drag );
			mScrollHandler.mScrollFace.stopDrag();
		}
		private function drag(event:Event):void {
			var scrollFacePos:Number = mScrollHandler.mScrollFace.y/(mScrollHandler.mScrollArea.height-mScrollHandler.mScrollFace.height);
			mContent.y=(mMasker.height-mContent.height)*scrollFacePos;
		}
	}
}