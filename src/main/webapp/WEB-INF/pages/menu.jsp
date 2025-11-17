<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header data-bs-theme="dark">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Parking Lot</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.requestURI.substring(pageContext.request
                        .requestURI.lastIndexOf("/")) eq '/about.jsp' ? ' active' : ''}"
                           aria-current="page" href="${pageContext.request.contextPath}/about.jsp">
                            About
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${requestScope.activePage eq 'Cars' ? 'active' : ''}"
                           aria-current="page" href="${pageContext.request.contextPath}/Cars">
                            Cars
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${requestScope.activePage eq 'Users' ? 'active' : ''}"
                           aria-current="page" href="${pageContext.request.contextPath}/Users">
                            Users
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                    </li>
                </ul>

                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>