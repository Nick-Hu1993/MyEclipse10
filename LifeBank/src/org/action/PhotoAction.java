package org.action;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.util.ChangeTime;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class PhotoAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	private static final long serialVersionUID = 1L;
	private List<File> upload = new ArrayList<>();
	private List<String> fileNames = new ArrayList<>();
	private String fileNUM="0";
	private List<InputStream> fileList = new ArrayList<>();
	private File file1;
	private File file2;
	private File file3;
	private File file4;
	private File file5;
	private File file6;
	private File file7;
	private File file8;
	private File file9;
	public void photoUp() {
		for (int i = 1; i <= Integer.parseInt(fileNUM); i++) {
			System.out.println(request.getAttribute("file" + i));
			System.out.println(request.getParameter("filename" + i));
			upload.add((File) request.getAttribute("file" + i));
			fileNames.add(request.getParameter("filename" + i));
		}
		if (upload != null) {
			for (int i = 0; i < upload.size(); i++) {
				try {
					InputStream is = new FileInputStream(upload.get(i));
					String a = fileNames.get(i);
					System.out.println(a);
					String s[] = a.split("\\.");
					System.out.println(s);
					OutputStream os = new FileOutputStream(Utils.BASESRC+s[0]+"_"+ChangeTime.timeStamp()+"."+s[1]);
					byte buffer[] = new byte[1024];
					int count = 0;
					while ((count = is.read(buffer)) > 0) {
						os.write(buffer, 0, count);
					}
					os.close();
					is.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("message", "success");
		message.put("path",Utils.BASESRC);
		JSONArray jsonArray = JSONArray.fromObject(message);
		responseMS(jsonArray);
	}

	/**
	 * 显示图片
	 */
	public void requestPhoto() {
		
	}

	/**
	 * responseMS 响应请求 返回JsonArray
	 */
	private void responseMS(JSONArray JsonArry) {
		try {
			JSONObject json = new JSONObject();
			json.element("JsonArry", JsonArry);
			response.setContentType("text/html;charset=utf-8");
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<InputStream> getFileList() {
		return fileList;
	}

	public void setFileList(List<InputStream> fileList) {
		this.fileList = fileList;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public String getFileNUM() {
		return fileNUM;
	}

	public void setFileNUM(String fileNUM) {
		this.fileNUM = fileNUM;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public File getFile3() {
		return file3;
	}

	public void setFile3(File file3) {
		this.file3 = file3;
	}

	public File getFile4() {
		return file4;
	}

	public void setFile4(File file4) {
		this.file4 = file4;
	}

	public File getFile5() {
		return file5;
	}

	public void setFile5(File file5) {
		this.file5 = file5;
	}

	public File getFile6() {
		return file6;
	}

	public void setFile6(File file6) {
		this.file6 = file6;
	}

	public File getFile7() {
		return file7;
	}

	public void setFile7(File file7) {
		this.file7 = file7;
	}

	public File getFile8() {
		return file8;
	}

	public void setFile8(File file8) {
		this.file8 = file8;
	}

	public File getFile9() {
		return file9;
	}

	public void setFile9(File file9) {
		this.file9 = file9;
	}


}
