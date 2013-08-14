<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertAttribute name="head" />
<body class="index"> 
	<div class="general">
	    <div id="title">
	    	<tiles:insertAttribute name="header" />
	    </div>
	    <div id="tabs">
	    	<tiles:insertAttribute name="tabs" />
	   	</div>
	    <div id="content">
	    	<tiles:insertAttribute name="body" />
	    </div>
    </div>
</body> 
</html>