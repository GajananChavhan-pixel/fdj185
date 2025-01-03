package edu.cjc.sms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.servicei.StudentServiceI;

@Controller
public class AdminController {

	@Autowired
	StudentServiceI ssi;
	
	@RequestMapping("/")
	public String preLogin()
	{
		return "login";
	}
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam("username") String u, @RequestParam("password") String p, Model m)
	{
		if(u.equals("admin") && p.equals("admin"))
		{
			m.addAttribute("data", ssi.getAllStudents());
			return "adminscreen";
		}else {
			return "login";
		}
	}
	
	@RequestMapping("/enroll_student")
	public String addStudent(@ModelAttribute Student s, Model m)
	{
		List<Student> list =ssi.addStudent(s);
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String searchStudent(@RequestParam("batchNumber") String batchNumber ,Model m){

		List<Student> list = ssi.getBatchesStudent(batchNumber);
		
		if(list.size()>0) {
			m.addAttribute("data", list);
			return "adminscreen";
		} else {
			List<Student> l = ssi.getAllStudents();
			m.addAttribute("data", l);
			m.addAttribute("message", "No Record Available  "+batchNumber);
		return "adminscreen";
	}
}
}