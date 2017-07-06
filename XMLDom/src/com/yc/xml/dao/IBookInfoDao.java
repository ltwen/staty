package com.yc.xml.dao;

import java.util.List;

import com.yc.xmldom.entity.BookInfo;

public interface IBookInfoDao {
	/**
	 * 返回所有的书本信息的方法信息来源为books.xml
	 * @return
	 */
	public List<BookInfo> find() ;
}
