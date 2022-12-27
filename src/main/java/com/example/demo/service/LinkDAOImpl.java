package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LinkDAOImpl implements LinkDAO {
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Link getRedirectLink(String requestLink) throws ServiceException {
		String redirectLinkQuery = "select rl from Link rl where rl.requestLink = ?1";
		TypedQuery<Link> query = entityManager.createQuery(redirectLinkQuery, Link.class);
		query.setParameter(1, requestLink);
		try {
			Link redirectLink = query.getSingleResult();
			return redirectLink;
		}
		catch (Exception  e) {
			throw new ServiceException();
		}
	}
	
	@Override
	public void deleteLink(int id) {
		String deleteLinkQuery = "delete from Link where id = ?1";
		Query query = entityManager.createQuery(deleteLinkQuery);
		query.setParameter(1, id);
		query.executeUpdate();
	}
	

	@Override
	public Link save(String newLink) {
		Link link = new Link();
		link.setRedirectLink(newLink);
		Link persistedLink = entityManager.merge(link);
		return persistedLink;
	}

	@Override
	public List<Link> getAllLinks() {
		String getLinksQuery = "select rl from Link rl";
		TypedQuery<Link> query = entityManager.createQuery(getLinksQuery, Link.class);
		return query.getResultList();
	}
}
