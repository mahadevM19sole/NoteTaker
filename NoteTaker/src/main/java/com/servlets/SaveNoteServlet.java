package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.entities.Note;
import com.helper.FactoryProvider;

public class SaveNoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SaveNoteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            // Create note object
            Note note = new Note(title, content, new Date());

            // Hibernate: save the note
            Session s = FactoryProvider.getFactory().openSession();
            Transaction tx = s.beginTransaction();
            s.save(note);
           tx.commit();
            s.close();

//            // Send response back to the user
            response.setContentType("text/html");
           PrintWriter out = response.getWriter();
           out.println("<h1 style='text-align:center;'>Note is added successfully</h1>");
            out.println("<h1 style='text-align:center;'><a href='all_notes.jsp'>View all notes</a></h1>");
//
      } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
