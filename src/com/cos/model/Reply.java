package com.cos.model;

import java.sql.Timestamp;

public class Reply {
	// 필요할 때 필드 만들어놓기
private ResponseData responseData = new ResponseData();
private int id;
private int commentId;
private int userId;
private String content;
private Timestamp createDate;
private User user = new User();
public ResponseData getResponseData() {
	return responseData;
}
public void setResponseData(ResponseData responseData) {
	this.responseData = responseData;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCommentId() {
	return commentId;
}
public void setCommentId(int commentId) {
	this.commentId = commentId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Timestamp getCreateDate() {
	return createDate;
}
public void setCreateDate(Timestamp createDate) {
	this.createDate = createDate;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

}
