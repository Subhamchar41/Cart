/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

@WebServlet("/DisplayItem")
public class DisplayItem extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try{
        int add_cart_product=0;
        Cookie c=null;
           Connection conn=ConnectDB.connect();
           String q="SELECT * FROM PRODUCT";
           PreparedStatement pst=conn.prepareStatement(q);
           ResultSet trs=pst.executeQuery();
       
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
         
       c=new Cookie(request.getParameter("ItemId"),"1");
            response.addCookie(c);
            out.print("Product Added");
        }catch(Exception e){
            e.printStackTrace();
        }
        out.print("<a href='ViewItem'>View Item</a>");
        out.print(
          "<style>"
                +"td,th(padding14px 30px)"
                +"body{font-family:arial;}"
                +"table{border:1px solid black;}"
                +"a:hover{color:red;}"
          +"</style>"
             +"<table>"
               +"<tr>"
                +"<th>id</th>"
               +"<th>name</th>"
               +"<th>price</th>"
                   
        );
        
        
        while(trs.next()){
            out.print("<tr><td>"+trs.getInt(1) +"</td>" 
                    +"<td>"+trs.getString(2) +"</td>"
                    +"<td>"+trs.getInt(3) +"</td>"  
                    +"<td><a href='DisplayItems?ItemId=" +trs.getInt(1)+"'>Add</a></td>"
                    +"</tr><br><br>");
        }
        out.print("</table></body><html>");
    }
    catch(Exception ex)
    {
        out.print(ex);
    }
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
