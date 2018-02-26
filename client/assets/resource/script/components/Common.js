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
        // foo: {
        //     // ATTRIBUTES:
        //     default: null,        // The default value will be used only when the component attaching
        //                           // to a node for the first time
        //     type: cc.SpriteFrame, // optional, default is typeof default
        //     serializable: true,   // optional, default is true
        // },
        // bar: {
        //     get () {
        //         return this._bar;
        //     },
        //     set (value) {
        //         this._bar = value;
        //     }
        // },
    }, 
    
    onLoad: function() {
    	cc.sdearn = {};
    	cc.sdearn.socket = new WebSocket('ws://127.0.0.1:8060/websocket/majiang/room');
    	cc.sdearn.socket.onmessage = function (event) {
    		console.log(event);
    	}
    },

    getUrlParma: function(name) {
    	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
        	return unescape(r[2]);
        }else {
        	return null;
        }
    },
    
    str2json: function(str) {
    	var obj=false;
    	try  {
    		obj=eval(str);
    	}
    	catch(e) {
    		try {
    			obj=str.parseJSON();
    		} 
    		catch(e) {
    			try {
    				obj=JSON.parse(str);
    			}
    			catch(e) {
    				console.log(e);
    			}
    		}
    	}
    	return obj;
    }

});
