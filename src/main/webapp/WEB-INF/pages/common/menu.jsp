<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form method="POST" commandName="provinceFormBean">
	<form:label path="province">Seleccione una provincia: </form:label>
 	<form:select path="province"  items="${provinces}" itemValue="provinceId" itemLabel="name" onchange="javascritp:loadKML();"/>
</form:form>

<div id="dialog" title="Datos no accesibles">
  <p>
    <span class="ui-icon ui-icon-circle-check" style="float: left; margin: 0 7px 50px 0;"></span>
    No se encuentran datos para la provincia seleccionada.
  </p>

</div>


<!-- a href="volcar.do">Volcar Datos</a -->