<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 10/4/2014
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="storage.VariablesStorage"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:useBean id="storage" class="storage.VariablesStorage">

</jsp:useBean>
<%
    if (request.getParameter("inText") != null && request.getParameter("inText").length() > 0 && "Add".equals(request.getParameter("mainButton"))) {
        VariablesStorage.list.add(request.getParameter("inText"));
    }
%>
<c:if test="param['inText'] != null && fn:length(param['inText']) > 0 && 'Add' eq param['mainButton']">

</c:if>
<%
    if ("Edit".equals(request.getParameter("mainButton"))) {
        VariablesStorage.list.set(VariablesStorage.getElemToEdit(), request.getParameter("inText"));
        VariablesStorage.setText("");
        VariablesStorage.setButtonName("Add");
    }
%>
<%
    if (request.getParameter("delete") != null && request.getParameter("curElem") != null) {
        VariablesStorage.list.remove(Integer.parseInt(request.getParameter("curElem")));
    }
%>
<%
    if (request.getParameter("edit") != null && request.getParameter("curElem") != null) {
        VariablesStorage.setElemToEdit(Integer.parseInt(request.getParameter("curElem")));
        VariablesStorage.setText(VariablesStorage.list.get(VariablesStorage.getElemToEdit()));
        VariablesStorage.setButtonName("Edit");
    }
%>


<html>
<head>
    <title></title>
</head>
<body>
    <c:set var="storedText" value=""/>
    <c:set var="buttonName" value="Add"/>
    <c:set var="curElemIndex" value=""/>
    <c:set var="list" value=""/>

    <form method="get">
        <input type="text" name="inText" value="${storedText}"/>
        <input type="submit" name="mainButton" value=" ${buttonName}"/>
    </form>
    <table>
        <c:forEach var="item" items="${pageScope.list}" varStatus="loopCounter">
            <tr>
                <td>
                    <c:out value="${item}"/>
                </td>
                <td>
                    <form method="get">
                        <input type="submit" name="delete" value="Delete"/>
                        <input type="submit" name="edit" value="Edit"/>
                        <input type="hidden" value="${loopCounter.count}" name="curElem"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
