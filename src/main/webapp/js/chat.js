function showMessage(id, message) {
	$("#" + id + " p").html(message);
	$("#" + id).removeClass("hidden");
	setTimeout(function() {
		$("#" + id).addClass("hidden");
	}, 2000);
}

$(document).ready(function(){
	var socketKey = "message";

	$WS.connect({
		key: socketKey,
		url: "ws://192.157.229.101:8080/webchat/cbus",
		onopen: function(event) {
			
		},
		onmessage: {
			chat_group: function(event, protocol) {
				var message = protocol.module.data;
				var pup = '<div class="chat-record chat-record-theme clearfix"><img src="http://adrianyao.qiniudn.com/default.jpg" alt="..." class="chat-record-header"><p class="chat-record-title chat-record-title-theme">'+message.name+'</p><p class="alert alert-info chat-record-content chat-record-content-theme">'+message.message+'</p></div>';
				$("#chat-content-view").append(pup);
				$("#chat-content-view").scrollTop($("#chat-content-view")[0].scrollHeight);
			}
		},
		onclose: function(event) {
			alert("连接中断, 请尝试刷新页面解决");
		},
		onerror: function(event) {
			console.log("Error: " + event.data);
		}
	});

	$("#chat-send").bind('click', function() {
		var message = $("#chat-message").val();
		if (message && message.length > 0) {
			if($WS.repos.message) {
				$WS.repos.message.send(message);
			}
		}
		var message = $("#chat-message").val('');
	});

	function sendMessage() {
		var user = $.parseJSON(localStorage.user);
		var message = $("#chat-send-box").val();
		var protocol = {
			header: {
				type: "request",
				token: user.token.token
			},
			"module": {
				name: "chat",
				action: "group",
				data: {
					groupId: 1,
					userId: user.id,
					name: user.name,
					message: message
				}
			}
		};
		$WS.repos[socketKey].send(JSON.stringify(protocol));

		var pup = '<div class="chat-my-record chat-my-record-theme clearfix"><img src="http://adrianyao.qiniudn.com/default.jpg" alt="..." class="chat-my-record-header"><p class="alert alert-info chat-my-record-content chat-my-record-content-theme">'+message+'</p></div>'
		$("#chat-send-box").val("");
		$("#chat-content-view").append(pup);
		$("#chat-content-view").scrollTop($("#chat-content-view")[0].scrollHeight);
	}

    

    $("#login-button").bind('click', function() {
    	var username = $("#input-username").val();
    	var password = $("#input-password").val();
    	var body = '{"header":{"type": "request", "uid": "'+ new Date().getTime() +'"},  "module":{"name":"oauth", "action":"login", "data": {"username":"'+username+'", "password":"'+password+'"}}}';
    	$WS.repos[socketKey].send(body, function(e, protocol) {
    	 	if (protocol.module.response.state.code === 200) {
    			if (localStorage) {
    				localStorage.user = JSON.stringify(protocol.module.response.data);
    			}
    			$WS.repos[socketKey].ack(protocol.header.uid);

	    		$("#loginModal .close").click();

	    		$("#login-user").html("你好" + protocol.module.response.data.name);
	    		$("#login-bar").addClass("hidden");
	    		$("#login-user").removeClass("hidden");
	    		$("#chat-win").removeClass("hidden");
    		} else {
    			showMessage("login-message", "用户名或密码错误");
    		}
    		
    	});
    });


    $("#chat-send-button").bind('click', sendMessage);

    $("#chat-send-box").keydown(function(e) {
            if (e.keyCode == 13 && e.ctrlKey) {  
                sendMessage();
            } 
    });
});