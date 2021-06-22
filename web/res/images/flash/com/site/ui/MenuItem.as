package com.site.ui{
	import flash.display.*;
	import flash.events.*;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	import fl.transitions.Tween;
	import fl.transitions.easing.*;

	public class MenuItem extends MovieClip {
		public var app:MovieClip=new MovieClip();
		public var caption:String;
		public var swf:String;
		public var id:Number;
		private var _w:Number;
		private var _tween:Tween;

		public function MenuItem() {

		}
		public function init():void {
			this.buttonMode=true;
			txt.mouseEnabled=false;
			txt.autoSize=TextFieldAutoSize.LEFT;
			txt.text=caption;
			txt.x=16;
			_w=32 + txt.width;
			mMasker.width=_w;
			mBg.width=_w;
			mHitArea.width=_w;
			mBg.mask=mMasker;
			mBg.y=57;
			this.addEventListener( MouseEvent.MOUSE_OVER, over);
			this.addEventListener( MouseEvent.MOUSE_OUT, out);
			this.addEventListener( MouseEvent.CLICK, click);
		}
		private function over(event:MouseEvent):void {
			_tween = new Tween(mBg, "y", Strong.easeOut, mBg.y, 0, .3, true);
		}
		private function out(event:MouseEvent):void {
			if (app.curId!=id) {
				_tween = new Tween(mBg, "y", Strong.easeOut, mBg.y, 57, .3, true);
			}
		}
		private function click(event:MouseEvent):void {
			app.openSec(id);
		}
		public function select():void {
			_tween = new Tween(mBg, "y", Strong.easeOut, mBg.y, 0, .3, true);
		}
		public function disselect():void {
			_tween = new Tween(mBg, "y", Strong.easeOut, mBg.y, 57, .3, true);
		}
	}
}