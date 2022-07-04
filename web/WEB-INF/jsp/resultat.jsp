<%-- 
    Document   : resultat
    Created on : 4 mars 2018, 20:29:48
    Author     : faycal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultat</title>
    </head>
    <body>
        
        ${user.login}
        <table border="1" cellpadding="10">
           
           
              <TR>
                  <TD>Product ID</TD>
                  <TD>Manufacturer ID</TD>
                  <TD>Product Code</TD>
                  <TD>Purshase Cost</TD>
                  <TD>Quantity</TD>
                  <TD>Markup</TD>
                  <TD>Available</TD>
                  <TD>Description</TD>
                </TR>
            
                <c:forEach items="${liste}" var="achats" varStatus="row" >
                <TR>
                  <c:forEach items="${achats}" var="achat" >  
                    <TD>${achat}
                    </TD>
                  </c:forEach>
                </TR>
                </c:forEach>
                <%--   <c:forEach items="${liste}" var="client">
                <TR>
                    <TD>${client.customerId}</TD>
                    <TD>${client.discountCode}</TD>
                    <TD>${client.zip}</TD>
                    <TD>${client.name}</TD>
                </TR>
                        </c:forEach>        --%>
                 
        </table>
        
    </body>
</html>
