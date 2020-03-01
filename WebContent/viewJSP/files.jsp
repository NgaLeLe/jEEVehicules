<%@ page import="java.util.List,fr.test.java.modele.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tree File </title>
         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
        <link rel="stylesheet" href="resource/css/main.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
        <script type="text/javascript">
                $(function () { $('#jstree_div').jstree(); });
        </script>
    </head>
    <body>
      <div id=jstree_div>
          <ul>
         <li data-jstree='{"opened":false,"selected":true}'>Upload files
           <c:forEach items="${requestScope.files}" var="file">
           	<ul><c:if test="${file.isDossier()=='true'}">
           			<li>
           				<span class="caret"> ${file.getFileName()}</span>
           			    <ul class = "nested">
               				<c:forEach items="${requestScope.files}" var="child">
                				<c:if test="${child.getNameParent() == file.getFileName()}">
                					<li>${child.getFileName()} OK
                					</li>
                				</c:if>
                			</c:forEach>
              			</ul>
               			<c:if test="${file.isDossier()=='false'}">${file.fileName}
               			</c:if>
               		</li>
              	</c:if>
             </ul>
           </c:forEach>
          </li>
        </ul>
    </div>
 
  </body>
</html>