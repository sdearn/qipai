// Learn cc.Class:
//  - [Chinese] http://www.cocos.com/docs/creator/scripting/class.html
//  - [English] http://www.cocos2d-x.org/docs/editors_and_tools/creator-chapters/scripting/class/index.html
// Learn Attribute:
//  - [Chinese] http://www.cocos.com/docs/creator/scripting/reference/attributes.html
//  - [English] http://www.cocos2d-x.org/docs/editors_and_tools/creator-chapters/scripting/reference/attributes/index.html
// Learn life-cycle callbacks:
//  - [Chinese] http://www.cocos.com/docs/creator/scripting/life-cycle-callbacks.html
//  - [English] http://www.cocos2d-x.org/docs/editors_and_tools/creator-chapters/scripting/life-cycle-callbacks/index.html

cc.Class({
    extends: cc.Component,

    properties: {
    },
    onLoad: function() {
    	cc.socket = new WebSocket('ws://127.0.0.1:8060/websocket/majiang/gcwefdghf');
    	
    	cc.socket.onopen = function(event) {
    		cc.director.preloadScene("majiang", function () {
                cc.director.loadScene("majiang");
            });
    	}
    	
    	cc.socket.onmessage = function(event) {
    		console.log(111);
    	}
    	
    	console.log(this.target.getComponent("MajiangLib"));
    }
});
