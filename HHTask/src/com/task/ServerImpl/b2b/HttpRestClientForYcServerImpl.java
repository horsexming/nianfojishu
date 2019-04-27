/**
 * 
 */
package com.task.ServerImpl.b2b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.StrutsStatics;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.openapi.openaipexample.client.http.OrderForB2B;
import com.huawei.openapi.openaipexample.client.http.TokenUtil;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.b2b.HttpRestClientForYcServer;
import com.task.util.MKUtil;

public class HttpRestClientForYcServerImpl implements HttpRestClientForYcServer {
	private TotalDao totalDao;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 100;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * public static void main(String[] args) { try {
	 * 
	 * selbaioqian(); } catch (IOException e) { e.printStackTrace(); } }
	 */

	/**
	 * 导出Excel
	 */
	public void get(Integer pageIndex, Integer pageSize, String itemCode, String suppItemCode, String orgId,
			String startTime, String endTime, String buyerName, String purchaseMode, String version, String zhuangtai,
			String taday, String nexttoday, String twonextday, String threeday, String forday, String friveday,
			String sixday, String sevenday, String eitday, String nineday, String tenday, String elevenday)
			throws IOException {
		List<OrderForB2B> listall = new ArrayList<OrderForB2B>();
		Integer page = null;
		if (zhuangtai.equals("当前页")) {
			page = pageIndex;
			pageIndex = 1;
		}

		for (int j = 1; j <= pageIndex; j++) {
			Date day = new Date();
			String LIST_URL = null;
			// 打开url
			if (zhuangtai.equals("当前页")) {
				LIST_URL = "https://openapi.huawei.com:443/service/esupplier/findForecastList/1.0.0/" + page;
			} else if (zhuangtai.equals("全部")) {
				LIST_URL = "https://openapi.huawei.com:443/service/esupplier/findForecastList/1.0.0/" + j;
			}

			if ((itemCode == null || itemCode.equals("")) && (suppItemCode == null || suppItemCode.equals(""))
					&& (orgId == null || orgId.equals("")) && (startTime == null || startTime.equals(""))
					&& (endTime == null || endTime.equals("")) && (buyerName == null || buyerName.equals(""))
					&& (purchaseMode == null || purchaseMode.equals("")) && (version == null || version.equals(""))) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String dayString = df.format(day);
				startTime = dayString;
				endTime = dayString;
			}

			if (startTime == null || endTime == null) {

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(df.format(day));
				String dayString = df.format(day);
				startTime = dayString;
				endTime = dayString;
			}

			String poStatus = "";

			// itemCode 华为物料编码
			if (itemCode == null) {
				itemCode = "";
			}
			if (suppItemCode == null) {
				// suppItemCode 供法物料编码
				suppItemCode = "";
			}

			// if (orgId == null) {
			// orgId组织名称，固定值218是华为
			orgId = "218";
			// }
			if (buyerName == null) {
				// 采购员
				buyerName = "";
			}

			if (purchaseMode == null) {
				// 采购模式
				purchaseMode = "";
			}

			if (version == null) {
				// 欠料标识
				version = "";
			}

			HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			StringBuilder sb = new StringBuilder();
			sb.append("{\"suppItemCode \":\"" + poStatus + "\",\"itemCode\":\"" + itemCode + "\",\"suppItemCode\":\""
					+ suppItemCode + "\",\"orgId\":\"" + orgId + "\",\"startTime\":\"" + startTime + "\",\"endTime\":\""
					+ endTime + "\",\"purchaseMode\":\"" + purchaseMode + "\" ,\"buyerName\":\"" + buyerName
					+ "\" ,\"version\":\"" + version + "\"}");

			OutputStream output = connection.getOutputStream();
			output.write(sb.toString().getBytes("UTF-8"));
			output.flush();
			output.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line = "";
			Integer count = 0;
			ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();

				JsonObject jsonObject2 = new JsonParser().parse(jsonObject.get("data").toString()).getAsJsonObject();
				JsonArray jsonArray = jsonObject2.getAsJsonArray("result");

				Gson gson = new Gson();
				for (JsonElement jsonElement : jsonArray) {

					JsonObject jsonObject3 = new JsonParser().parse(jsonElement.toString()).getAsJsonObject();

					OrderForB2B b2b = new OrderForB2B();

					if (jsonObject3.get("version") != null) {
						b2b.setItemRevision(jsonObject3.get("version").toString().replace("\"", "").replace("\"", ""));
					}

					JsonObject jsonObject4 = new JsonParser().parse(jsonObject3.get("data").toString())
							.getAsJsonObject();

					if (jsonObject4.get("Q1").toString() != "null" || jsonObject4.get("Q1") != null) {
						b2b.setExpireDate(jsonObject4.get("Q1").toString());
					} else {
						b2b.setExpireDate("0");
					}

					if (jsonObject4.get("Q2").toString() != "null" || jsonObject4.get("Q2") != null) {
						b2b.setPhrLastUpdateDate(jsonObject4.get("Q2").toString());
					} else {
						b2b.setPhrLastUpdateDate("0");
					}

					if (jsonObject4.get("Q3").toString() != "null" || jsonObject4.get("Q3") != null) {
						b2b.setStartDate(jsonObject4.get("Q3").toString());
					} else {
						b2b.setStartDate("0");
					}

					if (jsonObject4.get("Q4").toString() != "null" || jsonObject4.get("Q4") != null) {
						b2b.setPhrCreationDate(jsonObject4.get("Q3").toString());
					} else {
						b2b.setPhrCreationDate("0");
					}

					if (jsonObject4.get("Q5").toString() != "null" || jsonObject4.get("Q5") != null) {
						b2b.setLastUpdateDate(jsonObject4.get("Q5").toString());
					} else {
						b2b.setLastUpdateDate("0");
					}

					if (jsonObject4.get("Q6").toString() != "null" || jsonObject4.get("Q6") != null) {
						b2b.setRateDate(jsonObject4.get("Q6").toString());
					} else {
						b2b.setRateDate("0");
					}

					if (jsonObject4.get("Q7").toString() != "null" || jsonObject4.get("Q7") != null) {
						b2b.setCreationDate(jsonObject4.get("Q7").toString());
					} else {
						b2b.setCreationDate("0");
					}
					if (jsonObject4.get("Q8").toString() != "null" || jsonObject4.get("Q8") != null) {
						b2b.setApprovedDate(jsonObject4.get("Q8").toString());
					} else {
						b2b.setApprovedDate("0");
					}
					if (jsonObject4.get("Q9").toString() != "null" || jsonObject4.get("Q9") != null) {
						b2b.setRecvVendorTelNum(jsonObject4.get("Q9").toString());
					} else {
						b2b.setRecvVendorTelNum("0");
					}
					if (jsonObject4.get("Q10").toString() != "null" || jsonObject4.get("Q10") != null) {
						b2b.setPrhaInterfaceSourceCode(jsonObject4.get("Q10").toString());
					} else {
						b2b.setPrhaInterfaceSourceCode("0");
					}
					if (jsonObject4.get("Q11").toString() != "null" || jsonObject4.get("Q11") != null) {
						b2b.setCreatedBy(jsonObject4.get("Q11").toString());
					} else {
						b2b.setCreatedBy("0");
					}

					if (jsonObject4.get("Q12").toString() != "null" || jsonObject4.get("Q12") != null) {
						b2b.setBillToLocationId(jsonObject4.get("Q12").toString());
					} else {
						b2b.setBillToLocationId("0");
					}

					if (jsonObject4.get("total").toString() != null) {

						b2b.setNumber(Integer.parseInt(jsonObject4.get("total").toString()));

					}

					// 在途订单数量quantity
					if (jsonObject3.get("openPOQty") != null) {

						String shulopnen = jsonObject3.get("openPOQty").toString();
						b2b.setTermsMode(shulopnen);
					}

					if (jsonObject3.get("suppItemCode") != null) {
						b2b.setVendorCode(jsonObject3.get("suppItemCode").toString());
					}

					// 数据类型
					if ((jsonObject3.get("dataMeasure")) != null) {
						String dataMeasure = jsonObject3.get("dataMeasure").toString().replace("\"", "");
						b2b.setItemDescription(dataMeasure);
					}
					// VMI库存w
					if ((jsonObject3.get("vmiQty")) != null) {
						b2b.setQuantityRejected(jsonObject3.get("vmiQty").toString());
					}
					// 供应商库存
					if ((jsonObject3.get("currentInventory")) != null) {
						b2b.setCurrencyCode(jsonObject3.get("currentInventory").toString());
					}
					//
					// // 订单行号poLineNum
					// if ((jsonObject3.get("lineNo")) != null) {
					// b2b.setPoLineNum(Integer.parseInt(jsonObject3.get("lineNo").toString()));
					// }

					// 采购员
					if ((jsonObject3.get("buyerName")) != null) {
						String buyerNam = jsonObject3.get("buyerName").toString().replace("\"", "");
						b2b.setAgentName(buyerNam);
					}

					// 采购模式
					if ((jsonObject3.get("purchaseMode")) != null) {
						String purchaseMod = jsonObject3.get("purchaseMode").toString().replace("\"", "");
						b2b.setBusinessMode(purchaseMod);
					}

					// 机柜IV类名称器件
					if ((jsonObject3.get("partSort")) != null) {
						String partSort = jsonObject3.get("partSort").toString().replace("\"", "");
						b2b.setVendorName(partSort);
					}

					// 华为物料供应商编码
					if ((jsonObject3.get("itemCode")) != null) {
						String item = jsonObject3.get("itemCode").toString().replace("\"", "");
						b2b.setItemCode(item);
					}

					// 发布日期
					if ((jsonObject3.get("publishDate")) != null) {
						// 去双引号
						String publishDate = jsonObject3.get("publishDate").toString().replace("\"", "");

						b2b.setPublishDate(publishDate);
					}
					if (jsonObject3.get("version") != null) {
						b2b.setItemRevision(jsonObject3.get("version").toString().replace("\"", "").replace("\"", ""));
					}

					orderList.add(b2b);

				}
				listall.addAll(orderList);
				JsonObject data = jsonObject2.get("pageVO").getAsJsonObject();
				pageSize = data.get("pageSize").getAsInt();
				count = data.get("totalRows").getAsInt();

			}

			Map<String, List<String>> headerFields = connection.getHeaderFields();
			for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
				if ("activityID".equals(element.getKey())) {
					System.out
							.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
				}
			}
			reader.close();
			connection.disconnect();
		}

		// 导入
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("ISCP_Supplier_Forecast" + df.format(new Date()));
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("供方物料编码");
		row.createCell(1).setCellValue("华为物料编码");
		row.createCell(2).setCellValue("版本号");
		row.createCell(3).setCellValue("采购模式");
		row.createCell(4).setCellValue("器件分类");
		row.createCell(5).setCellValue("采购员");
		row.createCell(6).setCellValue("发布日期");
		row.createCell(7).setCellValue("在途订单数量");
		row.createCell(8).setCellValue("VMI实时库存");
		row.createCell(9).setCellValue("VMI库存");
		row.createCell(10).setCellValue("供应商库存");
		row.createCell(11).setCellValue("数据类型");
		row.createCell(12).setCellValue(taday);
		row.createCell(13).setCellValue(nexttoday);
		row.createCell(14).setCellValue(twonextday);
		row.createCell(15).setCellValue(threeday);
		row.createCell(16).setCellValue(forday);
		row.createCell(17).setCellValue(friveday);
		row.createCell(18).setCellValue(sixday);
		row.createCell(19).setCellValue(sevenday);
		row.createCell(20).setCellValue(eitday);
		row.createCell(21).setCellValue(nineday);
		row.createCell(22).setCellValue(tenday);
		row.createCell(23).setCellValue(elevenday);
		row.createCell(24).setCellValue("合计");

		for (int b = 0; b < listall.size(); b++) {
			OrderForB2B orderForB2B = listall.get(b);
			HSSFRow createRow = sheet.createRow(b + 1);
			createRow.createCell(0).setCellValue(orderForB2B.getVendorCode());
			createRow.createCell(1).setCellValue(orderForB2B.getItemCode());
			createRow.createCell(2).setCellValue(orderForB2B.getItemRevision());
			createRow.createCell(3).setCellValue(orderForB2B.getBusinessMode());
			createRow.createCell(4).setCellValue(orderForB2B.getVendorName());
			createRow.createCell(5).setCellValue(orderForB2B.getAgentName());
			createRow.createCell(6).setCellValue(orderForB2B.getPublishDate());
			createRow.createCell(7).setCellValue(orderForB2B.getTermsMode());
			createRow.createCell(8).setCellValue(orderForB2B.getQuantityRejected());
			createRow.createCell(9).setCellValue(orderForB2B.getQuantityRejected());
			createRow.createCell(10).setCellValue(orderForB2B.getCurrencyCode());
			createRow.createCell(11).setCellValue(orderForB2B.getItemDescription());
			createRow.createCell(12).setCellValue(orderForB2B.getExpireDate());
			createRow.createCell(13).setCellValue(orderForB2B.getPhrLastUpdateDate());
			createRow.createCell(14).setCellValue(orderForB2B.getStartDate());
			createRow.createCell(15).setCellValue(orderForB2B.getPhrCreationDate());
			createRow.createCell(16).setCellValue(orderForB2B.getLastUpdateDate());
			createRow.createCell(17).setCellValue(orderForB2B.getRateDate());
			createRow.createCell(18).setCellValue(orderForB2B.getCreationDate());
			createRow.createCell(19).setCellValue(orderForB2B.getApprovedDate());
			createRow.createCell(20).setCellValue(orderForB2B.getRecvVendorTelNum());
			createRow.createCell(21).setCellValue(orderForB2B.getPrhaInterfaceSourceCode());
			createRow.createCell(22).setCellValue(orderForB2B.getCreatedBy());
			createRow.createCell(23).setCellValue(orderForB2B.getCreatedBy());
			createRow.createCell(24).setCellValue(orderForB2B.getNumber());
		}
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String datep = "ISCP_Supplier_Forecast" + df1.format(new Date());
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(StrutsStatics.HTTP_RESPONSE);
		OutputStream os;
		os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(datep.getBytes("GB2312"), "8859_1") + ".xls");
		workbook.write(os);
		workbook.close();

	}

	/***
	 * 查询采购预测能力接口
	 */
	@SuppressWarnings("unused")
	public Object[] getList(Integer pageIndex, Integer pageSize, String itemCode, String suppItemCode, String orgId,
			String startTime, String endTime, String buyerName, String purchaseMode, String version)
			throws IOException {

		// 打开url
		String LIST_URL = "https://openapi.huawei.com:443/service/esupplier/findForecastList/1.0.0/" + pageIndex;

		if ((itemCode == null || itemCode.equals("")) && (suppItemCode == null || suppItemCode.equals(""))
				&& (orgId == null || orgId.equals("")) && (startTime == null || startTime.equals(""))
				&& (endTime == null || endTime.equals("")) && (buyerName == null || buyerName.equals(""))
				&& (purchaseMode == null || purchaseMode.equals("")) && (version == null || version.equals(""))) {
			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(df.format(day));
			String dayString = df.format(day);
			startTime = dayString;
			endTime = dayString;
		}

		if (startTime == null || endTime == null) {
			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(df.format(day));
			String dayString = df.format(day);
			startTime = dayString;
			endTime = dayString;
		}

		String poStatus = "";

		// itemCode 华为物料编码
		if (itemCode == null) {
			itemCode = "";
		}
		if (suppItemCode == null) {
			// suppItemCode 供法物料编码
			suppItemCode = "";
		}

		// if (orgId == null) {
		// orgId组织名称，固定值218是华为
		orgId = "218";
		// }
		if (buyerName == null) {
			// 采购员
			buyerName = "";
		}

		if (purchaseMode == null) {
			// 采购模式
			purchaseMode = "";
		}

		if (version == null) {
			// 欠料标识
			version = "";
		}

		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		sb.append("{\"suppItemCode \":\"" + poStatus + "\",\"itemCode\":\"" + itemCode + "\",\"suppItemCode\":\""
				+ suppItemCode + "\",\"orgId\":\"" + orgId + "\",\"startTime\":\"" + startTime + "\",\"endTime\":\""
				+ endTime + "\",\"purchaseMode\":\"" + purchaseMode + "\" ,\"buyerName\":\"" + buyerName
				+ "\" ,\"version\":\"" + version + "\"}");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
			if (jsonObject.get("result").toString().replace("\"", "").equals("No record was found!")) {
				orderList = null;
				return new Object[] { orderList, count, pageSize };
			}

			JsonObject jsonObject2 = new JsonParser().parse(jsonObject.get("data").toString()).getAsJsonObject();
			JsonArray jsonArray = jsonObject2.getAsJsonArray("result");

			Gson gson = new Gson();
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject3 = new JsonParser().parse(jsonElement.toString()).getAsJsonObject();

				OrderForB2B b2b = new OrderForB2B();

				if (jsonObject3.get("version") != null) {
					b2b.setItemRevision(jsonObject3.get("version").toString().replace("\"", "").replace("\"", ""));
				}

				JsonObject jsonObject4 = new JsonParser().parse(jsonObject3.get("data").toString()).getAsJsonObject();

				if (jsonObject4.get("Q1").toString() != "null" || jsonObject4.get("Q1") != null) {
					b2b.setExpireDate(jsonObject4.get("Q1").toString());
				} else {
					b2b.setExpireDate("0");
				}

				if (jsonObject4.get("Q2").toString() != "null" || jsonObject4.get("Q2") != null) {
					b2b.setPhrLastUpdateDate(jsonObject4.get("Q2").toString());
				} else {
					b2b.setPhrLastUpdateDate("0");
				}

				if (jsonObject4.get("Q3").toString() != "null" || jsonObject4.get("Q3") != null) {
					b2b.setStartDate(jsonObject4.get("Q3").toString());
				} else {
					b2b.setStartDate("0");
				}

				if (jsonObject4.get("Q4").toString() != "null" || jsonObject4.get("Q4") != null) {
					b2b.setPhrCreationDate(jsonObject4.get("Q3").toString());
				} else {
					b2b.setPhrCreationDate("0");
				}

				if (jsonObject4.get("Q5").toString() != "null" || jsonObject4.get("Q5") != null) {
					b2b.setLastUpdateDate(jsonObject4.get("Q5").toString());
				} else {
					b2b.setLastUpdateDate("0");
				}

				if (jsonObject4.get("Q6").toString() != "null" || jsonObject4.get("Q6") != null) {
					b2b.setRateDate(jsonObject4.get("Q6").toString());
				} else {
					b2b.setRateDate("0");
				}

				if (jsonObject4.get("Q7").toString() != "null" || jsonObject4.get("Q7") != null) {
					b2b.setCreationDate(jsonObject4.get("Q7").toString());
				} else {
					b2b.setCreationDate("0");
				}
				if (jsonObject4.get("Q8").toString() != "null" || jsonObject4.get("Q8") != null) {
					b2b.setApprovedDate(jsonObject4.get("Q8").toString());
				} else {
					b2b.setApprovedDate("0");
				}
				if (jsonObject4.get("Q9").toString() != "null" || jsonObject4.get("Q9") != null) {
					b2b.setRecvVendorTelNum(jsonObject4.get("Q9").toString());
				} else {
					b2b.setRecvVendorTelNum("0");
				}
				if (jsonObject4.get("Q10").toString() != "null" || jsonObject4.get("Q10") != null) {
					b2b.setPrhaInterfaceSourceCode(jsonObject4.get("Q10").toString());
				} else {
					b2b.setPrhaInterfaceSourceCode("0");
				}
				if (jsonObject4.get("Q11").toString() != "null" || jsonObject4.get("Q11") != null) {
					b2b.setCreatedBy(jsonObject4.get("Q11").toString());
				} else {
					b2b.setCreatedBy("0");
				}

				if (jsonObject4.get("Q12").toString() != "null" || jsonObject4.get("Q12") != null) {
					b2b.setBillToLocationId(jsonObject4.get("Q12").toString());
				} else {
					b2b.setBillToLocationId("0");
				}

				if (jsonObject4.get("total").toString() != null) {

					b2b.setNumber(Integer.parseInt(jsonObject4.get("total").toString()));

				}

				// 在途订单数量quantity
				if (jsonObject3.get("openPOQty") != null) {

					String shulopnen = jsonObject3.get("openPOQty").toString().replace("\"", "");
					b2b.setTermsMode(shulopnen);
				}

				if (jsonObject3.get("suppItemCode") != null) {
					b2b.setVendorCode(jsonObject3.get("suppItemCode").toString());
				}

				// 数据类型
				if ((jsonObject3.get("dataMeasure")) != null) {
					String dataMeasure = jsonObject3.get("dataMeasure").toString().replace("\"", "");
					b2b.setItemDescription(dataMeasure);
				}
				// VMI库存w
				if ((jsonObject3.get("vmiQty")) != null) {
					b2b.setQuantityRejected(jsonObject3.get("vmiQty").toString());
				}
				// 供应商库存
				if ((jsonObject3.get("currentInventory")) != null) {
					b2b.setCurrencyCode(jsonObject3.get("currentInventory").toString());
				}
				//
				// // 订单行号poLineNum
				// if ((jsonObject3.get("lineNo")) != null) {
				// b2b.setPoLineNum(Integer.parseInt(jsonObject3.get("lineNo").toString()));
				// }

				// 采购员
				if ((jsonObject3.get("buyerName")) != null) {
					String buyerNam = jsonObject3.get("buyerName").toString().replace("\"", "");
					b2b.setAgentName(buyerNam);
				}

				// 采购模式
				if ((jsonObject3.get("purchaseMode")) != null) {
					String purchaseMod = jsonObject3.get("purchaseMode").toString().replace("\"", "");
					b2b.setBusinessMode(purchaseMod);
				}

				// 机柜IV类名称器件
				if ((jsonObject3.get("partSort")) != null) {
					String partSort = jsonObject3.get("partSort").toString().replace("\"", "");
					b2b.setVendorName(partSort);
				}

				// 华为物料供应商编码
				if ((jsonObject3.get("itemCode")) != null) {
					String item = jsonObject3.get("itemCode").toString().replace("\"", "");
					b2b.setItemCode(item);
				}

				// 发布日期
				if ((jsonObject3.get("publishDate")) != null) {
					// 去双引号
					String publishDate = jsonObject3.get("publishDate").toString().replace("\"", "");

					b2b.setPublishDate(publishDate);
				}

				orderList.add(b2b);

			}
			JsonObject data = jsonObject2.get("pageVO").getAsJsonObject();
			pageSize = data.get("pageSize").getAsInt();
			count = data.get("totalRows").getAsInt();
		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		return new Object[] { orderList, count, pageSize };
	}

	/**
	 * 1.2回复供应能力数据
	 * 
	 */
	public static Object[] gethuifu() throws IOException {

		String poStatus = "";
		String itemCode = "02113041";
		String Code = "02113041";
		String itemok = "02113077";
		String it = "02113087";
		String suppItemCode = "";
		String orgId = "218";
		String da = "2018-11-28";
		String startTime = "2018-11-27";
		String endTime = "2018-11-27";
		String LIST_URL = "https://openapi.huawei.com:443/service/esupplier/updateForecast/1.0.0";
		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		// sb.append("{\"suppItemCode \":\"" + poStatus + "\",\"itemCode\":\"" +
		// itemCode + "\",\"suppItemCode\":\""
		// + suppItemCode + "\",\"orgId\":\"" + orgId + "\",\"startTime\":\"" +
		// startTime + "\",\"endTime\":\""
		// + endTime + "\"}");
		sb.append("{\"supplyResponse\"：[" + "{\"data\":{\"" + da + "\":" + 123 + "}," + "\"itemCode\":" + "\"" + Code
				+ "\"" + "},{\"data\":{\"" + da + "\":" + 123 + "}," + "\"itemCode\":" + "\"" + Code + "\""
				+ "}  ],\"queryVO\":{\"orgId\":\"" + orgId + "\", }}");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();

			JsonObject jsonObject2 = new JsonParser().parse(jsonObject.get("code").toString()).getAsJsonObject();
			JsonObject jsonObject3 = new JsonParser().parse(jsonObject.get("success").toString()).getAsJsonObject();
			JsonObject jsonObject4 = new JsonParser().parse(jsonObject.get("result").toString()).getAsJsonObject();

		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		return new Object[] {};
	}

	/**
	 * 回复能力数据接口
	 */
	@Override
	public boolean getet(String data) throws IOException {
		String orgId = "218";
		String LIST_URL = "https://openapi.huawei.com:443/service/esupplier/updateForecast/1.0.0";
		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();

		sb.append("{\"supplyResponse\"：[" + data + "],\"queryVO\":{\"orgId\":\"" + orgId + "\" }}");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		boolean re = false;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();

			if (jsonObject.get("success").toString() != "true") {
				re = false;
			} else {
				re = true;
			}
			JsonObject jsonObject2 = new JsonParser().parse(jsonObject.get("code").toString()).getAsJsonObject();
			JsonObject jsonObject3 = new JsonParser().parse(jsonObject.get("success").toString()).getAsJsonObject();
			JsonObject jsonObject4 = new JsonParser().parse(jsonObject.get("result").toString()).getAsJsonObject();
		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		if (re == true) {
			MKUtil.writeJSON(true, "回复成功", null);
		} else {
			MKUtil.writeJSON(false, "回复失败", null);
		}

		return re;
	}

	/**
	 * 到货供应能力
	 */
	public static Object[] getdaohuo(Integer pageIndex, Integer pageSize) throws IOException {
		// itemCode 华为物料编码
		String itemCode = "";
		// 子公司id 157，218是华为
		String orgnizationId = "218";
		// 紧急标志“ALL”“Y”“N”
		String urgentFlag = "ALL";
		// 供应商库存等于0
		String vendorCode = "0";
		// 需求状态
		String requestStatus = "";
		// 需求结束发放时间
		String sourceDateEndStr = "2018-12-17";
		// 需求开始发放时间
		String sourceDateStr = "2018-12-17";
		// 七天内是否有需求
		String hebdomadHasDemand = "0";
		// 七天内是否有gap
		String hebdomadHasGAP = "0";
		// 在途数量
		String openPoQty = "0";
		// 采购员
		String buyerName = "";
		// 打开url
		String LIST_URL = "https://openapi.huawei.com:443/service/esupplier/findDunDemand/1.0.0/" + pageIndex;

		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		sb.append("{\"buyerName\": \"" + buyerName + "\",\"hebdomadHasDemand\":" + hebdomadHasDemand + ""
				+ ",\"hebdomadHasGAP\":" + hebdomadHasGAP + " ,\"item\":\"" + itemCode + "\" ,\"openPoQty\":"
				+ openPoQty + " , \"orgId\":\"" + orgnizationId + "\",\"requestStatus\":\"" + requestStatus
				+ "\",\"sourceDateStr\":\"" + sourceDateStr + "\"" + ",\"sourceDateEndStr\":\"" + sourceDateEndStr
				+ "\",\"urgentFlag\":\"" + urgentFlag + "\",\"vendorInv\":" + vendorCode + " }");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		System.out.println(reader.readLine());
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();

			JsonObject jsonObject2 = new JsonParser().parse(jsonObject.get("pageVO").toString()).getAsJsonObject();
			JsonArray jsonArray = jsonObject.getAsJsonArray("result");

			JsonObject data = jsonObject2.get("pageVO").getAsJsonObject();
			pageSize = data.get("pageSize").getAsInt();
			count = data.get("totalRows").getAsInt();
		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		return new Object[] { count, pageSize };
	}

	private static int p = 0;

	/***
	 * 查询可预约DN信息（queryDNList）
	 */
	@SuppressWarnings("unused")
	public Object[] songhuo(Integer pageSize, Integer curPage, String validityPeriod) throws IOException {

		p = 0;
		// 打开url
		String LIST_URL = "https://openapi.huawei.com:443/service/asn/queryDNList/1.0.0";

		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		StringBuilder sb = new StringBuilder();
		sb.append("{\"validityPeriod\":\"" + validityPeriod + "\",\"pageSize\":" + pageSize + ",\"curPage\":" + curPage
				+ "}");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();

			JsonArray jsonArray = jsonObject.getAsJsonArray("result");

			Gson gson = new Gson();
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject2 = new JsonParser().parse(jsonElement.toString()).getAsJsonObject();
				OrderForB2B b2b = new OrderForB2B();

				// 客户公司
				if (jsonObject2.get("orgName").toString().equals("null")) {
					b2b.setOrgName("");
				} else {
					b2b.setOrgName(jsonObject2.get("orgName").toString().replace("\"", ""));
				}

				// 需求ID2
				if (jsonObject2.get("demandNumber2").toString().equals("null")) {
					b2b.setPoHeaderId("");
				} else {
					b2b.setPoHeaderId(jsonObject2.get("demandNumber2").toString().replace("\"", ""));
				}
				// 需求/计划交货日期
				if (jsonObject2.get("issueTime").toString().equals("null")) {
					b2b.setNeedByDate("");
				} else {
					b2b.setNeedByDate(jsonObject2.get("issueTime").toString().replace("\"", ""));
				}
				// 物料编码
				if (jsonObject2.get("itemCode").toString().equals("null")) {
					b2b.setItemCode("");
				} else {
					b2b.setItemCode(jsonObject2.get("itemCode").toString().replace("\"", ""));
				}

				// 物料版本
				if (jsonObject2.get("itemRevision").toString().equals("null")) {
					b2b.setItemRevision("");
				} else {
					b2b.setItemRevision(jsonObject2.get("itemRevision").toString().replace("\"", ""));
				}
				// 需求数量
				if (jsonObject2.get("requestQty").toString().equals("null")) {
					b2b.setShipmentType("");
				} else {
					b2b.setShipmentType(jsonObject2.get("requestQty").toString().replace("\"", ""));
				}
				// 可发货数量
				if (jsonObject2.get("availableQty").toString().equals("null")) {
					b2b.setReceivedFinishFlag("");
				} else {
					b2b.setReceivedFinishFlag(jsonObject2.get("availableQty").toString().replace("\"", ""));
				}
				// PO可预约总数量
				if (jsonObject2.get("poAppointList").toString().equals("[]")) {
					b2b.setTermsDescription("");
				} else {
					JsonArray asJsonArray = jsonObject2.get("poAppointList").getAsJsonArray();

					for (JsonElement jsonElement2 : asJsonArray) {
						JsonObject jsonObject3 = new JsonParser().parse(jsonElement2.toString()).getAsJsonObject();
						b2b.setTermsDescription(jsonObject3.get("availableQty").toString().replace("\"", ""));
					}

				}
				// 本次发货数量
				if (jsonObject2.get("quantityShipped").toString().equals("null")) {
					b2b.setAgentEmployeeNumber("");
				} else {
					b2b.setAgentEmployeeNumber(jsonObject2.get("quantityShipped").toString().replace("\"", ""));
				}
				// 交货地点
				if (jsonObject2.get("shipToCode").toString().equals("null")) {
					b2b.setItemDescription("");
				} else {
					b2b.setItemDescription(jsonObject2.get("shipToCode").toString().replace("\"", ""));
				}
				// 字库
				if (jsonObject2.get("sublibrary").toString().equals("null")) {
					b2b.setTypeLookupCode("");
				} else {
					b2b.setTypeLookupCode(jsonObject2.get("sublibrary").toString().replace("\"", ""));
				}
				// 货位
				if (jsonObject2.get("allocation").toString().equals("null")) {
					b2b.setRecvVendorAddr("");
				} else {

					b2b.setRecvVendorAddr(jsonObject2.get("allocation").toString().replace("\"", ""));
				}
				// 最早到货时间
				if (jsonObject2.get("needTimeStart").toString().equals("")) {
					b2b.setPhrCreationDate("");
				} else {
					b2b.setPhrCreationDate(jsonObject2.get("needTimeStart").toString().replace("\"", ""));
				}
				// 要求到货时间
				if (jsonObject2.get("needTime").toString().equals("")) {
					b2b.setRateDate("");
				} else {
					b2b.setRateDate(jsonObject2.get("needTime").toString().replace("\"", ""));
				}
				// 最晚到货时间
				if (jsonObject2.get("needTimeEnd").toString().equals("")) {
					b2b.setLastUpdateDate("");
				} else {
					b2b.setLastUpdateDate(jsonObject2.get("needTimeEnd").toString().replace("\"", ""));
				}
				// 来源sourceCode
				if (jsonObject2.get("sourceCode").toString().equals("")) {
					b2b.setPrecision("");
				} else {
					b2b.setPrecision(jsonObject2.get("sourceCode").toString().replace("\"", ""));
				}
				orderList.add(b2b);
			}

			JsonObject data = jsonObject.get("pageVO").getAsJsonObject();

			pageSize = data.get("pageSize").getAsInt();
			count = data.get("totalRows").getAsInt();
		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		return new Object[] { orderList, count, pageSize };
	}

	/***
	 * 填写ASN信息==查询item包规
	 */

	@SuppressWarnings("unused")
	public Object[] selASN(String itemCode, String itemRevision, String quantityShipped, String invOrgId)
			throws IOException {
		p = 0;
		// 打开url
		String LIST_URL = "https://openapi.huawei.com:443/service/asn/queryitemPkgDIM/1.0.0";

		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();

		sb.append("{\"itemList\":[{\"itemCode\":\"" + itemCode + "\",\"itemRevision\":\"" + itemRevision
				+ "\",\"quantityShipped\":" + quantityShipped + " }],\"vendorCode\": \"\",\"invOrgId\": \"" + invOrgId
				+ "\" }");

		// ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();
		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;

		OrderForB2B forB2B = new OrderForB2B();
		String baogui = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();

			JsonObject jsonresult = new JsonParser().parse(jsonObject.get("result").toString()).getAsJsonObject();

			// 客户
			if (jsonresult.get("vendorCode").toString().replace("\"", "").equals("null")) {
				forB2B.setReceivedFinishFlag("");
			} else {
				forB2B.setReceivedFinishFlag(jsonresult.get("vendorCode").toString().replace("\"", ""));
			}

			// 备注
			if (jsonresult.get("comments").toString().replace("\"", "").equals("null")) {
				forB2B.setAuthorizationStatus("");
			} else {
				forB2B.setAuthorizationStatus(jsonresult.get("comments").toString().replace("\"", ""));
			}

			JsonArray array = jsonresult.get("itemInfoList").getAsJsonArray();

			for (JsonElement jsonElement2 : array) {

				JsonObject jsonObject3 = new JsonParser().parse(jsonElement2.toString()).getAsJsonObject();

				// 物料编码
				if (jsonObject3.get("itemCode").toString().replace("\"", "").equals("null")) {
					forB2B.setShipmentType("");
				} else {
					forB2B.setShipmentType(jsonObject3.get("itemCode").toString().replace("\"", ""));
				}

				// 物料版本
				if (jsonObject3.get("itemRevision").toString().replace("\"", "").equals("null")) {
					forB2B.setPartNumber("");
				} else {
					forB2B.setPartNumber(jsonObject3.get("itemRevision").toString().replace("\"", ""));
				}
				// 追溯类型
				if (jsonObject3.get("tracingMode").toString().replace("\"", "").equals("null")) {
					forB2B.setRepOfficeCode("");
				} else {
					forB2B.setRepOfficeCode(jsonObject3.get("tracingMode").toString().replace("\"", ""));
				}
				// 物料描述
				if (jsonObject3.get("itemDesc").toString().replace("\"", "").equals("null")) {
					forB2B.setQuantityAccepted("");
				} else {
					forB2B.setQuantityAccepted(jsonObject3.get("itemDesc").toString().replace("\"", ""));
				}
				// 发货数量
				if (jsonObject3.get("quantityShipped").toString().replace("\"", "").equals("null")) {
					forB2B.setSendConnecter("");
				} else {
					forB2B.setSendConnecter(jsonObject3.get("quantityShipped").toString().replace("\"", ""));
				}
				// 箱件数
				if (jsonObject3.get("numOfContainers").toString().replace("\"", "").equals("null")) {
					forB2B.setRepOfficeName("");
				} else {
					forB2B.setRepOfficeName(jsonObject3.get("numOfContainers").toString().replace("\"", ""));
				}

				// 外检信息
				if (jsonObject3.get("iQCInfo").toString().replace("\"", "").equals("null")) {
					forB2B.setComments("");
				} else {
					forB2B.setComments(jsonObject3.get("iQCInfo").toString().replace("\"", ""));
				}

				// 子库
				if (jsonObject3.get("sublibrary").toString().replace("\"", "").equals("null")) {
					forB2B.setBillToLocationId("");
				} else {
					forB2B.setBillToLocationId(jsonObject3.get("sublibrary").toString().replace("\"", ""));
				}

				// 货位
				if (jsonObject3.get("allocation").toString().replace("\"", "").equals("null")) {
					forB2B.setCreatedBy("");
				} else {
					forB2B.setCreatedBy(jsonObject3.get("allocation").toString().replace("\"", ""));
				}

				// 原产地
				if (jsonObject3.get("countryOfOrigin").toString().replace("\"", "").equals("null")) {
					forB2B.setPrhaInterfaceSourceCode("");
				} else {
					forB2B.setPrhaInterfaceSourceCode(jsonObject3.get("countryOfOrigin").toString().replace("\"", ""));
				}
				// 环保标识
				if (jsonObject3.get("rohs").toString().replace("\"", "").equals("null")) {
					forB2B.setRecvVendorTelNum("");
				} else {
					forB2B.setRecvVendorTelNum(jsonObject3.get("rohs").toString().replace("\"", ""));
				}

				// 日韩标识
				if (jsonObject3.get("specialFlag").toString().replace("\"", "").equals("null")) {
					forB2B.setCategory("");
				} else {
					forB2B.setCategory(jsonObject3.get("specialFlag").toString().replace("\"", ""));
				}

				// 是否法检
				if (jsonObject3.get("lawInspectionFlag").toString().replace("\"", "").equals("null")) {
					forB2B.setPrimaryKey("");
				} else {
					forB2B.setPrimaryKey(jsonObject3.get("lawInspectionFlag").toString().replace("\"", ""));
				}

				// 是否多件套
				if (jsonObject3.get("setFlag") == null) {
					forB2B.setPaymentTerms("");
				} else {
					forB2B.setPaymentTerms(jsonObject3.get("setFlag").toString().replace("\"", ""));
				}

				// 套件数
				if (jsonObject3.get("setTotals").toString().replace("\"", "").equals("null")) {
					forB2B.setBusinessMode("");
				} else {
					forB2B.setBusinessMode(jsonObject3.get("setTotals").toString().replace("\"", ""));
				}

				// FCC认证
				if (jsonObject3.get("fccType").toString().replace("\"", "").equals("null")) {
					forB2B.setColTaskOrPoStatus("");
				} else {
					forB2B.setColTaskOrPoStatus(jsonObject3.get("fccType").toString().replace("\"", ""));
				}
				// CE认证
				if (jsonObject3.get("ciType") == null) {
					forB2B.setShipmentNum("");
				} else {
					forB2B.setShipmentNum(jsonObject3.get("ciType").toString().replace("\"", ""));
				}
				// 制造日期date
				if (jsonObject3.get("DateCode") == null) {
					forB2B.setPromiseDate("");
				} else {
					forB2B.setPromiseDate(jsonObject3.get("DateCode").toString().replace("\"", ""));
				}
				// 制造批次
				if (jsonObject3.get("lot").toString().replace("\"", "").equals("null")) {
					forB2B.setExpireDate("");
				} else {
					forB2B.setExpireDate(jsonObject3.get("lot").toString());
				}
				// 制造工厂
				if (jsonObject3.get("packageList").toString().equals("null")) {
					p++;
					baogui = "包规异常";
				} else {
					baogui = "包规无异常";
					JsonArray packageList = jsonObject3.get("packageList").getAsJsonArray();
					for (JsonElement jsonElement : packageList) {
						JsonObject jsonObject6 = new JsonParser().parse(jsonElement.toString()).getAsJsonObject();
						// 包规
						if (jsonObject6.get("isDefault") == null) {
							forB2B.setTermsMode("");
						} else {
							forB2B.setTermsMode(jsonObject6.get("isDefault").toString().replace("\"", ""));
						}
						// 内装数量
						if (jsonObject6.get("packingQty").toString().replace("\"", "").equals("null")) {
							forB2B.setUnitOfMeasure("");
						} else {
							forB2B.setUnitOfMeasure(jsonObject6.get("packingQty").toString().replace("\"", ""));
						}
						// 件套数SN/TN
						if (jsonObject6.get("setsComments").toString().replace("\"", "").equals("null")) {
							forB2B.setShipToLocation("");
						} else {
							forB2B.setShipToLocation(jsonObject6.get("setsComments").toString().replace("\"", ""));
						}
						// 净重
						if (jsonObject6.get("netWeight").toString().replace("\"", "").equals("nuull")) {
							forB2B.setIssuOffice("");
						} else {
							forB2B.setIssuOffice(jsonObject6.get("netWeight").toString().replace("\"", ""));
						}
						// 毛重
						if (jsonObject6.get("grossWeight").toString().replace("\"", "").equals("nuull")) {
							forB2B.setRemark("");
						} else {
							forB2B.setRemark(jsonObject6.get("grossWeight").toString().replace("\"", ""));
						}
						// 重量单位
						if (jsonObject6.get("unitOfWeight").toString().replace("\"", "").equals("nuull")) {
							forB2B.setPrNumber("");
						} else {
							forB2B.setPrNumber(jsonObject6.get("unitOfWeight").toString().replace("\"", ""));
						}
						// 外箱长
						if (jsonObject6.get("length").toString().replace("\"", "").equals("null")) {
							forB2B.setTaskNum("");
						} else {
							forB2B.setTaskNum(jsonObject6.get("length").toString().replace("\"", ""));
						}
						// 外箱宽
						if (jsonObject6.get("width").toString().replace("\"", "").equals("null")) {
							forB2B.setAgentName("");

						} else {
							forB2B.setAgentName(jsonObject6.get("width").toString().replace("\"", ""));
						}
						// 外箱高
						if (jsonObject6.get("height").toString().replace("\"", "").equals("null")) {
							forB2B.setCarrierName("");
						} else {
							forB2B.setCarrierName(jsonObject6.get("height").toString().replace("\"", ""));
						}
						// 尺寸单位
						if (jsonObject6.get("unitOfMeasure").toString().replace("\"", "").equals("null")) {
							forB2B.setTypeLookupCode("");
						} else {
							forB2B.setTypeLookupCode(jsonObject6.get("unitOfMeasure").toString().replace("\"", ""));
						}
						// 是否带板送货
						if (jsonObject6.get("isStack").toString().replace("\"", "").equals("null")) {
							forB2B.setObjectChangeContext("");
						} else {
							forB2B.setObjectChangeContext(jsonObject6.get("isStack").toString().replace("\"", ""));
						}
						// 栈板规格
						if (jsonObject6.get("stackSpecifications").toString().replace("\"", "").equals("null")) {
							forB2B.setProjectNo("");
						} else {
							forB2B.setProjectNo(jsonObject6.get("stackSpecifications").toString().replace("\"", ""));
						}

					}

				}

			}
			// orderList.add(forB2B);
		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}

		reader.close();
		connection.disconnect();
		// orderList,
		return new Object[] { forB2B, p, baogui };
	}

	/**
	 * 导出单条数据
	 */
	public static void daochu() throws IOException {
		List<OrderForB2B> listall = new ArrayList<OrderForB2B>();
		Integer page = null;

	}

	/***
	 * 确认ASN信息
	 */
	@SuppressWarnings("unused")
	public static Object[] SureASN() throws IOException {

		// 打开url
		String LIST_URL = "https://openapi.huawei.com:443/service/asn/queryItem4ASNPOList/1.0.0";

		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		String vendorCode = "";

		String orgCode = "";

		// 本次发货数量
		String itemCode = "";
		// 客户
		String itemRevision = "null";
		int curPage = 1;
		int endIndex = 0;
		int pageSize = 20;
		int startIndex = 0;
		int totalPages = 0;
		int totalRows = 0;

		// 库存组织id
		String packingType = "";
		int quantityShipped = 10;
		String isPrePrintLable = "N";
		String asnHeaderId = "null";

		sb.append(
				"{\"vendorCode\":\"" + vendorCode + "\",\"orgCode\":\"" + orgCode + "\",\"itemList\":[{\"itemCode\":\""
						+ itemCode + "\",\"itemRevision\": " + null + " }],\"pageVO\":{\"curPage\":" + curPage
						+ ",\"endIndex\": " + endIndex + ",\"pageSize\": " + pageSize + ",\"startIndex\":" + startIndex
						+ ",\"totalPages\":" + totalPages + ",\"totalRows\":" + totalRows + "} }");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
			JsonArray jsonArray = jsonObject.getAsJsonArray("result");

			Gson gson = new Gson();
			for (JsonElement jsonElement : jsonArray) {

				JsonObject jsonObject3 = new JsonParser().parse(jsonElement.toString()).getAsJsonObject();
				OrderForB2B forB2B = new OrderForB2B();

				// 客户
				jsonObject3.get("orgName").toString();
				// poAppointList
				jsonObject3.get("poNumber").toString();
				jsonObject3.get("itemCode").toString();
				jsonObject3.get("itemRevision").toString();
				jsonObject3.get("itemAvailableQty").toString();
				// 可发货数量
				jsonObject3.get("availableQty").toString();

				jsonObject3.get("quantityShipped").toString();
				// po类型
				jsonObject3.get("businessMode").toString();
				// 订单行
				jsonObject3.get("poLineNum").toString();
				jsonObject3.get("shipmentNum").toString();
				jsonObject3.get("approvedDate").toString();
				jsonObject3.get("itemDesc").toString();
				jsonObject3.get("partNumber").toString();
				jsonObject3.get("tradeTerms").toString();
				jsonObject3.get("taskNum").toString();
				jsonObject3.get("accountTaskOrder").toString();
				jsonObject3.get("poRemark").toString();
				orderList.add(forB2B);
			}

		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		return new Object[] { orderList };
	}

	/**
	 * 查询标签
	 */
	@SuppressWarnings("unused")
	public Object[] excel(Integer pageSize, Integer curPage, String vendorCode) throws IOException {

		// 打开url
		String LIST_URL = "https://openapi.huawei.com:443/service/asn/queryLabelList/1.0.0";

		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		if (vendorCode == null) {
			vendorCode = "";
		}

		sb.append("{\"vendorCode\":  \"" + vendorCode + "\",\"pageVo\":{\"pageSize\":" + pageSize + ",\"curPage\":"
				+ curPage + "}}");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
			JsonObject data = jsonObject.get("pageVO").getAsJsonObject();
			pageSize = data.get("pageSize").getAsInt();
			count = data.get("totalRows").getAsInt();
			JsonArray jsonArray = jsonObject.getAsJsonArray("result");

			Gson gson = new Gson();
			for (JsonElement jsonElement : jsonArray) {

				JsonObject jsonObject3 = new JsonParser().parse(jsonElement.toString()).getAsJsonObject();
				OrderForB2B b2b = new OrderForB2B();

				// 籍号
				if (jsonObject3.get("barcode").toString().replace("\"", "").equals("null")) {
					b2b.setVendorCode("");
				} else {
					b2b.setVendorCode(jsonObject3.get("barcode").toString().replace("\"", ""));
				}

				// 物料编码
				if (jsonObject3.get("itemCode").toString().replace("\"", "").equals("null")) {
					b2b.setVendorName("");
				} else {
					b2b.setVendorName(jsonObject3.get("itemCode").toString().replace("\"", ""));
				}
				// 物料版本
				if (jsonObject3.get("itemRevision").toString().replace("\"", "").equals("null")) {
					b2b.setColTaskOrPoStatus("");
				} else {
					b2b.setColTaskOrPoStatus(jsonObject3.get("itemRevision").toString().replace("\"", ""));
				}

				// 追溯类型
				if (jsonObject3.get("tracingMode").toString().replace("\"", "").equals("null")) {
					b2b.setShipmentStatus("");
				} else {
					b2b.setShipmentStatus(jsonObject3.get("tracingMode").toString().replace("\"", ""));
				}

				// ASN单号
				if (jsonObject3.get("shipmentNum").toString().replace("\"", "").equals("null")) {
					b2b.setPoSubType("");
				} else {
					b2b.setPoSubType(jsonObject3.get("shipmentNum").toString().replace("\"", ""));
				}

				// PO号
				if (jsonObject3.get("poNumber").toString().replace("\"", "").equals("null")) {
					b2b.setPoNumber("");
				} else {
					b2b.setPoNumber(jsonObject3.get("poNumber").toString().replace("\"", ""));
				}

				// 内装数量
				if (jsonObject3.get("qty").toString().replace("\"", "").equals("null")) {
					b2b.setPoHeaderId("");
				} else {
					b2b.setPoHeaderId(jsonObject3.get("qty").toString().replace("\"", ""));
				}

				// 制造日期
				if (jsonObject3.get("dateCode").toString().replace("\"", "").equals("null")) {
					b2b.setPoReleaseId("");
				} else {
					b2b.setPoReleaseId(jsonObject3.get("dateCode").toString().replace("\"", ""));
				}

				// 制造批次
				if (jsonObject3.get("lot").toString().replace("\"", "").equals("null")) {
					b2b.setPublishDate("");
				} else {
					b2b.setPublishDate(jsonObject3.get("lot").toString().replace("\"", ""));
				}

				// 打印次数
				if (jsonObject3.get("printCount").toString().replace("\"", "").equals("null")) {
					b2b.setBusinessMode("");
				} else {
					b2b.setBusinessMode(jsonObject3.get("printCount").toString().replace("\"", ""));
				}
				// 创建时间
				if (jsonObject3.get("creationDate").toString().replace("\"", "").equals("null")) {
					b2b.setCurrencyCode("");
				} else {
					b2b.setCurrencyCode(jsonObject3.get("creationDate").toString().replace("\"", ""));
				}

				// 最后修改时间
				if (jsonObject3.get("lastUpdateDate").toString().replace("\"", "").equals("null")) {
					b2b.setShipmentNum("");
				} else {
					b2b.setShipmentNum(jsonObject3.get("lastUpdateDate").toString().replace("\"", ""));
				}

				orderList.add(b2b);
			}

		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		return new Object[] { orderList, count, pageSize };
	}

	/**
	 * 查询标签
	 */
	@SuppressWarnings("unused")
	public Object[] selbaioqian(Integer pageSize, Integer curPage, String vendorCode) throws IOException {

		// 打开url
		String LIST_URL = "https://openapi.huawei.com:443/service/asn/queryLabelList/1.0.0";

		HttpURLConnection connection = TokenUtil.getConnection(LIST_URL);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder sb = new StringBuilder();
		if (vendorCode == null) {
			vendorCode = "";
		}

		sb.append("{\"vendorCode\":  \"" + vendorCode + "\",\"pageVo\":{\"pageSize\":" + pageSize + ",\"curPage\":"
				+ curPage + "}}");

		OutputStream output = connection.getOutputStream();
		output.write(sb.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		Integer count = 0;
		ArrayList<OrderForB2B> orderList = new ArrayList<OrderForB2B>();

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
			JsonObject data = jsonObject.get("pageVO").getAsJsonObject();
			pageSize = data.get("pageSize").getAsInt();
			count = data.get("totalRows").getAsInt();
			JsonArray jsonArray = jsonObject.getAsJsonArray("result");

			Gson gson = new Gson();
			for (JsonElement jsonElement : jsonArray) {

				JsonObject jsonObject3 = new JsonParser().parse(jsonElement.toString()).getAsJsonObject();
				OrderForB2B b2b = new OrderForB2B();

				// 籍号
				if (jsonObject3.get("barcode").toString().replace("\"", "").equals("null")) {
					b2b.setVendorCode("");
				} else {
					b2b.setVendorCode(jsonObject3.get("barcode").toString().replace("\"", ""));
				}

				// 物料编码
				if (jsonObject3.get("itemCode").toString().replace("\"", "").equals("null")) {
					b2b.setVendorName("");
				} else {
					b2b.setVendorName(jsonObject3.get("itemCode").toString().replace("\"", ""));
				}
				// 物料版本
				if (jsonObject3.get("itemRevision").toString().replace("\"", "").equals("null")) {
					b2b.setColTaskOrPoStatus("");
				} else {
					b2b.setColTaskOrPoStatus(jsonObject3.get("itemRevision").toString().replace("\"", ""));
				}

				// 追溯类型
				if (jsonObject3.get("tracingMode").toString().replace("\"", "").equals("null")) {
					b2b.setShipmentStatus("");
				} else {
					b2b.setShipmentStatus(jsonObject3.get("tracingMode").toString().replace("\"", ""));
				}

				// ASN单号
				if (jsonObject3.get("shipmentNum").toString().replace("\"", "").equals("null")) {
					b2b.setPoSubType("");
				} else {
					b2b.setPoSubType(jsonObject3.get("shipmentNum").toString().replace("\"", ""));
				}

				// PO号
				if (jsonObject3.get("poNumber").toString().replace("\"", "").equals("null")) {
					b2b.setPoNumber("");
				} else {
					b2b.setPoNumber(jsonObject3.get("poNumber").toString().replace("\"", ""));
				}

				// 内装数量
				if (jsonObject3.get("qty").toString().replace("\"", "").equals("null")) {
					b2b.setPoHeaderId("");
				} else {
					b2b.setPoHeaderId(jsonObject3.get("qty").toString().replace("\"", ""));
				}

				// 制造日期
				if (jsonObject3.get("dateCode").toString().replace("\"", "").equals("null")) {
					b2b.setPoReleaseId("");
				} else {
					b2b.setPoReleaseId(jsonObject3.get("dateCode").toString().replace("\"", ""));
				}

				// 制造批次
				if (jsonObject3.get("lot").toString().replace("\"", "").equals("null")) {
					b2b.setPublishDate("");
				} else {
					b2b.setPublishDate(jsonObject3.get("lot").toString().replace("\"", ""));
				}

				// 打印次数
				if (jsonObject3.get("printCount").toString().replace("\"", "").equals("null")) {
					b2b.setBusinessMode("");
				} else {
					b2b.setBusinessMode(jsonObject3.get("printCount").toString().replace("\"", ""));
				}
				// 创建时间
				if (jsonObject3.get("creationDate").toString().replace("\"", "").equals("null")) {
					b2b.setCurrencyCode("");
				} else {
					b2b.setCurrencyCode(jsonObject3.get("creationDate").toString().replace("\"", ""));
				}

				// 最后修改时间
				if (jsonObject3.get("lastUpdateDate").toString().replace("\"", "").equals("null")) {
					b2b.setShipmentNum("");
				} else {
					b2b.setShipmentNum(jsonObject3.get("lastUpdateDate").toString().replace("\"", ""));
				}

				orderList.add(b2b);
			}

		}

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> element : headerFields.entrySet()) {
			if ("activityID".equals(element.getKey())) {
				System.out.println("Please log the value: " + element.getKey() + "===>>" + element.getValue().get(0));
			}
		}
		reader.close();
		connection.disconnect();
		return new Object[] { orderList, count, pageSize };
	}

}
