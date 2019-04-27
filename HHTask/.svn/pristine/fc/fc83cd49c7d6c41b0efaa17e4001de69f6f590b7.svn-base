(function($) {
	$.keyboard.keyboards.qwerty = [
			[ '@', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', {
				text : 'BackSpace',
				name : 'backSpace',
				action : function(e) {
					return false;
				}
			} ], [ {
				text : 'Tab⇥',
				cap : {
					text : 'Tab⇤'
				},
				name : 'tab',
				action : false
			}, 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', {
				text : '⏎',
				name : 'return',
				action : false
			} ], [ {
				text : 'CapsLock',
				name : 'capsLock',
				action : function(e) {
					e.keyboard('capToggle', true);
					return false;
				}
			}, 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', {
				text : ',',
				alt : [ ':', ';' ]
			} ], [ {
				text : 'Shift⇧',
				name : 'leftShift',
				action : function(e) {
					e.keyboard('capToggle');
					return false;
				}
			}, 'z', 'x', 'c', 'v', 'b', 'n', 'm', {
				text : '.',
				alt : [ '?', '!', ';' ]
			}, '\'', {
				text : ':)',
				alt : [ ';)', 'X)', '8)', ':(' ]
			}, {
				text : 'Shift⇧',
				name : 'rightShift',
				action : function(e) {
					e.keyboard('capToggle');
					return false;
				}
			} ], [ {
				text : 'Ctrl',
				cap : {
					text : 'Ctrl'
				},
				name : 'ctrl',
				action : function(e) {
					e.keyboard(1);
					return false;
				}
			}, {
				text : 'Alt',
				cap : {
					text : 'Alt'
				},
				name : 'alt',
				action : false
			}, {
				text : ' ',
				name : 'space'
			}, {
				text : '←',
				action : false,
				name : 'leftArrow'
			}, {
				text : '→',
				action : false,
				name : 'rightArrow'
			} ] ];
})(jQuery);