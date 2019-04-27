package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class plate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public plate() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	// 保存字符串到文件中
	@SuppressWarnings("unused")
	private static boolean SaveFile(byte[] content, String path, String imgName) {
		FileOutputStream writer = null;
		boolean result = false;
		try {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			writer = new FileOutputStream(new File(path, imgName));
			writer.write(content);
			result = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is 11111111111");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		// JSONObject jsonObject;
		String line = null;
		try {
			request.setCharacterEncoding("utf-8");
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				//jb.append(line);
			}
			try {
				request.getRequestDispatcher(
						"AccessAction_carPaiVerify.action?barurl1="
								+ jb.toString()).forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) { /* report an error */
		}

		// System.out.println(jb.toString());
		// try {
		// jsonObject = JSONObject.fromObject(jb.toString());
		// } catch (Exception e) {
		// // crash and burn
		// throw new IOException("Error parsing JSON request string");
		// }
		// 保存图片
		// try {
		// String image = jsonObject.getJSONObject("AlarmInfoPlate")
		// .getJSONObject("result")
		// .getJSONObject("PlateResult")
		// .getString("imageFile");
		// BASE64Decoder decoder = new BASE64Decoder();
		// byte[] decoderBytes = decoder.decodeBuffer(image);
		// if(SaveFile(decoderBytes,"d:\\","snapshot.jpg")){
		// out.println("image saved!");
		// }
		// }catch (Exception e) {
		// crash and burn
		// throw new IOException("Error no image");
		// }
		// //返回json内容，如果选择发送图片，把这段注释
		// response.setContentType("text/json");
		// PrintWriter out = response.getWriter();
		//		
		// out.println(jsonObject.toString());
		// out.flush();
		// out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
