package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	LinkDAO linkDAO;
	
	@Override
	public String getRedirectLinkAndDelete(String requestLink) throws ServiceException {
		Link link = linkDAO.getRedirectLink(requestLink);
		linkDAO.deleteLink(link.getId());
		return link.getRedirectLink();
	}
	
	@Override
	public Link createLink(String requestLink) {
		return linkDAO.save(requestLink);
	}

	@Override
	public List<Link> getAllLinks() {
		return linkDAO.getAllLinks();
	}

	@Override
	public void deleteLink(int id) {
		linkDAO.deleteLink(id);
	}
	
}
