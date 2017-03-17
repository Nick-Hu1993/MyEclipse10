package org.dao;

import java.io.File;
import java.util.List;

import org.model.Photo;

public interface PhotoDao {
	/**
	 * 新增多个图片
	 */
	public boolean insert(Long userid, Integer type, Long foreignId,
			List<String> srcList);
	/**
	 * 新增单个图片
	 */
	public boolean insert(Long userid, Integer type, String url);
	/**
	 * 查看图片
	 */
	public List<Photo> getPhotos(Long userid, Long foreignId, Integer type);
	/**
	 * 获取对应的地址
	 * @param userid
	 * @param foreignId
	 * @param type 1为头像，2为话题，3为评论
	 */
	public List<String> getUrlList(Long userid, Long foreignId, Integer type);

	/**
	 * 删除用户时同时删除图片
	 */
	public boolean delete(Long userid);
	/**
	 * 删除用户之前上传的头像
	 * 
	 * @param userid
	 *            用户Id
	 * @param type
	 *            类型,0表示头像，2、3分别表示话题和评论的图片
	 */
	public boolean delete(Long userid, Integer type);
	/**
	 * 删除图片数据，用于删除评论时删除图片
	 * 
	 * @param foreignId
	 *            外键
	 */
	public boolean delByForeignId(Long foreignId);
	/**
	 * 删除图片数据，用于删除评论时删除图片
	 */
	public boolean delByUrl(String url);
	/**
	 * 获取评论对应的所有图片地址
	 */
	public List<String> getUrlByForeignId(Long foreignId);

	/**
	 * 获取话题对应的所有图片地址，包含它对应的评论的所有图片地址
	 */
	public List<String> getTopAllUrl(Long foreignId);

	/**
	 * 获取用户上传的所有图片
	 */
	public List<Photo> getPhotos(Long userid);

	/**
	 * 获取单个图片的url，暂时用于获取头像
	 * 
	 * @param userid
	 * @param type
	 * @return
	 */
	public Photo getPhoto(Long userid, Integer type);

	/**
	 * 获取已经上传的头像--用于删除
	 * 
	 * @param userid
	 * @param type
	 */
	public List<Photo> getUsedHead(Long userid, Integer type);
	/**
	 * 获取用户上传的所有图片地址
	 */
	public List<String> getUrlList(Long userid);

}
