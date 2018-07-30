SET FOREIGN_KEY_CHECKS=0;

DELETE FROM acl_class;
DELETE FROM acl_entry;
DELETE FROM acl_object_identity;
DELETE FROM acl_sid;
DELETE FROM authoritie;
DELETE FROM persistent_logins;
DELETE FROM token;
DELETE FROM users;

SET FOREIGN_KEY_CHECKS=1;