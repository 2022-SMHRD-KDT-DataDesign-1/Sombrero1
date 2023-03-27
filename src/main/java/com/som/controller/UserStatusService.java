package com.som.controller;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.som.command.Command;
import com.som.model.UsersDAO;
import com.som.model.UsersVO;

public class UserStatusService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String moveURL = "";
		String user_status = request.getParameter("user_status");
		String user_id = request.getParameter("user_id");
		String status = null;
		
		if(user_status.equals("depositcomplete")) {
			status = "입금완료";
		}else if(user_status.equals("rentalComplete")) {
			status = "대여완료";
		}else if(user_status.equals("returnProgress")) {
			status = "반납진행";
		}else if(user_status.equals("returnComplete")) {
			status = "반납완료";
		}else if(user_status.equals("wrightReview")) {
			status = "후기 남기기";
		}else if(user_status.equals("resetStatus")) {
			status = null;
		}
		
		
		UsersVO vo = new UsersVO(user_id,status);
		UsersDAO dao = new UsersDAO();
		int cnt = dao.updateStatus(vo);
		
		if(cnt>0) {			
		System.out.println("수정성공");
		request.getSession().setAttribute("user_status", vo);
		moveURL = "admin.jsp";
		}else {
			moveURL = "admin.jsp";

		}
		
		return moveURL;

	}

}
