<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="🚙💨 Edit a CAR 🚙💨">
    <h1>Edit a CAR here, if ya wish</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCar">
        <div class="row">
            <div class="mb-3">
                <label for="license_plate" class="form-label">License Plate</label>
                <input type="text" class="form-control" id="license_plate" name="license_plate" placeholder="" value="${car.licensePlate}" required aria-describedby="license_plate">
                <div class="invalid-feedback">
                    Please enter a valid license plate.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="mb-3">
                <label for="parking_spot" class="form-label">Parking Spot</label>
                <input type="text" class="form-control" id="parking_spot" name="parking_spot" placeholder="" value="${car.parkingSpot}" required>
                <div class="invalid-feedback">
                    Please enter a valid parking spot.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="mb-3">
                <label for="owner_id" class="form-label">Owner</label>
                <select id="owner_id" class="form-select" name="owner_id" required>
                    <option value="">Choose...</option>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <option value="${user.id}" ${car.ownerName eq user.username ? "selected" : ""}>${user.username}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please select a valid owner.
                </div>
            </div>
        </div>
        <input type="hidden" name="car_id" value="${car.id}" />
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</t:pageTemplate>
