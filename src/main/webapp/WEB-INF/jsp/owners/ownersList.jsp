<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>



<petclinic:layout pageName="owners">
    <div class="es"><h2>Due�os</h2></div>
    <div class="en"><h2>Owners</h2></div>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;"><div class="es">Nombre</div><div class="en">Name</div></th>
            <th style="width: 200px;"><div class="es">Direcci�n</div><div class="en">Address</div></th>
            <th><div class="es">Ciudad</div><div class="en">City</div></th>
            <th style="width: 120px"><div class="es">Tel�fono</div><div class="en">Phone Address</div></th>
            <th><div class="es">Mascotas</div><div class="en">Pets</div></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="owner">
            <tr>
                <td>
                    <spring:url value="/owners/{ownerId}" var="ownerUrl">
                        <spring:param name="ownerId" value="${owner.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${owner.firstName} ${owner.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${owner.address}"/>
                </td>
                <td>
                    <c:out value="${owner.city}"/>
                </td>
                <td>
                    <c:out value="${owner.telephone}"/>
                </td>
                <td>
                    <c:forEach var="pet" items="${owner.pets}">
                        <c:out value="${pet.name} "/>
                    </c:forEach>
                </td>
                <td class="text-left">
                	<a href="/owners/${owner.id}/delete">
                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                	</a>
                </td>  
                
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
