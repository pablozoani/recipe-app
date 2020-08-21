# mysql -u root -h 127.0.0.1 -p

CREATE SCHEMA recipe_dev;
CREATE SCHEMA recipe_prod;

CREATE USER recipe_dev_user;
CREATE USER recipe_prod_user;

ALTER USER 'recipe_dev_user'@'%' IDENTIFIED BY 'some_password';
ALTER USER 'recipe_prod_user'@'%' IDENTIFIED BY 'some_password';

GRANT INSERT, UPDATE, DELETE, SELECT ON recipe_dev.* TO 'recipe_dev_user'@'%';
GRANT INSERT, UPDATE, DELETE, SELECT ON recipe_prod.* TO 'recipe_prod_user'@'%';