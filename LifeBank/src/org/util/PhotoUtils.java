package org.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 图片工具类
 */
public class PhotoUtils {
	/**
	 * 保存图片到服务器上
	 * 
	 * @param upload
	 * @param srcList
	 * @param fileNUM
	 * @return
	 */
	public static boolean photoUp(List<File> upload, List<String> srcList,
			String fileNUM) {
		if (upload != null) {
			for (int i = 0; i < upload.size(); i++) {
				try {
					InputStream is = new FileInputStream(upload.get(i));
					OutputStream os = new FileOutputStream(srcList.get(i));
					byte buffer[] = new byte[1024];
					int count = 0;
					while ((count = is.read(buffer)) > 0) {
						os.write(buffer, 0, count);
					}
					os.close();
					is.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * 保存单个文件到服务器上
	 * @param file1
	 * @param src
	 * @return
	 */
	public static boolean photoUp(File file1, String src) {
		if (file1 != null && src != null) {
			try {
				InputStream is = new FileInputStream(file1);
				OutputStream os = new FileOutputStream(src);
				byte buffer[] = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					os.write(buffer, 0, count);
				}
				os.close();
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

}
