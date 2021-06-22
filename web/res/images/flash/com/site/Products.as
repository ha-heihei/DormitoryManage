package com.site{
	import flash.display.*;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.text.TextField;
	import flash.events.*;

	public class Products extends Sprite {
		private var _arrCategories:Array=["all","apple","pear","banana","orange"];
		private var _xml:XML;
		private var _curId:Number=0;
		private var _filter:String="all";
		private var _loader:URLLoader;

		public function Products():void {
			init();
		}
		private function init():void {
			_loader = new URLLoader(new URLRequest("products.xml"));
			_loader.addEventListener(Event.COMPLETE, onXmlLoadComplete);
		}
		private function onXmlLoadComplete(event:Event):void {
			_xml = new XML(_loader.data);
			initMenus();
			focusMenus();
			getList();
		}
		private function initMenus():void {
			for (var i:Number=0; i<5; i++) {
				this["m" + i].num=i;
				this["m" + i].buttonMode=true;
				this["m" + i].addEventListener(MouseEvent.CLICK,clickBtn);
				this["m" + i].addEventListener(MouseEvent.MOUSE_OVER,overBtn);
				this["m" + i].addEventListener(MouseEvent.MOUSE_OUT,outBtn);
			}
		}
		private function clickBtn(event:MouseEvent):void {
			openCategory(event.target.num);
			focusMenus();
		}
		private function overBtn(event:MouseEvent):void {
			if (event.target.num!=_curId) {
				event.target.alpha=.5;
			}
		}
		private function outBtn(event:MouseEvent):void {
			if (event.target.num!=_curId) {
				event.target.alpha=0;
			}
		}
		private function openCategory(num:Number):void {
			_curId=num;
			getList();
		}
		private function focusMenus():void {
			for (var i:Number=0; i<5; i++) {
				this["m"+i].alpha=0;
			}
			this["m"+_curId].alpha=1;
		}
		private function getList():void {
			mList.visible=true;
			mDetail.visible=false;
			removeAllProduct();
			var i:Number=0;
			var j:Number=0;
			var column:Number=5;
			for each (var xmlItem:XML in _xml.item) {
				if (xmlItem.category==_arrCategories[_curId] || _arrCategories[_curId]=="all") {
					var item:Thumb = new Thumb();
					mList.addChild(item);
					item.num=j;
					item.txt.text=xmlItem.title;
					item.x = 140*(i-(Math.ceil((i+1)/column)-1)*column);
					item.y = 150*(Math.ceil((i+1)/column)-1);
					var loader:Loader = new Loader();
					loader.load(new URLRequest(xmlItem.thumb));
					item.addChild(loader);
					loader.mouseEnabled=false;
					loader.x=3;
					loader.y=3;
					item.buttonMode=true;
					item.addEventListener(MouseEvent.CLICK,clickThumb);
					item.addEventListener(MouseEvent.MOUSE_OVER,overThumb);
					item.addEventListener(MouseEvent.MOUSE_OUT,outThumb);
					i++;
				}
				j++;
			}
		}
		private function removeAllProduct():void {
			var _loopTimes:Number = mList.numChildren;
			for (var i:Number=0; i<_loopTimes; i++) {
				mList.removeChildAt(0);
			}
		}
		private function overThumb(event:MouseEvent):void {
			event.target.alpha=.5;
		}
		private function outThumb(event:MouseEvent):void {
			event.target.alpha=1;
		}
		private function clickThumb(event:MouseEvent):void {
			openProduct(event.target.num);
		}
		private function openProduct(num:Number):void {
			removeAllDetail();
			mList.visible=false;
			mDetail.visible=true;
			var item:Fullsize = new Fullsize();
			var loader:Loader = new Loader();
			loader.load(new URLRequest(_xml.item[num].fullsize));
			item.addChild(loader);
			loader.x=3;
			loader.y=3;
			item.tTitle.text=_xml.item[num].title;
			item.tContent.htmlText=_xml.item[num].content;
			item.bBack.addEventListener(MouseEvent.CLICK,clickBack);
			mDetail.addChild(item);
		}
		private function removeAllDetail():void {
			var _loopTimes:Number = mDetail.numChildren;
			for (var i:Number=0; i<_loopTimes; i++) {
				mDetail.removeChildAt(0);
			}
		}
		private function clickBack(event:MouseEvent):void {
			mList.visible=true;
			mDetail.visible=false;
		}
	}
}