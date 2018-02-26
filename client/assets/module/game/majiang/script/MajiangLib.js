var Common = require("Common");

cc.Class({
    extends: Common,

    properties: {
    	timer: {
    		default: null,
    		type: cc.Label
    	},
    	cards_number: {
    		default: null,
    		type: cc.Label
    	},
    	highlight: {
    		default: null,
    		type: cc.Node,
    	},
		player_layout_t: {
	    	default:null ,
	        type : cc.Node
		},
		player_layout_r: {
	    	default:null ,
	        type : cc.Node
		},
		player_layout_b: {
	    	default:null ,
	        type : cc.Node
		},
		player_layout_l: {
	    	default:null ,
	        type : cc.Node
		},
		ready_prefab: {
			default: null,
			type: cc.Prefab
		},
		invite_prefab: {
			default: null,
			type: cc.Prefab
		},
    	player_prefab: {
    		default: null,
    		type: cc.Prefab
    	},
    	handcards_layout_t: {
	    	default:null ,
	        type : cc.Node
    	},
    	handcards_layout_r: {
	    	default:null ,
	        type : cc.Node
    	},
    	handcards_layout_b: {
	    	default:null ,
	        type : cc.Node
    	},
    	handcards_layout_l: {
	    	default:null ,
	        type : cc.Node
    	},
    	deskcards_layout_t: {
	    	default:null ,
	        type : cc.Node
    	},
    	deskcards_layout_r: {
	    	default:null ,
	        type : cc.Node
    	},
    	deskcards_layout_b: {
	    	default:null ,
	        type : cc.Node
    	},
    	deskcards_layout_l: {
	    	default:null ,
	        type : cc.Node
    	},
    	pengcards_layout_t: {
	    	default:null ,
	        type : cc.Node
    	},
    	pengcards_layout_r: {
	    	default:null ,
	        type : cc.Node
    	},
    	pengcards_layout_b: {
	    	default:null ,
	        type : cc.Node
    	},
    	pengcards_layout_l: {
	    	default:null ,
	        type : cc.Node
    	},
    	handcards_prefab_t: {
			default: null,
			type: cc.Prefab,
    	},
    	handcards_prefab_r: {
			default: null,
			type: cc.Prefab,
    	},
    	handcards_prefab_b: {
			default: null,
			type: cc.Prefab,
    	},
    	handcards_prefab_l: {
			default: null,
			type: cc.Prefab,
    	},
    	deskcards_prefab_t: {
			default: null,
			type: cc.Prefab,
    	},
    	deskcards_prefab_r: {
			default: null,
			type: cc.Prefab,
    	},
    	deskcards_prefab_b: {
			default: null,
			type: cc.Prefab,
    	},
    	deskcards_prefab_l: {
			default: null,
			type: cc.Prefab,
    	},
    	peng_prefab_t: {
			default: null,
			type: cc.Prefab,
    	},
    	peng_prefab_r: {
			default: null,
			type: cc.Prefab,
    	},
    	peng_prefab_b: {
			default: null,
			type: cc.Prefab,
    	},
    	peng_prefab_l: {
			default: null,
			type: cc.Prefab,
    	},
    	mgang_prefab_t: {
			default: null,
			type: cc.Prefab,
    	},
    	mgang_prefab_r: {
			default: null,
			type: cc.Prefab,
    	},
    	mgang_prefab_b: {
			default: null,
			type: cc.Prefab,
    	},
    	mgang_prefab_l: {
			default: null,
			type: cc.Prefab,
    	},
    	agang_prefab_t: {
    		default: null,
    		type: cc.Prefab,
    	},
    	agang_prefab_r: {
			default: null,
			type: cc.Prefab,
		},
		agang_prefab_b: {
			default: null,
			type: cc.Prefab,
		},
		agang_prefab_l: {
			default: null,
			type: cc.Prefab,
		},
    },

    onLoad: function() {
		
		this.highlight.active = false;
		
		this.room = {
				players: {},
				player_1: null,
				player_2: null,
				player_3: null,
				player_4: null
		};
		
		
		
//		this.player_layout_t.addChild(cc.instantiate(this.ready_prefab));
//		
//		this.player_layout_r.addChild(cc.instantiate(this.ready_prefab));
//		
//		this.player_layout_b.addChild(cc.instantiate(this.ready_prefab));
//		
//		this.player_layout_l.addChild(cc.instantiate(this.ready_prefab));
		
    	if(!this.socket) {
    		this.connection();
    	}
		
    	
    },
    
    connection: function() {
    	
    	let self = this
    	this.socket = new WebSocket('ws://127.0.0.1:8060/websocket/majiang/gcwefdghf');
		
		this.socket.onopen = function(event) {
			
		}
		
		this.socket.onmessage = function(event) {
			var data = event.data;
			data = self.str2json(data);

			if(data && data.action && self[data.action] && typeof self[data.action] == "function") {
				self[data.action].call(self, data);
			}
		}
		
		this.socket.onclose = function(event) {
			
		}
		
		this.socket.onerror = function(event) {
			
		}
    },
    
    playerEnter: function(msg) {
    	
    	for(var i = 0; i < msg.players.length; i++) {
    		this.room.players[msg.players[i].userId] = msg.players[i];
    		if(msg.players[i].position > 0) {
    			
    		}
    	}
    }
});
