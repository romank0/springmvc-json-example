<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple IRC</title>

<link rel="stylesheet" type="text/css" href="style.css" media="screen" />

<script src="jquery.js"></script>
<script type="text/javascript">

	var error = function(message) {
		$('#error').html(message);
	}

	var refreshMessages = function(repeat) {
		$.ajax({
			url: "/api/message",
		    success: function(response){
		    	var messages_html = "";
		    	var i=0;
		    	for (i=0;i<response.length;i++)
		    	{
		    		messages_html += "<div class='message'>"
    					+ "<span class='text'>"
    					+ response[i].text
    					+ "</span>"
    					+ "</div>";
		    	}
		    	$('#messages').html(messages_html);
		    	if (repeat) {
					setTimeout(function() { refreshMessages(true);}, 2000);
				}
			},  
			error: function(e){  
			      error('Error: ' + e);  
			    }  
			});
	 };
	 
	$(document).ready(function() {
		refreshMessages(true);
	});
	
	function sendMessage() {  
	  var message = $('#message').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: "/api/message/" + message,   
	    success: function() {refreshMessages(false);},  
	    error: function(e){  
	      error('Error: ' + e);  
	    }  
	  });  
	}  
</script>
</head>
<body>
<h1>Simple IRC welcomes you</h1>
	<table>
		<tr><td>Message: </td><td> <input type="text" id="message"><br/></td></tr>
	
		<tr><td colspan="2"><input type="button" value="Send" onclick="sendMessage()"><br/></td></tr>
		<tr><td colspan="2"><div id="info" style="color: green;"></div></td></tr>
	</table>
	Recent messages:
	<div id="messages"></div>
	<div id="error"></div>
</body>
</html>
