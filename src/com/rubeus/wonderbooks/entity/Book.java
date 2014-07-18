package com.rubeus.wonderbooks.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
	private String title;
	private String subtitle;
	private String description;
	private String authors;
	private String publisher;
	private String publishedDate;
	private double averageRating;
	private int ratingCount;
	private String infoLink;
	private String thumbnail;
	private String isbn;
	private int pageCount;
	
	public Book(Parcel source){
		title = source.readString();
		subtitle = source.readString();
		description = source.readString();
		authors = source.readString();
		publisher = source.readString();
		publishedDate = source.readString();
		averageRating = source.readDouble();
		ratingCount = source.readInt();
		infoLink = source.readString();
		thumbnail = source.readString();
		isbn = source.readString();
		pageCount = source.readInt();
	}
	
	public Book(){}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public String getInfoLink() {
		return infoLink;
	}
	public void setInfoLink(String infoLink) {
		this.infoLink = infoLink;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {		
		dest.writeString(title);
		dest.writeString(subtitle);
		dest.writeString(description);
		dest.writeString(authors);
		dest.writeString(publisher);
		dest.writeString(publishedDate);
		dest.writeDouble(averageRating);		
		dest.writeInt(ratingCount);
		dest.writeString(infoLink);
		dest.writeString(thumbnail);
		dest.writeString(isbn);
		dest.writeInt(pageCount);
	}
	
	public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {

	    @Override
	    public Book createFromParcel(Parcel source) {
	        return new Book(source);
	    }

	    @Override
	    public Book[] newArray(int size) {
	        return new Book[size];
	    }

	};
}
