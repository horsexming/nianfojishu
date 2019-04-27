/*
 * Copyright 2002-2007 Jeremias Maerki or contributors to Barcode4J, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.task.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.eps.EPSCanvasProvider;
import org.krysalis.barcode4j.output.svg.SVGCanvasProvider;
import org.krysalis.barcode4j.tools.MimeTypes;

import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.avalon.framework.logger.ConsoleLogger;
import org.apache.avalon.framework.logger.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BarcodeServlet extends ActionSupport {

	// /** Parameter name for the message */
	// public static final String BARCODE_MSG = "msg";
	// /** Parameter name for the barcode type */
	// public static final String BARCODE_TYPE = "type";
	// /** Parameter name for the barcode height */
	// public static final String BARCODE_HEIGHT = "height";
	// /** Parameter name for the module width */
	// public static final String BARCODE_MODULE_WIDTH = "mw";
	// /** Parameter name for the wide factor */
	// public static final String BARCODE_WIDE_FACTOR = "wf";
	// /** Parameter name for the quiet zone */
	// public static final String BARCODE_QUIET_ZONE = "qz";
	// /** Parameter name for the human-readable placement */
	// public static final String BARCODE_HUMAN_READABLE_POS = "hrp";
	// /** Parameter name for the output format */
	// public static final String BARCODE_FORMAT = "fmt";
	// /** Parameter name for the image resolution (for bitmaps) */
	// public static final String BARCODE_IMAGE_RESOLUTION = "res";
	// /** Parameter name for the grayscale or b/w image (for bitmaps) */
	// public static final String BARCODE_IMAGE_GRAYSCALE = "gray";
	// /** Parameter name for the font size of the human readable display */
	// public static final String BARCODE_HUMAN_READABLE_SIZE = "hrsize";
	// /** Parameter name for the font name of the human readable display */
	// public static final String BARCODE_HUMAN_READABLE_FONT = "hrfont";
	// /** Parameter name for the pattern to format the human readable message
	// */
	// public static final String BARCODE_HUMAN_READABLE_PATTERN = "hrpattern";

	private String msg;// 条码内容(默认0123456789)
	private String type;// 条码类型(code128/code39)
	private String height;// 高度
	private String moduleWidth;// 宽度
	private String wideFactor;
	private String quietZone;
	private String hrp;
	private String format;// 条码格式(默认MIME_JPEG)
	private String res;
	private String gray;
	private String hrsize;
	private String hrfont;
	private String hrpatten;

	private transient Logger log = new ConsoleLogger(ConsoleLogger.LEVEL_INFO);

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest,
	 *      HttpServletResponse)
	 */
	@Override
	public String execute() {
		try {
			format = MimeTypes.expandFormat(format);
			if (format == null)
				format = MimeTypes.MIME_JPEG;

			int orientation = 0;

			DefaultConfiguration cfg = new DefaultConfiguration("barcode");
			// Get type
			if (type == null)
				type = "code39";
			DefaultConfiguration child = new DefaultConfiguration(type);
			cfg.addChild(child);
			// Get additional attributes
			DefaultConfiguration attr;
			if (height != null) {
				attr = new DefaultConfiguration("height");
				attr.setValue(height);
				child.addChild(attr);
			}
			if (moduleWidth != null) {
				attr = new DefaultConfiguration("module-width");
				attr.setValue(moduleWidth);
				child.addChild(attr);
			}
			if (wideFactor != null) {
				attr = new DefaultConfiguration("wide-factor");
				attr.setValue(wideFactor);
				child.addChild(attr);
			}
			if (quietZone != null) {
				attr = new DefaultConfiguration("quiet-zone");
				if (quietZone.startsWith("disable")) {
					attr.setAttribute("enabled", "false");
				} else {
					attr.setValue(quietZone);
				}
				child.addChild(attr);
			}

			// creating human readable configuration according to the new
			// Barcode
			// Element Mappings
			// where the human-readable has children for font name, font size,
			// placement and
			// custom pattern.
			String humanReadablePosition = hrp;
			String pattern = hrpatten;
			String humanReadableSize = hrsize;
			String humanReadableFont = hrfont;

			if (!((humanReadablePosition == null) && (pattern == null)
					&& (humanReadableSize == null) && (humanReadableFont == null))) {
				attr = new DefaultConfiguration("human-readable");

				DefaultConfiguration subAttr;
				if (pattern != null) {
					subAttr = new DefaultConfiguration("pattern");
					subAttr.setValue(pattern);
					attr.addChild(subAttr);
				}
				if (humanReadableSize != null) {
					subAttr = new DefaultConfiguration("font-size");
					subAttr.setValue(humanReadableSize);
					attr.addChild(subAttr);
				}
				if (humanReadableFont != null) {
					subAttr = new DefaultConfiguration("font-name");
					subAttr.setValue(humanReadableFont);
					attr.addChild(subAttr);
				}
				if (humanReadablePosition != null) {
					subAttr = new DefaultConfiguration("placement");
					subAttr.setValue(humanReadablePosition);
					attr.addChild(subAttr);
				}

				child.addChild(attr);
			}

			if (msg == null)
				msg = "0123456789";

			BarcodeUtil util = BarcodeUtil.getInstance();
			BarcodeGenerator gen = util.createBarcodeGenerator(cfg);

			ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
			try {
				if (format.equals(MimeTypes.MIME_SVG)) {
					// Create Barcode and render it to SVG
					SVGCanvasProvider svg = new SVGCanvasProvider(false,
							orientation);
					gen.generateBarcode(svg, msg);
					org.w3c.dom.DocumentFragment frag = svg.getDOMFragment();

					// Serialize SVG barcode
					TransformerFactory factory = TransformerFactory
							.newInstance();
					Transformer trans = factory.newTransformer();
					Source src = new javax.xml.transform.dom.DOMSource(frag);
					javax.xml.transform.Result res = new javax.xml.transform.stream.StreamResult(
							bout);
					trans.transform(src, res);
				} else if (format.equals(MimeTypes.MIME_EPS)) {
					EPSCanvasProvider eps = new EPSCanvasProvider(bout,
							orientation);
					gen.generateBarcode(eps, msg);
					eps.finish();
				} else {
					String resText = res;
					int resolution = 300; // dpi
					if (resText != null) {
						resolution = Integer.parseInt(resText);
					}
					if (resolution > 2400) {
						throw new IllegalArgumentException(
								"Resolutions above 2400dpi are not allowed");
					}
					if (resolution < 10) {
						throw new IllegalArgumentException(
								"Minimum resolution must be 10dpi");
					}
					BitmapCanvasProvider bitmap = ("true"
							.equalsIgnoreCase(gray) ? new BitmapCanvasProvider(
							bout, format, resolution,
							BufferedImage.TYPE_BYTE_GRAY, true, orientation)
							: new BitmapCanvasProvider(bout, format,
									resolution, BufferedImage.TYPE_BYTE_BINARY,
									false, orientation));
					gen.generateBarcode(bitmap, msg);
					bitmap.finish();
				}
			} finally {
				bout.close();
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(format);
			response.setContentLength(bout.size());
			response.getOutputStream().write(bout.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			log.error("Error while generating barcode", e);
		} catch (Throwable t) {
			log.error("Error while generating barcode", t);
		}

		return null;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMw() {
		return moduleWidth;
	}

	public void setMw(String mw) {
		this.moduleWidth = mw;
	}

	public String getWf() {
		return wideFactor;
	}

	public void setWf(String wf) {
		this.wideFactor = wf;
	}

	public String getQz() {
		return quietZone;
	}

	public void setQz(String qz) {
		this.quietZone = qz;
	}

	public String getHrp() {
		return hrp;
	}

	public void setHrp(String hrp) {
		this.hrp = hrp;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getGray() {
		return gray;
	}

	public void setGray(String gray) {
		this.gray = gray;
	}

	public String getHrsize() {
		return hrsize;
	}

	public void setHrsize(String hrsize) {
		this.hrsize = hrsize;
	}

	public String getHrfont() {
		return hrfont;
	}

	public void setHrfont(String hrfont) {
		this.hrfont = hrfont;
	}

	public String getHrpatten() {
		return hrpatten;
	}

	public void setHrpatten(String hrpatten) {
		this.hrpatten = hrpatten;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
}
