-- Drop user first if they exist
DROP USER if exists 'antoanbaomat'@'%' ;

-- Now create user with prop privileges
CREATE USER 'antoanbaomat'@'%' IDENTIFIED BY 'antoanbaomat';

GRANT ALL PRIVILEGES ON * . * TO 'antoanbaomat'@'%';