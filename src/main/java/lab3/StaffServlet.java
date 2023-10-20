package lab3;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

@MultipartConfig
@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/staff/form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// dinh dang thoi gian nhap vao
			DateTimeConverter dtc = new DateConverter(new Date(0));
			dtc.setPattern("MM/dd/yyyy");
			ConvertUtils.register(dtc, Date.class);

			
			Staff staff = new Staff();
			// doc tham so vao cac thuoc tinh cua bean staff
			BeanUtils.populate(staff, req.getParameterMap());
			File dir = new File(req.getServletContext().getRealPath("/files"));
			Part photo = req.getPart("photo_file");
			File photoFile = new File(dir, photo.getSubmittedFileName());
			photo.write(photoFile.getAbsolutePath());
			staff.setPhoto(photoFile.getName());
			
			// chia se voi result
			req.setAttribute("bean", staff);
			req.getRequestDispatcher("/staff/result.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	

}
