package com.site{
	import flash.display.*;
	import flash.net.URLRequest;
	import flash.events.*;
	import fl.transitions.Tween;
	import fl.transitions.easing.*;
	import com.site.ui.MenuItem;

	public class Main extends MovieClip {
		public var curId:Number=0;
		private var _arrMenu:Array=[{caption:"Home",swf:"home.swf"},
									{caption:"About",swf:"about.swf"},
									{caption:"News & Events",swf:"news.swf"},
									{caption:"Products",swf:"products.swf"},
									{caption:"Contact",swf:"contact.swf"}];
		private var _loader:Loader=new Loader;
		private var _topInTween:Tween;
		private var _bottomInTween:Tween;
		private var _contentInTween:Tween;
		private var _ringTween:Tween;

		public function Main():void {
			init();
		}
		public function init():void {
			toStage(mTop,-189);
			toStage(mBottom,700);
			this.alpha=1;
			initMenu();
			focusMenu();
			animateMain();
			openSec(curId);
		}
		private function animateMain():void {
			_topInTween=new Tween(mTop,"y",Strong.easeInOut,mTop.y,mTop.oy,1.5,true);
			_bottomInTween=new Tween(mBottom,"y",Strong.easeInOut,mBottom.y,mBottom.oy,1.5,true);
		}
		private function toStage(mc:MovieClip,newy:Number):void {
			mc.ox=mc.x;
			mc.oy=mc.y;
			mc.y=newy;
		}
		private function initMenu():void {
			var _hisX:Number=0;
			var _loopTimes:int=_arrMenu.length;
			for (var i:Number=0; i < _loopTimes; i++) {
				var item:MenuItem=new MenuItem();
				item.name="m" + i;
				mTop.mMenu.addChild(item);
				item.app=this;
				item.caption=_arrMenu[i].caption;
				item.swf=_arrMenu[i].swf;
				item.id=i;
				item.x=_hisX;
				item.init();
				_hisX+= item.width + 9;
			}
		}
		private function focusMenu():void {
			var loopTimes:int=_arrMenu.length;
			for (var i:Number=0; i < loopTimes; i++) {
				if (curId == i) {
					MovieClip(mTop.mMenu.getChildByName("m" + i)).select();
				} else {
					MovieClip(mTop.mMenu.getChildByName("m" + i)).disselect();
				}
			}
		}
		public function openSec(id:Number):void {
			curId=id;
			focusMenu();
			_loader.load(new URLRequest(_arrMenu[id].swf));
			configureListeners(_loader.contentLoaderInfo);
			mContent.addChild(_loader);
		}
		private function configureListeners(dispatcher):void {
			dispatcher.addEventListener(Event.OPEN,openHandler);
			dispatcher.addEventListener(Event.COMPLETE,completeHandler);
		}
		private function openHandler(event:Event):void {
			_ringTween=new Tween(mRing,"alpha",Regular.easeOut,mRing.alpha,1,1,true);
		}
		private function completeHandler(event:Event):void {
			_loader.contentLoaderInfo.content.alpha=0;
			_ringTween=new Tween(mRing,"alpha",Regular.easeOut,mRing.alpha,0,1,true);
			_contentInTween=new Tween(_loader.contentLoaderInfo.content,"alpha",Regular.easeOut,0,1,1,true);
		}
	}
}