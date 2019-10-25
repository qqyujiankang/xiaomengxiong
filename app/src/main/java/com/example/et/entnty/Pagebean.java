package com.example.et.entnty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 分页列表
 * @author Administrator
 * @param <T>
 */
public class Pagebean<T> implements Serializable {
	
	private static final long serialVersionUID = 798982558989288744L;
	private Integer code;//消息code
	private String msg;//消息

	private int count;//数量
	private HashMap<String, Object> stringMap;

	public HashMap<String, Object> getStringMap() {
		return stringMap;
	}

	public void setStringMap(HashMap<String, Object> stringMap) {
		this.stringMap = stringMap;
	}



	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}



	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private List<T> list;            // 要返回的某一页的记录列表
	private List<T> listOther;            // 要返回的某一页的记录列表
	private Integer allRow;          // 总记录数
	private Integer totalPage;       // 总页数
	private Integer currentPage;     // 当前页
	private Integer pageSize;        // 每页记录数
	private T typeClass;
	private String type;
	private String longitude;
	private String latitude;

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public List<T> getListOther() {
		return listOther;
	}
	public void setListOther(List<T> listOther) {
		this.listOther = listOther;
	}
	public Integer getAllRow() {
		return allRow;
	}
	public void setAllRow(Integer allRow) {
		this.allRow = allRow;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public T getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(T typeClass) {
		this.typeClass = typeClass;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
