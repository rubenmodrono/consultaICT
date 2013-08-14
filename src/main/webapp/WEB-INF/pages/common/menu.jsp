<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form commandName="provinceFormBean" action="javascript:loadKML();">
	<form:label path="province">Seleccione una provincia: </form:label>
 	<form:select id="combobox" path="province"  items="${provinces}" itemValue="provinceId" itemLabel="name" onchange="javascritp:loadKML();"/>
</form:form>

