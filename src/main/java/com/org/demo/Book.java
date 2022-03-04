package com.org.demo;

import javax.persistence.*;
@Entity
@Table(name = "Books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "published")
	private boolean published;
	@Column(name = "author")
	private String author;
	public Book() {
	}
	public Book(String title, String description, boolean published ,String author) {
		this.title = title;
		this.description = description;
		this.published = published;
		this.author = author;
	  }
	   public String getAuthor() {
		return author;
	   }
	  public void setAuthor(String author) {
		this.author = author;
	  }
	  public long getId() {
		return id;
	  }
	  public String getTitle() {
		return title;
	  }
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public boolean isPublished() {
			return published;
		}
		public void setPublished(boolean isPublished) {
			this.published = isPublished;
		}
		@Override
		public String toString() {
			return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + " , author=" +author + "]";
		}
	}
