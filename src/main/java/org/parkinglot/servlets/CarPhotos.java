package org.parkinglot.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.parkinglot.common.CarPhotoDto;
import org.parkinglot.ejb.CarsBean;

@WebServlet(name = "CarPhotos", value = "/CarPhotos")
public class CarPhotos  extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request,
                         jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {

        Integer carId = Integer.parseInt(request.getParameter("id"));
        CarPhotoDto carPhotoDto = carsBean.findPhotoByCarId(carId);
        if(carPhotoDto != null){
            response.setContentType(carPhotoDto.getFileType());
            response.setContentLength(carPhotoDto.getFileContent().length);
            response.getOutputStream().write(carPhotoDto.getFileContent());
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
