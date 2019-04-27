package com.task.action.xinxi;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.swetake.util.Qrcode;
import com.task.Dao.TotalDao;
import com.task.entity.Users;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.util.HttpRequest;
import com.task.util.HttpResponse;
import com.task.util.MKUtil;
import com.task.util.Util;
  
public class TwoDimensionCode extends ActionSupport{ 
	
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
     private Users user;
     private ArchiveUnarchiverAplt archiveUnarchiverAplt;
     private String content;//条码内容
     String filename=Util.getDateTime("yyyy-MM-DD");
     private String name  ;
    public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     */  
    public void encoderQRCode(String content, String imgPath) {  
        this.encoderQRCode(content, imgPath, "png", 7);  
    }  
      
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param output 输出流 
     */  
    public void encoderQRCode(String content, OutputStream output) {  
        this.encoderQRCode(content, output, "png", 7);  
    }  
      
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     * @param imgType 图片类型 
     */  
    public void encoderQRCode(String content, String imgPath, String imgType) {  
        this.encoderQRCode(content, imgPath, imgType, 7);  
    }  
      
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param output 输出流 
     * @param imgType 图片类型 
     */  
    public void encoderQRCode(String content, OutputStream output, String imgType) {  
        this.encoderQRCode(content, output, imgType, 7);  
    }  
  
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     */  
    public void encoderQRCode(String content, String imgPath, String imgType, int size) {  
        try {  
            BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);  
              
            File imgFile = new File(imgPath);  
            // 生成二维码QRCode图片  
            ImageIO.write(bufImg, imgType, imgFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param output 输出流 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     */  
    public void encoderQRCode(String content, OutputStream output, String imgType, int size) {  
        try {  
            BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);  
            // 生成二维码QRCode图片  
            ImageIO.write(bufImg, imgType, output);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 生成二维码(QRCode)图片的公共方法 
     * @param content 存储内容 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     * @return 
     */  
    private BufferedImage qRCodeCommon(String content, String imgType, int size) {  
        BufferedImage bufImg = null;  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            qrcodeHandler.setQrcodeVersion(size);  
            // 获得内容的字节数组，设置编码格式  
            byte[] contentBytes = content.getBytes("utf-8");  
            // 图片尺寸  
            int imgSize = 67 + 12 * (size - 1);  
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            // 设置背景颜色  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, imgSize, imgSize);  
  
            // 设定图像颜色> BLACK  
            gs.setColor(Color.BLACK);  
            // 设置偏移量，不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容> 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 800) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
            }  
            gs.dispose();  
            bufImg.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bufImg;  
    }  
      
    /** 
     * 解析二维码（QRCode） 
     * @param imgPath 图片路径 
     * @return 
     */  
    public String decoderQRCode(String imgPath) {  
        // QRCode 二维码图片的文件  
        File imageFile = new File(imgPath);  
        BufferedImage bufImg = null;  
        String content = null;  
        try {  
            bufImg = ImageIO.read(imageFile);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");   
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }  
      
    /** 
     * 解析二维码（QRCode） 
     * @param input 输入流 
     * @return 
     */  
    public String decoderQRCode(InputStream input) {  
        BufferedImage bufImg = null;  
        String content = null;  
        try {  
            bufImg = ImageIO.read(input);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");   
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }  
    public String test(){
    	 String imgPath=ServletActionContext
			.getServletContext().getRealPath("/upload/user")+ "/"+user.getCode()+".png";
    	 File newFile=new File(imgPath);
    	 if(newFile.isDirectory()==false){
    		 newFile.delete();
    	 }
 	 String encoderContent = "姓名：" + user.getName()+"\n工号： "+user.getCode()+ "\n部门 :"+user.getDept()+"\n职务:"+user.getDuty();  
     TwoDimensionCode handler = new TwoDimensionCode();  
     handler.encoderQRCode(encoderContent, imgPath, "png");  
  
     return "saveOK";
    }
    public String creatBarcode(){
    	
    	 String imgPath=ServletActionContext
			.getServletContext().getRealPath("/upload/user")+ "/"+filename+".png";
 	 File newFile=new File(imgPath);
 	 if(newFile.isDirectory()==false){
 		 newFile.delete();
 	 }
	  System.out.println("======"+imgPath); 	 
	 String encoderContent = content;  
  TwoDimensionCode handler = new TwoDimensionCode();  
  handler.encoderQRCode(encoderContent, imgPath, "png"); 
    	return "creatBarcode";
    }
    public String piliang(){    	 
    	//String hql="from Users where code in('022','147','034','123','078','416','430','439','440','066','069','062','072','063','109','438','097','101','104','103','102','107','076','056','098','178','180','319','358','145')";
    	//String hql="from Users where code in('148','145','114','211','212')";
    	String hql="from Users where dept='总成班'";
    	List<Users> list=totalDao.query(hql);
        for(int i=0;i<list.size();i++){
        	 Users user=list.get(i); 
        	 String imgPath=ServletActionContext
 			.getServletContext().getRealPath("/upload/user")+ "/"+user.getCode()+".png";
		  	 File newFile=new File(imgPath);
		  	 if(newFile.isDirectory()==false){
		  		 newFile.delete();
		  	 }
        	 String encoderContent = "姓名：" + user.getName()+"\n工号： "+user.getCode()+ "\n部门 :"+user.getDept()+"\n职务:"+user.getDuty();  
             TwoDimensionCode handler = new TwoDimensionCode();  
             handler.encoderQRCode(encoderContent, imgPath, "png");  
        }
        return "piliang";
    }
    
	/**
	 * @author Li_Cong
	 * @return 档案申请生成的二维码
	 */
	public String danganQRcode(String inCode) {
		String filead = "/upload/file/daQRcode";
		if (inCode != null && !"".equals(inCode)) {
			String path = ServletActionContext.getServletContext()
					.getRealPath(filead);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			String imgPath = ServletActionContext.getServletContext()
					.getRealPath(filead)
					+ "/" + inCode + ".png";
			File newFile = new File(imgPath);
			if (newFile.isDirectory() == false) {
				newFile.delete();
			}
			TwoDimensionCode handler = new TwoDimensionCode();
			handler.encoderQRCode(inCode, imgPath, "png",1);
			return "true";
		}
		return "false";
	}
	/**
	 * @author 
	 * @return 智能诊断系统链接
	 */
	public String danganQRcode1() {
		String filead = "/upload/file/znzdxtcode";
		String inCode ="IntelligentDiagnosisAction_initadd.action?id=1135";
		if (inCode != null && !"".equals(inCode)) {
			String path = ServletActionContext.getServletContext()
			.getRealPath(filead);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			name = Util.getDateTime("yyyyMMddHHmmss");
			String imgPath = ServletActionContext.getServletContext()
			.getRealPath(filead)
			+ "/" + name+ ".png";
		HttpServletRequest 	request	= ServletActionContext.getRequest();
		String path1 = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path1 + "/";
			inCode =basePath+ inCode;
			File newFile = new File(imgPath);
			if (newFile.isDirectory() == false) {
				newFile.delete();
			}
			TwoDimensionCode handler = new TwoDimensionCode();
			handler.encoderQRCode(inCode, imgPath, "png",1);
			return "saveOK";
		}
		return "false";
	}
    
    
    
    
    
    
    /*public static void main(String[] args) {  
      //  String imgPath = "D:/TDDOWNLOAD/Michael_QRCode.png";  
    	  //String imgPath = "/upload/user/Michael_QRCode.png";
    	  String imgPath=ServletActionContext
			.getServletContext().getRealPath("/upload/user")+ "/Michael_QRCode.png";
    	  System.out.println("======"+imgPath);
        String encoderContent = "Hello 大大、小小,welcome to QRCode!" + "\nMyblog [ http://sjsky.iteye.com ]" + "\nEMail [ sjsky007@gmail.com ]";  
        TwoDimensionCode handler = new TwoDimensionCode();  
        handler.encoderQRCode(encoderContent, imgPath, "png");  
//      try {  
//          OutputStream output = new FileOutputStream(imgPath);  
//          handler.encoderQRCode(content, output);  
//      } catch (Exception e) {  
//          e.printStackTrace();  
//      }  
        System.out.println("========encoder success");  
          
          
        String decoderContent = handler.decoderQRCode(imgPath);  
        System.out.println("解析结果如下：");  
        System.out.println(decoderContent);  
        System.out.println("========decoder success!!!");  
    }*/

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ArchiveUnarchiverAplt getArchiveUnarchiverAplt() {
		return archiveUnarchiverAplt;
	}
	public void setArchiveUnarchiverAplt(ArchiveUnarchiverAplt archiveUnarchiverAplt) {
		this.archiveUnarchiverAplt = archiveUnarchiverAplt;
	}
}  