DROP table IF EXISTS transreport_user CASCADE;
CREATE TABLE IF NOT EXISTS transreport_user (
  id bigint not null,
  phone_number varchar(20) not null,
  user_name varchar(100),
  register_time timestamp without time zone, 
  login_time timestamp without time zone, 
  points bigint,
  CONSTRAINT user_pkey PRIMARY KEY(id)
);

CREATE SEQUENCE seq_user 
   INCREMENT 50
   START 1
   MINVALUE 1
   MAXVALUE 9223372036854775807
   CACHE 1
   OWNED BY transreport_user.id;



DROP table IF EXISTS message_log CASCADE;
CREATE TABLE message_log(
	id int8 NOT NULL,
	message_body varchar(255) NULL,
	send_date timestamp NULL,
	send_to_phone_num int8 NULL,
	send_to int8 NULL,
	send_by int8 NULL,
	CONSTRAINT message_log_pkey PRIMARY KEY(id)
);

CREATE SEQUENCE seq_message_log
   INCREMENT 50
   START 1
   MINVALUE 1
   MAXVALUE 9223372036854775807
   CACHE 1
   OWNED BY message_log.id;
