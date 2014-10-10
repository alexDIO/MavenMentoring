<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 10/4/2014
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="/WEB-INF/listmanager.tld"%>

<% pageContext.setAttribute("list", request.getAttribute("list")); %>
<c:set var="buttonName" value="Add"/>

<c:if test="${param['inText'] != null && fn:length(param['inText']) > 0 && 'Add' == param['mainButton']}">
    <my:managelist storage="${list}" valueToAdd="${param['inText']}"/>
</c:if>

<c:if test="${'Edit' == param['mainButton']}">
    <my:managelist storage="${list}" indexToSet="${param['elemToEdit']}" newValueToSet="${param['inText']}"/>
    <c:set var="storedText" value=""/>
    <c:set var="buttonName" value="Add"/>
</c:if>

<c:if test="${param['delete'] != null && param['curElem'] != null}">
    <my:managelist storage="${list}" indexToRemove="${param['curElem']}"/>
</c:if>

<c:if test="${param['edit'] != null && param['curElem'] != null}">
    <fmt:parseNumber var="index" value="${param['curElem']}"/>
    <c:set var="storedText" value="${list[index]}"/>
    <c:set var="buttonName" value="Edit"/>
</c:if>


<html>
<head>
    <title></title>
</head>
<body>

    <form method="get">
        <input type="text" name="inText" value="${storedText}"/>
        <input type="submit" name="mainButton" value="${buttonName}"/>
        <input type="hidden" value="${param['curElem']}" name="elemToEdit"/>
    </form>
    <table>
        <c:forEach var="item" items="${list}" varStatus="loopCounter">
            <tr>
                <td>
                    <c:out value="${item}"/>
                </td>
                <td>
                    <form method="get">
                        <input type="submit" name="delete" value="Delete"/>
                        <input type="submit" name="edit" value="Edit"/>
                        <input type="hidden" value="${loopCounter.index}" name="curElem"/>
                    </form>
                </td>
                <td>
                    <span>
                        <c:out value="${fn:length(fn:split(item,' ' ))}"/>
                    </span>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
