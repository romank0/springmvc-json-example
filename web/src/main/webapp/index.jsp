<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple IRC</title>
<script src="jquery.js"></script>
<script type="text/javascript">


	$(document).ready(function() {
	   
		$.ajax({
			url: "message/all",
		    success: function(response){
		    	$('#messages').html(response);
			    },  
			error: function(e){  
			      alert('Error: ' + e);  
			    }  
			});
	
	 });

	function sendMessage() {  
	  var message = $('#message').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: "/message",  
	    data: "name=" + name + "&education=" + education,  
	    success: function(response){
	      $('#info').html(response);
	      $('#name').val('');
	      $('#education').val('');
	    },  
	    error: function(e){  
	      alert('Error: ' + e);  
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
	<div id="messages"></div>
</body>
</html>
