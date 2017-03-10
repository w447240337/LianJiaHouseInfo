package com;

public class lianJiaHouse {

	private int id;// 编号

	private String title;// 标题

	private int price;// 价格

	private String createdate;// 创建日期

	private String updatedate;// 更新日期

	private String category;// 分类

	private String longitude;// 经纬度

	private int area;// 面积
	
	private String state;// 状态

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getArea() {
		return area;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public String getCreatedate() {
		return createdate;
	}
	
	public String getUpdatedate() {
		return updatedate;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getState() {
		return state;
	}
}
