package com.depthspace.notifications.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="NOTIFICATIONS")
public class NotificationsVO implements Serializable{
	
	@Id
	@Column(name="NOTE_ID",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noteId;
	
	@Column(name="MEM_ID",updatable = false)
	private Integer memId;
	
	@Column(name="NOTE_TYPE",updatable = false)
	private String noteType;
	
	@Column(name="NOTE_CONTENT",updatable = false)
	private String noteContent;
	
	@CreationTimestamp
	@Column(name="NOTE_CREATED",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date noteCreated;
	
	@Column(name="NOTE_READ")
	private byte noteRead;
	
	public NotificationsVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NotificationsVO(Integer noteId, Integer memId, String noteType, String noteContent, Timestamp noteCreated,
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

	public void setNoteCreated(Timestamp noteCreated) {
		this.noteCreated = noteCreated;
	}

	public byte getNoteRead() {
		return noteRead;
	}

	public void setNoteRead(byte noteRead) {
		this.noteRead = noteRead;
	}
	
	
}