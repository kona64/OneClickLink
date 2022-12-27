package com.example.demo.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

@Entity
@Table(name="links")
public class Link {
	public Link() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="request_link")
	private String requestLink;
	
	@Column(name="redirect_link")
	private String redirectLink;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRequestLink() {
		return requestLink;
	}

	public void setRequestLink(String requestLink) {
		this.requestLink = requestLink;
	}

	public String getRedirectLink() {
		return redirectLink;
	}

	public void setRedirectLink(String redirectLink) {
		this.redirectLink = redirectLink;
	}
	
	/* 
	 * the following code is called after the DAO persists a LINK object and generates a unique requestLink from the ID.
	 */
	@PostPersist
	public void setRequestLinkFromId() {
		requestLink = IntegerToURLMapper.convert(id);
	}
}
