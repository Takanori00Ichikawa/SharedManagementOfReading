package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 読書記録(RecordOfReaDing)のモデルクラス
 * @author user
 *
 */
public class RecordOfReadingModel implements Serializable {
	
	private int id;
	private int userId;
	private Date startedDate;
	private Date finishedDate;
	private String  bookTitle;
	private String author;
	private String dateOfIssue;
	private int bookmark;
	private int totalPages;
	private String purpose;
	private String memo;
	private int isFinished;
	private int isDeleted;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	private UserModel userModel;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Date getStartedDate() {
		return this.startedDate;
	}
	
	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
	
	public Date getFinishedDate() {
		return this.finishedDate;
	}
	
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	
	public String getBookTitle() {
		return this.bookTitle;
	}
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getDateOfIssue() {
		return this.dateOfIssue;
	}
	
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	
	public int getBookmark() {
		return this.bookmark;
	}
	
	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}
	
	public int getTotalPages() {
		return this.totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public String getPurpose() {
		return this.purpose;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getMemo() {
		return this.memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public int getIsFinished() {
		return this.isFinished;
	}
	
	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}
	
	public int getIsDeleted() {
		return this.isDeleted;
	}
	
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public UserModel getUserModel() {
		return this.userModel;
	}
	
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
}
