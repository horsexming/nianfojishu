package com.task.action.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.struts2.ServletActionContext;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.systemfile.SystemFileServer;
import com.task.entity.Users;
import com.task.util.Util;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class attachmentPreviewAction extends ActionSupport {
	private SystemFileServer systemFileServer;
	private String name;
	private String code;
	private String errorMessage;
	private String url;
	private String dfile;
	private String dpath;
    private String Rename;//下载重命名
	private String wmark;//页面水印
	

	private String FilePath;
	private Boolean Refresh = false;
	// e.g. "/pdf/web/2.pptx";

	// office 格式宏
	private static final int wdFormatPDF = 17;
	private static final int xlTypePDF = 0;
	private static final int ppSaveAsPDF = 32;

	public String show() {
		return "show";
	}

	public String error() {
		return "show";
	}

    public String getRename() {
        return Rename;
    }

    public void setRename(String rename) {
        Rename = rename;
    }

	public String getWmark() {
		return wmark;
	}

	public void setWmark(String wmark) {
		this.wmark = wmark;
	}

	/*
	 * 统一调用方法 img2PDF use apache_pdfbox 2.07 office2pdf use JAVA-COM JACOB 1.18
	 * 
	 * @author fy 2017-8-12 10:13:42
	 */
	public String execute() {
		Users users = Util.getLoginUser();
		name = users.getName();
		code = users.getCode();
		String filepath = FilePath;
		String realwordPath = ServletActionContext.getServletContext()
				.getRealPath(filepath);
		String fileName = filepath.substring(filepath.lastIndexOf("/"),
				filepath.length());
		String filepathWithoutFilename = filepath.substring(0, filepath
				.lastIndexOf("/"));
		systemFileServer.addBaomiLog(filepath.substring(filepath
				.lastIndexOf("/") + 1, filepath.length()));
		String pdfPath = "/upload/pdf_view/" + filepathWithoutFilename;
		String realpdfPath = ServletActionContext.getServletContext()
				.getRealPath(pdfPath);
		File dir = new File(realpdfPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		realpdfPath = realpdfPath + "\\" + fileName + ".pdf";
		dfile=fileName;
		dpath=filepathWithoutFilename;
		/* Check pdf exist */
		File realpdfPathdir = new File(realpdfPath);
		if (realpdfPathdir.exists() && Refresh == false) {
			return "show";
		}
		String inputFile = realwordPath;
		String pdfFile = realpdfPath;

		String suffix = getFileSufix(inputFile);
		File file = new File(inputFile);
		if (!file.exists()) {
			System.out.println("文件不存在！");
			return "error";
		}
		if (suffix.equalsIgnoreCase("pdf")) {
//			return addControlled2pdf(inputFile, pdfFile);
			 return "show";
		}
		try {
			if (suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx")
					|| suffix.equalsIgnoreCase("txt")) {
				return word2PDF(inputFile, pdfFile);
			} else if (suffix.equalsIgnoreCase("ppt")
					|| suffix.equalsIgnoreCase("pptx")) {
				return ppt2PDF(inputFile, pdfFile);
			} else if (suffix.equalsIgnoreCase("xls")
					|| suffix.equalsIgnoreCase("xlsx")) {
				return excel2PDF(inputFile, pdfFile);
			} else if (suffix.equalsIgnoreCase("jpg")
					|| suffix.equalsIgnoreCase("bmp")
					|| suffix.equalsIgnoreCase("png")
					|| suffix.equalsIgnoreCase("gif")
					|| suffix.equalsIgnoreCase("jpeg")
					|| suffix.equalsIgnoreCase("tif")
					|| suffix.equalsIgnoreCase("tiff")) {
				return img2PDF(inputFile, pdfFile);
			} else {
//			errorMessage = "文件格式不支持转换!请联系管理员!";

				return "downloadatt";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "downloadatt";
		}
	}

	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1).toLowerCase();
	}

	public String word2PDF(String inputFile, String pdfFile) {
		try {
			//https://www.cnblogs.com/FredTang/p/4253293.html  //WTF
			// 打开word应用程序
			ActiveXComponent app = new ActiveXComponent("Word.Application");// WPS坑爹啊!
			// 老版本注册表
			// WPS.Application
			// 新版本KWPS.Application。最后用的Word2010
			// 设置word不可见
			app.setProperty("Visible", false);
			// 获得word中所有打开的文档,返回Documents对象
			Dispatch docs = app.getProperty("Documents").toDispatch();
			// 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
			Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true)

			.toDispatch();
			// 调用Document对象的SaveAs方法，将文档保存为pdf格式
			/*
			 * Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF
			 * //word保存为pdf格式宏，值为17 );
			 */
			Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF // word保存为pdf格式宏，值为17
					);
			// 关闭文档
			Dispatch.call(doc, "Close", false);
			// 关闭word应用程序
			app.invoke("Quit", 0);
			return "show";
		} catch (Exception e) {
			return "error";
		}
	}

	// excel转不了可能是excel选项中 信任问题或者Dcomcnfg Dcom组件服务问题
	// http://www.bubuko.com/infodetail-1320787.html
	public String excel2PDF(String inputFile, String pdfFile) {
		try {
            Util.changeExcelPrintsize(inputFile,inputFile,true);
			ActiveXComponent app = new ActiveXComponent("Excel.Application");
			app.setProperty("Visible", false);
			Dispatch excels = app.getProperty("Workbooks").toDispatch();
			Dispatch excel = Dispatch.call(excels, "Open", inputFile, false,
					true).toDispatch();
			Dispatch.call(excel, "ExportAsFixedFormat", xlTypePDF, pdfFile);
			Dispatch.call(excel, "Close", false);
			app.invoke("Quit");
			return "show";
		} catch (Exception e) {
			return "error";
		}

	}
	public String test(){

		HttpServletRequest request =ServletActionContext.getRequest();

//		String icon_fileRealPath = ServletActionContext
//				.getServletContext().getRealPath(
//						"/upload/file/yz/icon_cl.png");
//		// 生成加章文件
//		Util.markPDFByIcon(icon_fileRealPath, ServletActionContext.getServletContext().getRealPath(
//				"\\pdf\\web/123.pdf"), ServletActionContext.getServletContext().getRealPath(
//				"\\pdf\\web/1235.pdf"),1.7f,1.14f,2.5f,9f);
//
//		icon_fileRealPath = ServletActionContext
//				.getServletContext().getRealPath(
//						"/upload/file/yz/icon_sk.png");
//
//		Util.markPDFByIcon(icon_fileRealPath, ServletActionContext.getServletContext().getRealPath(
//				"\\pdf\\web/1235.pdf"), ServletActionContext.getServletContext().getRealPath(
//				"\\pdf\\web/1235.pdf"),1.7f,1.14f,2.5f,9f);


//        try {
//            if (Util.changeExcelPrintsize("/pdf/web/1234.xlsx","/pdf/web/12345.xlsx",false))
//            errorMessage="true";
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
        return null;
    }

	public String ppt2PDF(String inputFile, String pdfFile) {
		try {
			ActiveXComponent app = new ActiveXComponent(
					"PowerPoint.Application");
			// app.setProperty("Visible", msofalse);
			Dispatch ppts = app.getProperty("Presentations").toDispatch();

			Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true,// ReadOnly
					true,// Untitled指定文件是否有标题
					false// WithWindow指定文件是否可见
					).toDispatch();

			Dispatch.call(ppt, "SaveAs", pdfFile, ppSaveAsPDF);

			Dispatch.call(ppt, "Close");

			app.invoke("Quit");
			return "show";
		} catch (Exception e) {
			return "error";
		}
	}

	// api:https://pdfbox.apache.org/docs/2.0.7/javadocs/
	public String img2PDF(String imagePath, String pdfPath) {

		if (!pdfPath.endsWith(".pdf")) {
			System.err
					.println("Last argument must be the destination .pdf file");
			System.exit(1);
		}

		PDDocument doc = new PDDocument();
		try {

			// page.setRotation(90);

			// PDRectangle docpage=new PDRectangle();
			// docpage.createRetranslatedRectangle();

			// PDPage page = new PDPage();

			// 弃用/改为自适应
			// doc.addPage(page);

			// createFromFile is the easiest way with an image file
			// if you already have the image in a BufferedImage,
			// call LosslessFactory.createFromImage() instead

			// e.g.1
			PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath,
					doc);
			float pdx = pdImage.getWidth();
			float pdy = pdImage.getHeight();

			PDRectangle pdrectangle = new PDRectangle(pdx, pdy);
			PDPage page = new PDPage(pdrectangle);// PDRectangle.A2);

			doc.addPage(page);

			// e.g.2
			// BufferedImage bimg = ImageIO.read(new File(imagePath));
			// PDImageXObject pdImage =
			// LosslessFactory.createFromImage(doc,bimg);

			PDPageContentStream contents = new PDPageContentStream(doc, page);

			// draw the image at full size at (x=20, y=20)
			// contents.drawImage(pdImage, 20, -20);
			float scale = 1.0f;

			// if (pdImage.getWidth() > 610) {
			// scale = pdImage.getWidth() / 610.0f;
			// scale = 1 / scale;
			// }

			// contents.drawImage(pdImage, 0, 800 - pdImage.getHeight(), pdImage
			// .getWidth()
			// * scale, pdImage.getHeight() * scale);

			contents.drawImage(pdImage, 0, 0, pdImage.getWidth(), pdImage
					.getHeight());

			// to draw the image at half size at (x=20, y=20) use
			// contents.drawImage(pdImage, 20, 20, pdImage.getWidth() / 2,
			// pdImage.getHeight() / 2);

			addWaterMark2pdf(doc, pdx, pdy);
			contents.close();
			doc.save(pdfPath);
			return "show";
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				doc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void addWaterMark2pdf(PDDocument doc, float pageWidth,
			float pageHeight) throws IOException {
		try {
			for (PDPage page2 : doc.getPages()) {
				float x = 50.0f;
				float y = 50.0f;
				for (int i = 0; i < 3; i++) {

					for (int j = 0; j < 3; j++) {
						PDPageContentStream cs = new PDPageContentStream(doc,
								page2, AppendMode.APPEND, true, true);
						// String ts = "PEBS" +"-"+ name + "-" + code;
						String ts = "PEBS";
						// default font
						// PDFont font = PDType1Font.HELVETICA_BOLD;
						/* 微软雅黑加粗.ttf */
						String ttfFile = ServletActionContext
								.getServletContext().getRealPath(
										"/pdf/web/msyhbd.ttf");
						PDFont font = PDType0Font.load(doc, new File(ttfFile));
						float fontSize = ((pageWidth + pageHeight) / 2 * 0.1f);
						PDResources resources = page2.getResources();
						PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
						r0.setNonStrokingAlphaConstant(0.4f);
						cs.setGraphicsStateParameters(r0);
						cs.setNonStrokingColor(170, 170, 170);//
						cs.beginText();
						cs.setFont(font, fontSize);
						/* rotate */
						Matrix matrix = Matrix.getTranslateInstance(x, y);
						matrix.rotate(0.5);

						cs.setTextMatrix(matrix);
						cs.showText(ts);
						cs.endText();
						cs.close();

						y += pageHeight / 2;
					}

					x += pageWidth / 2;
					y = 50.0f;
				}

			}

		} catch (Exception e) {

		}

	}
	
	/***
	 * 添加受控
	 * @param inputpath 输入PDF路径
	 * @param outpdfpath 输出PDF路径
	 * @return
	 */
	public String addControlled2pdf(String inputpath, String outpdfpath) {
		try {
			File file = new File(inputpath);
			PDDocument doc = PDDocument.load(file);
			doc = PDDocument.load(file);
			for (PDPage page2 : doc.getPages()) {
				String Path = "/Controlled.png";
				String ControlledImgPath = ServletActionContext
						.getServletContext().getRealPath(Path);
				PDImageXObject ControlledImg = PDImageXObject.createFromFile(
						ControlledImgPath, doc);
				PDPageContentStream contents = new PDPageContentStream(doc,
						page2, AppendMode.APPEND, false);
				contents.drawImage(ControlledImg, 10, 700, 210f, 70f);
				contents.close();
			}
			doc.save(outpdfpath);
		} catch (IOException e) {
			return e.toString();
		}
		return "show";
	}

	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getRefresh() {
		return Refresh;
	}

	public void setRefresh(Boolean refresh) {
		Refresh = refresh;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public SystemFileServer getSystemFileServer() {
		return systemFileServer;
	}

	public void setSystemFileServer(SystemFileServer systemFileServer) {
		this.systemFileServer = systemFileServer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDfile() {
		return dfile;
	}

	public void setDfile(String dfile) {
		this.dfile = dfile;
	}

	public String getDpath() {
		return dpath;
	}

	public void setDpath(String dpath) {
		this.dpath = dpath;
	}

}
