package org.parkinglot.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.Part;
import org.parkinglot.common.CarDto;
import org.parkinglot.ejb.CarsBean;

@MultipartConfig
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_CARS"}))
@WebServlet(name = "AddCarPhoto", value = "/AddCarPhoto")
public class AddCarPhoto extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request,
                         jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {

        Long carId = Long.parseLong(request.getParameter("id"));
        CarDto car = carsBean.findById(carId);
        request.setAttribute("car", car);

        request.getRequestDispatcher("/WEB-INF/pages/addCarPhoto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request,
                          jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {

        Long carId = Long.parseLong(request.getParameter("car_id"));
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();
        long fileSize = filePart.getSize();
        byte[] fileContent = new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);

        carsBean.addPhotoToCar(carId, fileName, fileType, fileContent);
        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
