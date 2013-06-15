<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form method="POST" commandName="provinceFormBean">
 	<form:select path="province"  items="${provinces}" itemValue="provinceId" itemLabel="name"/>
</form:form>

<a href="volcar.do">Volcar Datos</a>