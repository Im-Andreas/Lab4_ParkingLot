<%@ tag description="base page template" pageEncoding="UTF-8" %>
<%@ attribute name="pageTitle" %>

<!DOCTYPE html>
<html>
    <head>
        <title>${pageTitle}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/pages/menu.jsp" />
        <main class="container-fluid mt-5 mx-0 px-0">
            <jsp:doBody/>
        </main>
        <jsp:include page="/WEB-INF/pages/footer.jsp" />
        <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
    </body>
</html>