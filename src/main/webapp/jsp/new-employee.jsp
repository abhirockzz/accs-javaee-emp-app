<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">   		

    </head>
    <body>
        <div class="container">
            <form action="${pageContext.request.contextPath}/employee" method="post"  role="form" data-toggle="validator" class="form-horizontal" >
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="idEmployee" name="idEmployee" value="${employee.id}">
                <h2>Employee</h2>
                <div class="form-group ">
                    <label for="name" class="control-label col-sm-2">Name:</label>
                    <div class='col-sm-2'>
                        <input type="text" name="name" id="name" class="form-control" value="${employee.name}" required="true"/>    
                    </div>
                </div>
                <div class="form-group ">
                    <label for="lastName" class="control-label col-sm-2">Last name:</label>    
                    <div class='col-sm-2'>
                        <input type="text" name="lastName" id="lastName" class="form-control" value="${employee.lastName}" required="true"/> 
                    </div>
                </div>
                <div class="form-group ">
                    <label for="birthdate" class="control-label col-sm-2">Birth date</label>  
                    <div class='col-sm-2'>
                        <input type="text"  pattern="^\d{2}-\d{2}-\d{4}$" name="birthDate" id="birthdate" class="form-control" value="${employee.birthDate}" maxlength="10" placeholder="dd-MM-yyy" required="true"/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="role" class="control-label col-sm-2">Role:</label>                    
                    <div class='col-sm-2'>
                        <input type="text" name="role" id="role" class="form-control" value="${employee.role}" required="true"/> 
                    </div>
                </div>
                <div class="form-group ">
                    <label for="department" class="control-label col-sm-2">Department:</label>
                    <div class='col-sm-2'>
                    <input type="text" name="department" id="department" class="form-control" value="${employee.department}" required="true"/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="department" class="control-label col-sm-2">E-mail:</label>                   
                    <div class='col-sm-2'>
                    <input type="text" name="email" id="email" class="form-control" value="${employee.email}" placeholder="smith@aol.com" required="true"/>
                    </div>
                </div>
                <br></br>
                <button type="button" class="btn btn-default  btn-md" onclick="location.href = '${pageContext.request.contextPath}/employee';">Cancel</button>                    
                <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>                                                      
    </form>

</div>
</body>
</html>