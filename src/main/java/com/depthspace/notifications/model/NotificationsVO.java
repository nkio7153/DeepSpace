package com.depthspace.notifications.model;

import java.io.Serializable;
import java.util.Date;

public class NotificationsVO implements Serializable{
	
	private Integer noteId;
	private Integer memId;
	private String noteType;
	private String noteContent;
	private Date noteCreated;
	private byte noteRead;
	
	public NotificationsVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NotificationsVO(Integer noteId, Integer memId, String noteType, String noteContent, Date noteCreated,
			byte noteRead) {
		super();
		this.noteId = noteId;
		this.memId = memId;
		this.noteType = noteType;
		this.noteContent = noteContent;
		this.noteCreated = noteCreated;
		this.noteRead = noteRead;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Date getNoteCreated() {
		return noteCreated;
	}

	public void setNoteCreated(Date noteCreated) {
		this.noteCreated = noteCreated;
	}

	public byte getNoteRead() {
		return noteRead;
	}

	public void setNoteRead(byte noteRead) {
		this.noteRead = noteRead;
	}
	
	
}