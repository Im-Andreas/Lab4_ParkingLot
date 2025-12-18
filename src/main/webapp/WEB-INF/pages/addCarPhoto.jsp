<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add a Car Photo">
    <h1>Add a Car Photo 'ere, if ya want</h1>
    <form class="needs-validation" novalidate method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/AddCarPhoto">
        <div class="row">
            <div class="col-mb-3">
                License plate: ${car.licensePlate}
            </div>
        </div>
        <div class="row">
            <div class="col-mb-3">
                <label for="file" class="form-label">Photo</label>
                <input type="file" class="form-control" id="file" name="file" required>
                <div class="invalid-feedback">
                    Photo is required.
                </div>
            </div>
        </div>
        <input type="hidden" name="car_id" value="${car.id}"/>
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
    </form>
</t:pageTemplate>
