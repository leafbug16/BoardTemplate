package dto;

import java.util.Date;

public class Comment {
	private int commentId;
	private int boardId;
	private String comment;
	private String commenter;
	private Date createdAt;
	
	public Comment(int boardId, String comment, String commenter) {
		super();
		this.boardId = boardId;
		this.comment = comment;
		this.commenter = commenter;
	}
	public Comment(String comment, String commenter, int commentId) {
		super();
		this.comment = comment;
		this.commenter = commenter;
		this.commentId = commentId;
	}
	public Comment() {}
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", boardId=" + boardId + ", comment=" + comment + ", commenter=" + commenter + ", createdAt=" + createdAt + "]";
	}
}
