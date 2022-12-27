package com.example.demo.service;

import java.util.List;

public interface LinkDAO {
	public List<Link> getAllLinks();
	public Link getRedirectLink(String requestLink) throws ServiceException;
	public void deleteLink(int id);
	public Link save(String newLink);
}
