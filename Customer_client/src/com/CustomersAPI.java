package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CustomersAPI")
public class CustomersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Customer cusObj = new Customer();
	
    public CustomersAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
			
			String output = cusObj.insertCustomer(request.getParameter("customerName"),
			request.getParameter("customerAddress"),
			request.getParameter("customerNIC"),
			request.getParameter("customerEmail"),
			request.getParameter("customerPNO"));
			response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		}
		catch (Exception e)
		{
		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		
		String output = cusObj.updateCustomer(paras.get("hidCusSave").toString(),
						paras.get("customerName").toString(),
						paras.get("customerAddress").toString(),
						paras.get("customerNIC").toString(),
						paras.get("customerEmail").toString(),
						paras.get("customerPNO").toString());
		
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		
		String output = cusObj.deleteCustomer(paras.get("cID").toString());
		
		response.getWriter().write(output);
	}

}
