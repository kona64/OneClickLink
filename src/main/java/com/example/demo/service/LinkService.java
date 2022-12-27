package com.example.demo.service;

import java.util.List;

public interface LinkService {
	public String getRedirectLinkAndDelete(String requestLink) throws ServiceException;
	public Link createLink(String redirectLink);
	public List<Link> getAllLinks();
	public void deleteLink(int id);
}
