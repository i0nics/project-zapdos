package com.zapdos.main;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Sprite {
	private static final double SCALE = MainApplication.SCALE;
	private GImage image;
	private double width;
	private double height;
	private String fileName;
	public double centerX;
	public double centerY;
	
	// Default constructor
	public Sprite(String fileName, Double x, Double y, Double scale) {
		
		if (fileName.startsWith("T")) fileName = "sprites/" + fileName;
		image = new GImage (fileName, x, y); 
		width = image.getWidth() * scale;
		height = image.getHeight() * scale;
		image.setSize(width, height);
		
		
		this.fileName = fileName;
		
		centerX = x + width / 2;
		centerY = y + height / 2;
	}
	
	public Sprite(String fileName, Double x, Double y) {
		this(fileName, x, y, SCALE);
	}
	
	public double getHeight() {
		return image.getHeight();
	}
	
	public double getWidth() {
		return image.getWidth();
	}
	
	public double getX() {
		return image.getX();
	}
	
	public double getY() {
		return image.getY();
	}
	
	public void setX(double x) {
		image.setLocation(x, getY());
	}
	
	public void setY(double y) {
		image.setLocation(getX(), y);
	}
	
	public void setImage(String fileName) {
		if (fileName.startsWith("T")) fileName = "sprites/" + fileName;
		image.setImage(fileName);
		this.fileName = fileName;
	}
		
	public void setLocation(Double x, Double y) {
		image.setLocation(x, y);
		centerX = x + width / 2;
		centerY = y + height / 2;
	}
	
	public void move(Double x, Double y) {
		image.move(x, y);
		centerX = x + width / 2;
		centerY = y + height / 2;
	}
	
	public void setSize(Double w, Double h) {
		image.setSize(w, h);
	}
	
	public GImage getImg() {
		return image;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public GRectangle getBounds() {
		return image.getBounds();
	}
	
	public Double getTop() {
		return centerY - image.getHeight()/2;
	}
	
	public Double getBottom() {
		return centerY + image.getHeight()/2;
	}
	
	public Double getLeft() {
		return centerX - image.getWidth()/2;
	}
	
	public Double getRight() {
		return centerX + image.getWidth()/2;
	}
	
	public void setTop(Double top) {
		centerY = top + image.getHeight()/2;
	}
	
	public void setBottom(Double bottom) {
		centerY = bottom - image.getHeight()/2;
	}
	
	public void setLeft(Double left) {
		centerX = left + image.getWidth()/2;
	}
	
	public void setRight(Double right) {
		centerX = right - image.getWidth()/2;
	}
	
}