package com.martdevelopers.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.martdevelopers.model.User;
import com.martdevelopers.service.UserService;


@Controller
public class UserController {

 @Autowired
 private UserService userService;
 
 
 //index or home page controller
 
 @RequestMapping(value = {"/"}, method=RequestMethod.GET)
 public ModelAndView index()
 {
	 ModelAndView model = new ModelAndView();
	  
	  model.setViewName("frontend/index");
	  return model;
 }
 

 //login page controller

 @RequestMapping(value= {"/login"}, method=RequestMethod.GET)
 public ModelAndView login() 
 {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("user/login");
  return model;
 }
 

 //signup controller
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() 
 {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  model.setViewName("user/signup");
  
  return model;
 }


 //signup verification controller

 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  User userExists = userService.findUserByEmail(user.getEmail());
  
  if(userExists != null) {
   bindingResult.rejectValue("email", "error.user", "This email already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("user/signup");
  } 
  else
  {
   userService.saveUser(user);
   model.addObject("msg", "Success! Your Account Has Been Created");
   model.addObject("user", new User());
   model.setViewName("user/signup");
  }
  
  return model;
  
 }
 


 /*
 Student's controllers
 -->These Are The One and only controllers in students dashboard

  */

//Student Dashboard controller
 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
 public ModelAndView home()
 {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  
  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
  model.setViewName("home/home");
  return model;
 }


 //All courses controllers

 @RequestMapping(value = {"/home/home/allcourses"}, method=RequestMethod.GET)
 public ModelAndView allcourses()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("home/allcourses");
  return model;
 }


 //Mycourse Controller
 @RequestMapping(value = {"/home/home/mycourse"}, method=RequestMethod.GET)
 public ModelAndView myCourses()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("home/mycourse");
  return model;
 }

 //Student Profile Controller
 @RequestMapping(value = {"/home/home/myprofile"}, method=RequestMethod.GET)
 public ModelAndView myprofile()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("home/myprofile");
  return model;
 }

 //Student CourseMaterials  Controller
 @RequestMapping(value = {"/home/home/coursematerials"}, method=RequestMethod.GET)
 public ModelAndView coursematerials()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("home/coursematerials");
  return model;
 }

 //Student take Assignments  Controller
 @RequestMapping(value = {"/home/home/questions"}, method=RequestMethod.GET)
 public ModelAndView questions()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("home/questions");
  return model;
 }

 //Student submmit Assignments  Controller
 @RequestMapping(value = {"/home/home/submit_assignments"}, method=RequestMethod.GET)
 public ModelAndView submitAssignments()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("home/submit_assignments");
  return model;
 }
 



 //verification of accounts or details submitted in the form
 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }

 
 /*
  * 
  * Admin Account Controllers
  * 
  */



 //1 > Admin Login Controller
 @RequestMapping(value= {"/admin/login"}, method=RequestMethod.GET)
 public ModelAndView adminlogin()
 {
     ModelAndView model = new ModelAndView();

     model.setViewName("admin/login");
     return model;
 }


 /*
 //2>Admin Dashboard Controller
  */

 @RequestMapping(value= {"/admin/home"}, method=RequestMethod.GET)
 public ModelAndView adminindex()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("admin/home");
  return model;
 }

//3> Admin Enroll  Students Controller

 @RequestMapping(value= {"/admin/enroll"}, method=RequestMethod.GET)
 public ModelAndView adminEnrollStudents()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("admin/enroll");
  return model;
 }

 //4 Admin  View Enrolled Students

 @RequestMapping(value= {"/admin/viewenrolled"}, method=RequestMethod.GET)
 public ModelAndView adminViewEnrollStudents()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("admin/viewenrolled");
  return model;
 }

//5 Admin create course controller

@RequestMapping(value= {"/admin/createcourse"}, method=RequestMethod.GET)
public ModelAndView adminCreateCourse()
{
 ModelAndView model = new ModelAndView();

 model.setViewName("admin/createcourse");
 return model;
}

//6: Admin View Courses
@RequestMapping(value= {"/admin/viewcourses"}, method=RequestMethod.GET)
public ModelAndView adminViewCourse()
{
 ModelAndView model = new ModelAndView();

 model.setViewName("admin/viewcourses");
 return model;
}

//7:admin upload assaignment controller
@RequestMapping(value= {"/admin/upload_questions"}, method=RequestMethod.GET)
public ModelAndView adminUpload_Questions()
{
 ModelAndView model = new ModelAndView();

 model.setViewName("admin/upload_questions");
 return model;
}

 //7:admin grade assaignment controller
 @RequestMapping(value= {"/admin/review_questions"}, method=RequestMethod.GET)
 public ModelAndView adminReview_Questions()
 {
  ModelAndView model = new ModelAndView();

  model.setViewName("admin/review_questions");
  return model;
 }



}
 