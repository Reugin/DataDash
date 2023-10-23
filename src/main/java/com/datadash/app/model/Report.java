
package com.datadash.app.model;


import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "reports")
public class Report {
	
    private String id;

    private String userId;
    
    private String title;
    
    private String body;
    
    private String createdBy;

	public Report(String id, String userId, String title, String body, String createdBy) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.body = body;
		this.setCreatedBy(createdBy);
	}

	public Report() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
    
    

}
