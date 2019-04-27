/**
 * 
 */
package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.huawei.openapi.openaipexample.client.http.TokenUtil;

/**
 * @author wWX345526
 * @createTime 2016年6月30日
 * @JDK 1.7
 */
public class HttpRestClient {

	private final String LIST_URL = "https://api-beta.huawei.com:443/service/esupplier/findPoLineList/1.0.0/1";
	// private final String LIST_URL =
	// "https://api-beta.huawei.com:443/service/openapi/example/rest/student/getlist/1.0.0/";
	private final String UPDATE_URL = "https://api-beta.huawei.com:443/service/openapi/example/rest/student/updateStudent/1.0.0";
	private final String ADD_URL = "https://api-beta.huawei.com:443/service/openapi/example/rest/student/addStudent/1.0.0";
	private final String DELETE_URL = "https://api-beta.huawei.com:443/service/openapi/example/rest/student/deleteStudent/1.0.0";
	private final String BYID_URL = "https://api-beta.huawei.com:443/service/openapi/example/rest/student/getStudentById/1.0.0";

	public static void main(String[] args) {
		HttpRestClient httpRestClient = new HttpRestClient();
		try {
			httpRestClient.getList();
			// httpRestClient.addStudent();
			// httpRestClient.deleteStudent();
			httpRestClient.getStudentById();
			// httpRestClient.updateStudent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getList() throws IOException {
		HttpURLConnection connection = getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		StringBuilder sb = new StringBuilder();
		sb
				.append("{'combo3':'all','combo2':'all','combo1':'P','shipmentStatus':'all','statusType':'COL_TASK_STATUS','colTaskOrPoStatus':'all','poSubType':'P'}");
		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey()
						+ "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
	}

	public void addStudent() throws IOException {
		HttpURLConnection connection = getConnection(ADD_URL);
		connection.setRequestMethod("POST");// 请求方式
		// 此服务支持两种格式提交数据，一种是JSON，一种是XML
		// connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Type", "application/xml");
		StringBuilder sb = new StringBuilder();
		// sb.append(" {  student:{  \"age\": 29,    \"email\": \"openapi9@huawei.com\", \"name\": \"openAPI9\",    \"phone\": 13838457159,    \"sex\": 2 }}");
		sb.append("<student>");
		sb.append("<name>test</name>");
		sb.append("<age>25test</age>");
		sb.append("<sex>2test</sex>");
		sb.append("<email>openapi9test@huawei.com</email>");
		sb.append("<phone>13410103251test</phone>");
		sb.append("</student>");
		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		connection.disconnect();
	}

	public void deleteStudent() throws IOException {
		HttpURLConnection connection = getConnection(DELETE_URL + "/5/");
		connection.setRequestMethod("DELETE");// 请求方式
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		connection.disconnect();
	}

	public void getStudentById() throws IOException {
		HttpURLConnection connection = getConnection(BYID_URL + "/10/");
		connection.setRequestMethod("GET");// 请求方式
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		connection.disconnect();
	}

	public void updateStudent() throws IOException {
		HttpURLConnection connection = getConnection(UPDATE_URL);
		connection.setRequestMethod("PUT");// 请求方式
		// 此服务支持两种格式提交数据，一种是JSON，一种是XML
		// connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Type", "application/xml");
		StringBuilder sb = new StringBuilder();
		// sb.append(" {  student:{ \"id\":5 \"age\": 29,    \"email\": \"openapi9@huawei.com\", \"name\": \"openAPI9\",    \"phone\": 13838457159,    \"sex\": 2 }}");
		sb.append("<student>");
		sb.append("<id>5</id>");
		sb.append("<name>wangzhijun</name>");
		sb.append("<age>25</age>");
		sb.append("<sex>2</sex>");
		sb.append("<email>openapi9@huawei.com</email>");
		sb.append("<phone>13410103251</phone>");
		sb.append("</student>");
		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		connection.disconnect();
	}

	private HttpURLConnection getConnection(String url) throws IOException {
		URL postUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Charset", "UTF-8");
		connection.addRequestProperty("Authorization", "Bearer "
				+ TokenUtil.instance().getToken());// 有权限认证的服务必须加上认证
		return connection;
	}
}
