package com.site{
	import flash.display.*;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.events.*;
	import com.site.ui.Scroller;
	
	public class News extends MovieClip {
		private var _xml:XML;
		private var _scrollBar:Scroller;
		private var _loader:URLLoader;

		public function News() {
			init();
		}
		private function loadNews():void {
			_loader = new URLLoader(new URLRequest("news.xml"));
			_loader.addEventListener(Event.COMPLETE, onXmlLoadComplete);
		}
		private function onXmlLoadComplete(event:Event):void {
			_xml = new XML(_loader.data);
			showText();
		}
		private function showText():void {
			var temp_str:String="";
			for each (var xmlItem:XML in _xml.item) {
				temp_str+="<font size='11'>"+xmlItem.date+"</font><br><font size='12'>"+xmlItem.content+"</font><br><br>";
			}
			_scrollBar=new Scroller();
			addChild(_scrollBar);
			_scrollBar.content=temp_str;
			_scrollBar.init();
			_scrollBar.x=520;
			_scrollBar.y=140;
		}
		private function init():void {
			loadNews();
		}
	}
}