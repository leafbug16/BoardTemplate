package dto;

import java.util.Date;

public class Board {
	private int boardId;
	private String title;
	private String content;
	private String author;
	private int viewCnt;
	private Date createdAt;
	private int commentCnt;
	
	public Board(int boardId, String title, String content) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
	}
	public Board() {}
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", title=" + title + ", content=" + content + ", author=" + author + ", viewCnt=" + viewCnt + ", createdAt="
				+ createdAt + ", commentCnt=" + commentCnt + "]";
	}
}
