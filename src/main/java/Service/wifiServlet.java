package Service;

import Service.UseWifi;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class wifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String command = request.getParameter("command");
        if(command != null && command.equals(("getWifi"))){
            UseWifi useWifi = new UseWifi();
            useWifi.save();
            int count = useWifi.count();

            request.setAttribute("count", count);
            request.getRequestDispatcher("/getWifiInfo.jsp").forward(request,response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
