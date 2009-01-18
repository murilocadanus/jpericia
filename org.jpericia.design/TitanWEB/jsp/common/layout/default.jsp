<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css" type="text/css"></link>
<script language="JavaScript" type="text/javascript">ulm_save_doc=true</script>
<script language="JavaScript" type="text/javascript"src="<%=request.getContextPath()%>/js/common.js"></script>
<script language="JavaScript" type="text/javascript"src="<%=request.getContextPath()%>/js/displaytag.css"></script>
<style type="text/css">

</style>
</head>
<body>
<table width="800" class="main">
	<tr>
		<td align="center" colspan="2" class="tituloAplic"><tiles:getAsString name="title" /></td>
	</tr>
	<tr>
		<td align="left" width="100" valign="top"><tiles:insert name="menu" /></td>
		<td align="left"><tiles:insert name="body" /></td>
	</tr>
</table>
</body>
</html>
