package com.spirng.DAO;

import java.io.File;
import java.io.FileInputStream;

public class ImageFile_DTO {
	private String filePath, imgName;
	private File basicPath;
	private FileInputStream fis;
	
	public String getFilePath() {return filePath;}
	public void setFilePath(String filePath) {this.filePath = filePath;}
	
	public String getImgName() {return imgName;}	
	public void setImgName(String imgName) {this.imgName = imgName;}
	
	public File getBasicPath() {return basicPath;}
	public void setBasicPath(File basicPath) {this.basicPath = basicPath;}
	
	public FileInputStream getFis() {return fis;}
	public void setFis(FileInputStream fis) {this.fis = fis;}		
}
