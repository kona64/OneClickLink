DROP DATABASE IF EXISTS link_redirect;
CREATE DATABASE link_redirect;
USE link_redirect;

DROP TABLE IF EXISTS links;

CREATE TABLE links (
	id int NOT NULL AUTO_INCREMENT,
	request_link varchar(8) UNIQUE, 
	redirect_link varchar(2048),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;