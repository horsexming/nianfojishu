package com.task.util;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class Pages extends Component {
	private String cpage;
	private String total;
	private String url;

	private String styleClass;
	private String theme;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Pages(ValueStack arg0) {
		super(arg0);
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		try {
			StringBuilder str = new StringBuilder();
			boolean isValid = true;

			// 从ValueStack中取出数值
			if (isValid) {
				if (total.startsWith("%{") && total.endsWith("}")) {
					total = total.substring(2, total.length() - 1);
					total = (String) this.getStack().findValue(total);
					isValid = total == null ? false : true;
				} else {
					isValid = false;
				}
			}
			if (isValid) {
				if (cpage.startsWith("%{") && cpage.endsWith("}")) {
					cpage = cpage.substring(2, cpage.length() - 1);
					cpage = (String) this.getStack().findValue(cpage);
					isValid = cpage == null ? false : true;
				} else {
					isValid = false;
				}
			}
			if (isValid) {
				if (url.startsWith("%{") && url.endsWith("}")) {
					url = url.substring(2, url.length() - 1);
					url = (String) this.getStack().findValue(url);
					isValid = url == null ? false : true;
				} else {
					isValid = false;
				}
			}

			if (isValid) {
				Integer cpageInt = Integer.valueOf(cpage);
				str.append("<span ");
				if (styleClass != null) {
					str.append(" class='" + styleClass + "'>");
				} else {
					str.append(">");
				}

				// 文本样式
				if (theme == null || "number".equals(theme)) { // theme="text"样式
					// 当前页与总页数相等
					if (cpage.equals(total)) {
						// 如果total = 1，则无需分页，显示“[第1页] [共1页]”
						if ("1".equals(total)) {
							str.append("[第 " + cpage + " 页]");
							str.append(" [共 " + total + " 页]");
						} else {
							// 到达最后一页,显示“[首页] [上一页] [末页]”
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=1&total=" + total);
							} else {
								str.append("?cpage=1&total=" + total);
							}
							str.append("'>[首页]</a> <a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + (cpageInt - 1)
										+ "&total=" + total);
							} else {
								str.append("?cpage=" + (cpageInt - 1)
										+ "&total=" + total);
							}
							str.append("'>[上一页]</a> <a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + total + "&total="
										+ total);
							} else {
								str.append("?cpage=" + total + "&total="
										+ total);
							}
							str.append("'>[末页]</a>");
						}
					} else {
						// 当前页与总页数不相同
						if ("1".equals(cpage)) {
							// 第一页，显示“[首页] [下一页] [末页]”
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=1&total=" + total);
							} else {
								str.append("?cpage=1&total=" + total);
							}
							str.append("'>[首页]</a> <a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + (cpageInt + 1)
										+ "&total=" + total);
							} else {
								str.append("?cpage=" + (cpageInt + 1)
										+ "&total=" + total);
							}
							str.append("'>[下一页]</a> <a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + total + "&total="
										+ total);
							} else {
								str.append("?cpage=" + total + "&total="
										+ total);
							}
							str.append("'>[末页]</a>");
						} else {
							// 不是第一页，显示“[首页] [上一页] [下一页] [末页]”
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=1&total=" + total);
							} else {
								str.append("?cpage=1&total=" + total);
							}
							str.append("'>[首页]</a> <a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + (cpageInt - 1)
										+ "&total=" + total);
							} else {
								str.append("?cpage=" + (cpageInt - 1)
										+ "&total=" + total);
							}
							str.append("'>[上一页]</a> <a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + (cpageInt + 1)
										+ "&total=" + total);
							} else {
								str.append("?cpage=" + (cpageInt + 1)
										+ "&total=" + total);
							}
							str.append("'>[下一页]</a> <a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + total + "&total="
										+ total);
							} else {
								str.append("?cpage=" + total + "&total="
										+ total);
							}
							str.append("'>[末页]</a>");
						}
					}
					String newUrl = url;
					if (url.indexOf("?") > 0) {
						newUrl += "&total=" + total;
					} else {
						newUrl += "?total=" + total;
					}
					str
							.append("<input style='width:40px;' id='cpage_wlbhcf' value='"
									+ cpageInt
									+ "' "
									+ "onkeyup=\"javascript:if(this.value==''||this.value=='0'){this.value='"
									+ cpageInt
									+ "'};"
									+ "if(parseInt(this.value)>parseInt("
									+ total
									+ ")){this.value='"
									+ total
									+ "'};\">"
									+ "<input type='button' value='跳转' style='width:40px;' "
									+ "onclick=javascript:location.href='"
									+ newUrl
									+ "&cpage='+document.getElementById('cpage_wlbhcf').value;>");
					//(计:"	+ total + "*15="+(Integer.parseInt(total)*15)+")
				} else if ("text".equals(theme)) { // theme="number"的数字样式
					// [1 2 3 4 5 6 7 8 9 10
					// > >>]
					Integer totalInt = Integer.valueOf(total);

					// 如果只有一页，则无需分页
					str.append("[ ");
					if (totalInt == 1) {
						str.append("<strong>1</strong> ");
					} else {
						// 计算一共分几组
						int group = (totalInt - 1) / 10 + 1;
						// 当前第几组
						int cgroup = (cpageInt - 1) / 10 + 1;

						if (cgroup > 1) {
							// 当前不是第一组，要显示“<< <”
							// <<：返回前一组第一页
							// <：返回前一页
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + ((cgroup - 2) * 10 + 1)
										+ "&total=" + total);
							} else {
								str.append("?cpage=" + ((cgroup - 2) * 10 + 1)
										+ "&total=" + total);
							}
							str.append("'>&laquo</a> ");
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + (cpageInt - 1)
										+ "&total=" + total);
							} else {
								str.append("?cpage=" + (cpageInt - 1)
										+ "&total=" + total);
							}
							str.append("'>&lt;</a> ");
						}
						// 10个为一组显示
						for (int i = (cgroup - 1) * 10 + 1; i <= totalInt
								&& i <= cgroup * 10; i++) {
							if (cpageInt == i) { // 当前页要加粗显示
								str.append("<strong>");
							}
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + i + "&total=" + total);
							} else {
								str.append("?cpage=" + i + "&total=" + total);
							}
							str.append("'>" + i + "</a> ");
							if (cpageInt == i) {
								str.append("</strong>");
							}
						}
						// 如果多于1组并且不是最后一组，显示“> >>”
						if (group > 1 && cgroup != group) {
							// >>：返回下一组最后一页
							// >：返回下一页
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str.append("&cpage=" + (cpageInt + 1)
										+ "&total=" + total);
							} else {
								str.append("?cpage=" + (cpageInt + 1)
										+ "&total=" + total);
							}
							str.append("'>&gt;</a> ");
							str.append("<a href='");
							str.append(url);
							if (url.indexOf("?") > 0) {
								str
										.append("&cpage="
												+ ((cgroup * 10 + 10) > totalInt ? totalInt
														: (cgroup * 10 + 10))
												+ "&total=" + total);
							} else {
								str
										.append("?cpage="
												+ ((cgroup * 10 + 10) > totalInt ? totalInt
														: (cgroup * 10 + 10))
												+ "&total=" + total);
							}
							str.append("'>&raquo</a> ");
						}
					}
					str.append("]");
				}
				str.append("</span>");
			}

			writer.write(str.toString());

		} catch (IOException ex) {
			Logger.getLogger(Pages.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;

	}
}
