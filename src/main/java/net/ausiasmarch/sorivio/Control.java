package net.ausiasmarch.sorivio;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Control extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            Integer alto = Integer.parseInt(request.getParameter("alto"));
            Integer ancho = Integer.parseInt(request.getParameter("ancho"));
            Integer max = 30;
            Gson oGson = new Gson();
            if (ancho == null || ancho > max || ancho < 1 || alto == null || alto > max || alto < 1) {
                out.print(oGson.toJson("Error en los parámetros: ancho y alto deben ser enteros entre 1 y 50."));
            } else {
                ArrayList alBlock = new ArrayList<>();
                for (int i = 1; i <= alto; i++) {
                    ArrayList alRow = new ArrayList<>();
                    for (int j = 1; j <= ancho; j++) {
                        alRow.add(i * j);
                    }
                    alBlock.add(alRow);
                }
                out.print(oGson.toJson(alBlock));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
