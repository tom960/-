package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")//给这个类取一个访问的名字，浏览器通过这个名字来访问这个类
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")//为了在浏览器上能直接访问这个方法
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }

    //模拟处理查询请求的方法
    //下面的注解主要是给这个方法一个浏览器访问的路径
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    //演示怎么获得请求对象（封装请求数据），怎么或者响应对象（封装响应数据）
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        //请求行第一行的数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //请求行后面的数据
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }

        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html; charset=utf-8");
        try(
            PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>牛客网<h1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //简单的方式
    //GET请求
    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "1") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }


    //POST请求, 浏览器向服务器提交数据
    @RequestMapping(path="/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //向浏览器响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");//模板引擎就是view.html文件
        return mav;
    }

    //返回HTML网页
    @RequestMapping(path="/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 80);
        return "/demo/view";//（返回给客户端浏览器）view代表的就是view.html网页
    }

    //响应JSON数据（异步请求：比如注册B站账号的时候，
    // 昵称输入牛客网发现：框后面显示昵称已占用，这就是异步请求）
    //当前网页不刷新，但是悄悄的查询数据库并给网页返回一个数据
    // Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }


    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary", 9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        emp.put("salary", 10000.00);
        list.add(emp);

        return list;
    }

}
