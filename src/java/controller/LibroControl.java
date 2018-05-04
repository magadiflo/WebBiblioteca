/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LibroDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Libro;

/**
 *
 * @author Martín
 */
public class LibroControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LibroControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LibroControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String nombreAutor = request.getParameter("nombreAutor");
        String publicacion = request.getParameter("publicacion");
        int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
        String nitEditorial = request.getParameter("editorial");

        String accion = request.getParameter("accion").toLowerCase();

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setDescripcion(descripcion);
        libro.setNombreAutor(nombreAutor);
        libro.setPublicacion(publicacion);
        libro.setCodigoCategoria(codigoCategoria);
        libro.setNitEditorial(nitEditorial);

        switch (accion) {
            case "registrar":
                if (LibroDao.registrar(libro)) {
                    request.setAttribute("mensaje", "El libro: " + titulo + ", se registró correctamente.");
                } else {
                    request.setAttribute("mensaje", "Error al registrar el libro: " + titulo  + " , accion :" + accion);
                }   break;
            case "actualizar":
                if (LibroDao.actualizar(libro)) {
                    request.setAttribute("mensaje", "El libro: " + titulo + ", se actualizó.");
                } else {
                    request.setAttribute("mensaje", "Error al actualizar el libro: " + titulo);
                }   break;
            case "eliminar":
                if (LibroDao.eliminar(libro)) {
                    request.setAttribute("mensaje", "El libro: " + titulo + ", fue eliminado.");
                } else {
                    request.setAttribute("mensaje", "Error al eliminar el libro: " + titulo);
                }   break;
            default:
                request.setAttribute("mensaje", "Acción desconocida: " + accion);
                break;
        }
        request.getRequestDispatcher("registroLibro.jsp").forward(request, response);
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
