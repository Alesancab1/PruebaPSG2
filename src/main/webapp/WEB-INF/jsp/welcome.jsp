<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
	<div class="es"><h2>Bienvenido a PSG2-2021-G5-54 Petclinic</h2></div>
    <div class="en"><h2>Welcome to the PSG2-2021-G5-54 Petclinic</h2></div>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/imagenBienvenida.jpg" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
        </div>
    </div>
</petclinic:layout>
