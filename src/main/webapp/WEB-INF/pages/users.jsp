<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:pageTemplate pageTitle="👤 Users 👤">
    <h1>👤 USERS 👤</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Users">
        <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddUser">Add User</a>
            <button class="btn btn-danger" type="submit">Invoice</button>
        </c:if>
        <div class="container text-center">
            <c:forEach var="user" items="${users}">
                <div class="row">
                    <div class="col">
                        <input type="checkbox" name="user_ids" value="${user.id}"/>
                    </div>
                    <div class="col">
                            ${user.username}
                    </div>
                    <div class="col">
                            ${user.email}
                    </div>
                </div>
            </c:forEach>
        </div>
        <h5>Number of users: ${fn:length(users)}</h5>
    </form>
    <c:if test="${not empty invoices}">
        <h2>🔊INVOICES🔊</h2>
        <c:forEach var="username" items="${invoices}" varStatus="status">
            ${status.index + 1}. ${username}
            <br>
        </c:forEach>
    </c:if>
</t:pageTemplate>
