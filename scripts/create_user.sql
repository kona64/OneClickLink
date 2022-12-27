drop user linkRedirectApplication@localhost;
flush privileges;
create user linkRedirectApplication@localhost identified by 'password';
GRANT ALL PRIVILEGES ON link_redirect.* TO 'linkRedirectApplication'@'localhost';