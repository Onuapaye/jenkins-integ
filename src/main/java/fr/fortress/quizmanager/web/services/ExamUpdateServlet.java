package fr.fortress.quizmanager.web.services;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.fortress.quizmanager.model.Exam;
import fr.fortress.quizmanager.services.ExamService;

/**
 * Servlet implementation class QuestionUpdate
 */
@WebServlet("/exam-question")
public class ExamUpdateServlet extends SpringServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	ExamService examService;
    /**
     * Default constructor. 
     */
    public ExamUpdateServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/update-exam.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Exam exam = new Exam();

		exam.setExamId(Integer.parseInt(request.getParameter("hiddenExamId")));
		exam.setExamTitleName(request.getParameter("examTitle"));
		exam.setExamDescription(request.getParameter("examDescription"));
	
		boolean examCreated = examService.createExam(exam);
		if (examCreated == true) {
			response.sendRedirect("exams");
		} else {
			//request.setAttribute("errorMessage", "Invalid Username or Password entered.");
			request.getRequestDispatcher("/WEB-INF/views/create-exam.jsp").forward(request, response);
		}
	}

}
