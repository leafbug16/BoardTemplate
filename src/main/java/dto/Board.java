package dto;

import java.util.Date;

public class Board {
	private int postId;
	private String title;
	private String content;
	private String authorId;
	private int viewCount;
	private Date postDate;
	
	public Board(int postId, String title, String content) {
		this.postId = postId;
		this.title = title;
		this.content = content;
	}
	public Board() {}
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	@Override
	public String toString() {
		return "Board [postId=" + postId + ", authorId=" + authorId + ", title=" + title + ", content=" + content + ", viewCount=" + viewCount + ", postDate="
				+ postDate + "]";
	}
}
