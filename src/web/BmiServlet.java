package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet("/BmiServlet")
public class BmiServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final Gson GSON = new GsonBuilder().create();
	private JsonObject jsonIn;
	private BmiCalculator bmiCalcu;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			//將收到的req轉成JsonObject物件
			jsonIn= json2In(req);
			System.out.println("jsonIn"+jsonIn.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//解析JsonObject物件，此處有兩層{"action":"save","BmiCalculator":"{BmiCalculator物件}"}
		//先解第一層action，看是save or get
		String action = jsonIn.get("action").getAsString();
		String outStr = "";
		JsonObject jsonOut = new JsonObject();
		switch (action) {
		//action: save
		case "save":
			//取出第二層BmiCalculator物件，存在此網頁物件中，然後再回傳{"resultCode":1}；用writeJson()傳回client端
			bmiCalcu=GSON.fromJson((jsonIn.get("BmiCalculator").getAsString()),BmiCalculator.class);
			jsonOut.addProperty("resultCode", 1);
			outStr = jsonOut.toString();
			System.out.println("jsonOut"+outStr);
			writeJson(resp, outStr);
			break;
		//action: get
		case "get":
			//分別將{"resultCode":2}及{"bmiCalcu":有bmi值的bmiCalcu物件}，存入jsonOut；用writeJson()傳回client端
			//bmi = bmiCalcu.getBMI();
			bmiCalcu.getBMI();
			jsonOut.addProperty("resultCode", 2);
			jsonOut.addProperty("bmiCalcu", GSON.toJson(bmiCalcu));
			outStr = jsonOut.toString();
			System.out.println("jsonOut"+outStr);
			writeJson(resp, outStr);
			break;
			
		default:
			break;
		}
	}
	
	private void writeJson(HttpServletResponse resp, String outStr) {
		try (PrintWriter pw = resp.getWriter()){
			resp.setContentType(CONTENT_TYPE);
			pw.print(outStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private JsonObject json2In(HttpServletRequest req) {
		StringBuilder jsonIn = new StringBuilder();
		try(BufferedReader br = req.getReader()) {
			String line;
			while ((line=br.readLine())!=null) {
				jsonIn.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GSON.fromJson(jsonIn.toString(), JsonObject.class);
	}

	
}
