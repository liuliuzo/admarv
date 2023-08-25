package com.admarv.saas.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author liuliu
 *
 */
public class FilesUtils {
	
	private static final Logger log = LoggerFactory.getLogger(FilesUtils.class);
	
	public static byte[] readFileBytes(String filePath) {
		File file = new File(filePath);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			int bytesRead = fileInputStream.read(buffer);
			if (bytesRead == buffer.length) {
				return buffer;
			}
		} catch (IOException e) {
			log.error("read File error", e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					log.error("read File error", e);
				}
			}
		}
		return null;
	}
}
