/**
 * 
 */
package com.huawei.openapi.openaipexample.client.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.huawei.openapi.openaipexample.client.http.TokenUtil;
import com.opensymphony.xwork2.ActionContext;
import com.task.util.MKUtil;
import com.task.util.Util;

public class HttpRestClient {

	public static void main(String[] args) {
		HttpRestClient httpRestClient = new HttpRestClient();
		try {
			httpRestClient
					.getList("new_po", 1, 1, null, null, null, null, null);// huaweiPublishOrder、new_po、all
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static HttpURLConnection getConnection(String url)
			throws IOException {
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

	/***
	 * 查询PO列表
	 * 
	 * @param colTaskOrPoStatus
	 * @param pageIndex
	 * @param pageSize
	 * @param pageOrderForB2B
	 * @param fbStartDate
	 * @param fbEndDate
	 * @param cnStartDate
	 * @param cnEndDate
	 * @return
	 * @throws IOException
	 */
	public static Object[] getList(String colTaskOrPoStatus, Integer pageIndex,
			Integer pageSize, OrderForB2B pageOrderForB2B, String fbStartDate,
			String fbEndDate, String cnStartDate, String cnEndDate)
			throws IOException {
		String itemCode = "";
		String poNumber = "";
		String shipmentStatus = "all";

		if (colTaskOrPoStatus == null || colTaskOrPoStatus.length() <= 0) {
			colTaskOrPoStatus = "all";
		}

		if (pageOrderForB2B != null) {
			if (pageOrderForB2B.getItemCode() != null) {
				itemCode = pageOrderForB2B.getItemCode();
			}
			if (pageOrderForB2B.getPoNumber() != null) {
				poNumber = pageOrderForB2B.getPoNumber();
			}
			if (pageOrderForB2B.getShipmentStatus() != null) {
				shipmentStatus = pageOrderForB2B.getShipmentStatus();
			}
		}
		if (fbStartDate == null) {
			fbStartDate = "";
		}
		if (fbEndDate == null) {
			fbEndDate = "";
		}
		if (cnStartDate == null) {
			cnStartDate = "";
		}
		if (cnEndDate == null) {
			cnEndDate = "";
		}
		if (pageIndex == null || pageIndex <= 0) {
			pageIndex = 1;
		}
		String LIST_URL = "https://openapi.huawei.com:443/service/esupplier/findPoLineList/1.0.0/";
		// String LIST_URL =
		// "https://api-beta.huawei.com:443/service/esupplier/findPoLineList/1.0.0/"
		// + pageIndex;

		HttpURLConnection connection = getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		sb
				.append("{\"poStatus\":\"before_signe_back\","
						+ "\"combo3\":\"all\",\"combo2\":\"all\",\"combo1\":\"P\",\"shipmentStatus\":\""
						+ shipmentStatus
						+ "\",\"statusType\":\"COL_TASK_STATUS\",\"colTaskOrPoStatus\":\""
						+ colTaskOrPoStatus
						+ "\",\"poSubType\":\"P\",\"itemCode\":\"" + itemCode
						+ "\",\"poNumber\":\"" + poNumber
						+ "\",\"publishDateStart\":\"" + fbStartDate
						+ "\",\"publishDateEnd\":\"" + fbEndDate
						+ "\",\"promiseDateStart\":\"" + cnStartDate
						+ "\",\"promiseDateEnd\":\"" + cnEndDate + "\"}");
		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();
		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line)
					.getAsJsonObject();
			JsonArray jsonArray = jsonObject.getAsJsonArray("result");
			Gson gson = new Gson();
			for (JsonElement jsonElement : jsonArray) {
				OrderForB2B orderForB2B = gson.fromJson(jsonElement,
						new TypeToken<OrderForB2B>() {
						}.getType());
				String publishDate = Util.DateToString(Util.StringToDate(
						orderForB2B.getPublishDate(),
						"yyyy-MM-dd'T'HH:mm:ss.000+0800"),
						"yyyy-MM-dd HH:mm:ss");
				String promiseDate = Util.DateToString(Util.StringToDate(
						orderForB2B.getPromiseDate(),
						"yyyy-MM-dd'T'HH:mm:ss.000+0800"),
						"yyyy-MM-dd HH:mm:ss");
				orderForB2B.setPublishDate(publishDate);
				orderForB2B.setPromiseDate(promiseDate);
				orderList.add(orderForB2B);
			}

			// JsonParser parser = new JsonParser(); // 创建JSON解析器
			// JsonObject object = (JsonObject) parser.parse(line);
			// JsonArray array = object.get("result").getAsJsonArray(); //
			// 得到为json的数组
			// for (int i = 0; i < array.size(); i++) {
			// JsonObject subObject = array.get(i).getAsJsonObject();
			// String a = subObject.toString();
			// OrderForB2B orderForB2B = MKUtil.fromJson(a, OrderForB2B.class);
			// orderList.add(orderForB2B);
			//
			// // System.out.println("---------------");
			// // String b=MKUtil.toJson(orderForB2B);
			// // System.out.println("供应商编码="
			// // + subObject.get("vendorCode").getAsString());
			// // System.out.println("供应商名称="
			// // + subObject.get("vendorName").getAsString());
			// // System.out.println("PO号="
			// // + subObject.get("poNumber").getAsString());
			// // System.out.println("订单行号="
			// // + subObject.get("poLineNum").getAsInt());
			// // System.out.println("Item编码="
			// // + subObject.get("itemCode").getAsString());
			// // System.out.println("PR号="
			// // + subObject.get("prNumber").getAsString());
			// // System.out.println("订单下发日期="
			// // + subObject.get("publishDate").getAsString());
			// // System.out.println("订单状态="
			// // + subObject.get("shipmentStatus").getAsString());
			// // try {
			// // System.out.println("需求数量="
			// // + subObject.get("needQuantity").getAsFloat());
			// // } catch (Exception e) {
			// // e.printStackTrace();
			// // System.out.println("需求数量=0");
			// // }
			// // try {
			// // System.out.println("未交付数量="
			// // + subObject.get("dueQty").getAsFloat());
			// // } catch (Exception e) {
			// // e.printStackTrace();
			// // System.out.println("需求数量=0");
			// // }
			// // try {
			// // System.out.println("任务令="
			// // + subObject.get("taskNum").getAsString());
			// // } catch (Exception e) {
			// // System.out.println("任务令=");
			// // }
			// }
			// "pageVO":{"startIndex":701,"curPage":8,"mysqlStartIndex":700,"endIndex":800,"resultMode":0,"totalPages":8,"pageSize":100,"totalRows":766,"mysqlEndIndex":100}
			JsonObject data = jsonObject.get("pageVO").getAsJsonObject();
			pageSize = data.get("pageSize").getAsInt();
			count = data.get("totalRows").getAsInt();
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
		return new Object[] { orderList, count, pageSize };
	}

	/****
	 * 接受/遣返订单
	 * 
	 * @param lineLocationId
	 * @param instanceId
	 * @param operateType
	 * @return
	 * @throws IOException
	 */
	public static String signBackPOList(Integer lineLocationId,
			Integer instanceId, String operateType) throws IOException {
		String LIST_URL = "https://api-beta.huawei.com:443/service/esupplier/signBackPOList/1.0.0";
		HttpURLConnection connection = getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		sb.append("{\"operateType\":\"" + operateType
				+ "\",\"colTaskQueries\":[{\"lineLocationId\": "
				+ lineLocationId + ",\"instanceId\": " + instanceId
				+ ",\"businessType\":\"new_po\"}]}");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "UTF-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			// {"result":"操作成功!","code":"success","data":[{"msg":"操作成功","taskIndex":"null","lineLocationId":205677090,"code":"00000","instanceId":1,"poLineNum":"1","poNum":"HW20226748-1","taskId":2056770900100124}],"success":true,"failed":false}
			JsonParser parser = new JsonParser(); // 创建JSON解析器
			JsonObject object = (JsonObject) parser.parse(line);
			String code = object.get("code").getAsString();
			System.out.println("code=" + code);
			if ("success".equals(code)) {
				if ("accept".equals(operateType)) {

				}
			}
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
		return "";
	}

}
