<%--
  Created by  olomakovskyi on 9/30/2014
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jsp.ListStorage" %>

<%
    if (request.getParameter("inText") != null && request.getParameter("inText").length() > 0){
        ListStorage.list.add(request.getParameter("inText"));
    }
    if (request.getParameter("delete") != null && request.getParameter("curElem") != null){
        ListStorage.list.remove(Integer.parseInt(request.getParameter("curElem")));
    }
    if (request.getParameter("edit") != null && request.getParameter("curElem") != null){
        ListStorage.setText(ListStorage.list.get(Integer.parseInt(request.getParameter("curElem"))));
    }

%>


<html>
<head>
    <title></title>
</head>
<body>
    <form method="get">
        <input type="text" name="inText" value="Test +  <% String.format("\"%s\"", ListStorage.getText()); %>">
        <input type="submit" value="Add"/>

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
                <form method="get">
                    <input type="submit" name="delete" value="Delete"/>
                    <input type="submit" name="edit" value="Edit"/>
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
