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
<%@ taglib prefix="tm" uri="/WEB-INF/transportManager.tld"%>

<% pageContext.setAttribute("holder", request.getAttribute("holder")); %>
<c:set var="buttonName" value="Add"/>



<%--<c:if test="${param['inText'] != null && fn:length(param['inText']) > 0 && 'Add' == param['mainButton']}">--%>
    <%--<ml:manageList storage="${list}" valueToAdd="${param['inText']}"/>--%>
<%--</c:if>--%>

<%--<c:if test="${'Update' == param['mainButton']}">--%>
    <%--<ml:manageList storage="${list}" indexToSet="${param['elemToEdit']}" newValueToSet="${param['inText']}"/>--%>
    <%--<c:set var="storedText" value=""/>--%>
    <%--<c:set var="buttonName" value="Add"/>--%>
<%--</c:if>--%>

<%--<c:if test="${param['delete'] != null && param['curElem'] != null}">--%>
    <%--<ml:manageList storage="${list}" indexToRemove="${param['curElem']}"/>--%>
<%--</c:if>--%>

<%--<c:if test="${param['edit'] != null && param['curElem'] != null}">--%>
    <%--<fmt:parseNumber var="index" value="${param['curElem']}"/>--%>
    <%--<c:set var="storedText" value="${list[index]}"/>--%>
    <%--<c:set var="buttonName" value="Update"/>--%>
<%--</c:if>--%>

<c:if test="${param['delete'] != null && param['curElemID'] != null}">
    <fmt:parseNumber value="${param['curElemID']}" var="intElemID"/>
    <tm:manageTransportMap holder="${holder}" action="delete" idd="${intElemID}"/>
    <c:out value="curelemID ${param['curElemID']}"/>
    <c:out value="intelemID ${intElemID}"/>
</c:if>

<c:if test="${'Add' == param['mainButton'] && param['mark'] != null}">
    <tm:manageTransportMap holder="${holder}" action="add" transportType="${param['transportType']}" mark="${param['mark']}" color="${param['color']}" manufactureYear="${param['manufactureYear']}"
            passengersCount="${param['passengersCount']}" energySource="${param['energySource']}" transmission="${param['transmission']}" load="${param['load']}"/>
</c:if>


<html>
<head>
    <title></title>
    <script type="text/javascript">
        function showHide(){
            var selectedValue = document.getElementById("transport").value;
            switch (selectedValue){
                case "coupe":
                    document.getElementById("count").style.visibility = "hidden";
                    break;
                default:
                    document.getElementById("count").style.visibility = "visible";
            }
        }
    </script>
</head>
<body>

    <form method="get">
        <table>
            <tr>
                <td>
                    <span>Transport Type</span>
                </td>
                <td>
                    <select name="transportType" id="transport" onchange="showHide()">
                        <option value="coupe">coupe</option>
                        <option value="sedan">sedan</option>
                        <option value="limousine">limousine</option>
                        <option value="truck">truck</option>
                        <option value="bus">bus</option>
                        <option value="trolleybus">trolleybus</option>
                        <option value="tram">tram</option>
                    </select>
                </td>
                <td>
                    <span>Mark</span>
                </td>
                <td>
                    <input type="text" name="mark">
                </td>
                <td>
                    <span>Color</span>
                </td>
                <td>
                    <select name="color">
                        <option value="black">black</option>
                        <option value="white">white</option>
                        <option value="grey">grey</option>
                        <option value="red">red</option>
                        <option value="orange">orange</option>
                        <option value="yellow">yellow</option>
                        <option value="blue">blue</option>
                        <option value="brown">brown</option>
                    </select>
                </td>
                <td>
                    <span>Manufacture year</span>
                </td>
                <td>
                    <input type="text" name="manufactureYear">
                </td>
            </tr>
            <tr>
                <td>
                    <span>Passengers count</span>
                </td>
                <td>
                    <input type="text" name="passengersCount" id="count">
                </td>
                <td>
                    <span>Energy source</span>
                </td>
                <td>
                    <select name="energySource">
                        <option value="petrol">petrol</option>
                        <option value="gas">gas</option>
                        <option value="electricity">electricity</option>
                    </select>
                </td>
                <td>
                    <span>Transmission</span>
                </td>
                <td>
                    <select name="transmission">
                        <option value="manual">manual</option>
                        <option value="automate">automate</option>
                    </select>
                </td>
                <td>
                    <span>Load</span>
                </td>
                <td>
                    <input type="text" name="load">
                </td>
                <td>
                    <input type="hidden" name="id">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="mainButton" value="${buttonName}">
                </td>
            </tr>
        </table>
    </form>
    <table>
        <c:forEach var="item" items="${holder.map}" varStatus="loopCounter">
            <tr>
                <td>
                    <c:out value="${item.value}"/>
                </td>
                <td>
                    <form method="get">
                        <input type="submit" name="delete" value="Delete"/>
                        <input type="submit" name="edit" value="Edit"/>
                        <input type="hidden" value="${item.key}" name="curElemID"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
