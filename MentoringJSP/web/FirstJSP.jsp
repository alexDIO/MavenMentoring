<%--
  Created by IntelliJ IDEA.
  User: olomakovskyi
  Date: 9/30/2014
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jsp.ListStorage" %>

<%
    if (request.getParameter("inText") != null && request.getParameter("inText").length() > 0){
        ListStorage.list.add(request.getParameter("inText"));
    }
    if (request.getParameter("curElem") !=null){
        ListStorage.list.remove(Integer.parseInt(request.getParameter("curElem")));
    }
%>

<html>
<head>
    <title></title>
</head>
<body>
    <form>
        <input type="text" name="inText"/>
        <input type="submit" value="Add"/>
        <input type="button" value="Click me" />

    </form>
    <table>
        <%
            for (int i = 0; i < ListStorage.list.size(); i++){
        %>
        <tr>
            <td>
                <span><%=ListStorage.list.get(i)%></span>
            </td>
            <td>
                <form>
                    <input type="submit" value="Delete"/>
                    <input type="button" value="Edit"/>
                    <input type="hidden" value="<%=i%>" name="curElem"/>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
