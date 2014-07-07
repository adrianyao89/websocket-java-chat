var $WS = {repos:{}};

$WS.connect = function(options) {
	if (WebSocket) {
		if (options.onopen) {
			$("body").bind(options.key +'_onopen', options.onopen);
		}

		if (options.onmessage) {
			$("body").bind(options.key +'_onmessage', function(e, protocol) {
				var path = protocol.module.name + '_' + protocol.module.action;
				if(options.onmessage[path]) {
					options.onmessage[path](e, protocol);
				}
			});
		}
		
		if (options.onclose) {
			$("body").bind(options.key +'_onclose', options.onclose);
		}

		if (options.onerror) {
			$("body").bind(options.key +'_onerror', options.onerror);
		}

		var socket = new WebSocket(options.url);

		socket.onopen = function(event) {
			$("body").trigger(options.key +'_onopen', event);
		};

		socket.onmessage = function(event) {
			var protocol = $.parseJSON(event.data);
			if (protocol.header.type === "response") {
				$("body").trigger("response_" + protocol.header.uid, protocol);
			} else {
				$("body").trigger(options.key +'_onmessage', protocol);
			}
		};
	
		socket.onclose = function(event) {
			$("body").trigger(options.key +'_onclose', event);
			delete $WS.repos[options.key];
		};

		socket.onerror = function(event) {
			$("body").trigger(options.key +'_onerror', event);
		};

		if (options.key) {
			$WS.repos[options.key] = {
				socket: socket,
				send : function send(message, responseHandler) {
					var protocol = $.parseJSON(message);
					if (responseHandler) {
						$("body").bind("response_" + protocol.header.uid, responseHandler);
					}
					socket.send(message);
				},
				ack :function ack(uid) {
					$("body").unbind("response_" + uid);
				}
			};
		}
		

		return socket;
	} else {
		return null;
	}
	
}