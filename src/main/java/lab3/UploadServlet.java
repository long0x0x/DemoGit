package lab3;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/views/form.jsp").forward(req, resp);;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// duong dan thu muc den tu goc website
		File dir = new File(req.getServletContext().getRealPath("/files"));
		if (!dir.exists()) { //tao neu chua ton tai
			dir.mkdir();			
		}
		//luu cac file upload vao thu muc file
		Part photo = req.getPart("photo_file"); //file hinh
		File photoFile = new File(dir, photo.getSubmittedFileName());
		photo.write(photoFile.getAbsolutePath());
		Part doc = req.getPart("doc_file");//phim tai lieu
	    File docFile = new File(dir, doc.getSubmittedFileName());
		doc.write(docFile.getAbsolutePath());
		
		req.setAttribute("img", photoFile);
		req.setAttribute("dic",docFile);
		
		//chia se cho result.jsp de hien thi
		
		req.getRequestDispatcher("/views/result.jsp").forward(req, resp);
	}

}
