<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageTemplate pageTitle="🚙 Cars 🚙">
    <h1>🚙 CARS 🚙</h1>
    <div class="container text-center">
        <div class="row">
            <div class="col">
                Chevrolet
            </div>
            <div class="col">
                Strada Muncitorilor
            </div>
            <div class="col">
                Jack London
            </div>
        </div>
        <div class="row">
            <div class="col">
                Tesla
            </div>
            <div class="col">
                Strada Curentului
            </div>
            <div class="col">
                Thomas Edison
            </div>
        </div>
        <div class="row">
            <div class="col">
                Toyota
            </div>
            <div class="col">
                Strada Monstrilor
            </div>
            <div class="col">
                Shigeru Miyamoto
            </div>
        </div>
    </div>
    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>