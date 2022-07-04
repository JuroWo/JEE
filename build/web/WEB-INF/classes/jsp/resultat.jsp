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
        
                
        <table border="1" cellpadding="10">
           
           
              <TR>
                    <c:forEach items="${colonnes}" var="col">
                    <TD>${col}</TD> 
                    </c:forEach>
                </TR>
            
                <c:forEach items="${liste}" var="client" varStatus="row" >
                <TR>
                  <c:forEach items="${client}" var="cli" >  
                    <TD>${cli}
                    </TD>
                  </c:forEach>
                    <TD>
                        <form name="Result" action="detail.htm"  method="POST">
                        <input type=hidden name="num"  value="${liste[row.index][0]}"/>
                        <input type=submit  value="Détail"  />
                        </form>

                    </TD>
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
