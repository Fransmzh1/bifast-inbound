-- public.corebank_sequence definition

DROP SEQUENCE public.corebank_sequence;

CREATE SEQUENCE public.corebank_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
	START 1
	CACHE 1
	CYCLE;


-- public.hibernate_sequence definition

DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.kc_inboundseq definition

DROP SEQUENCE public.kc_inboundseq;

CREATE SEQUENCE public.kc_inboundseq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999999
	START 1
	CACHE 1
	CYCLE;


-- public.kc_outboundseq definition

DROP SEQUENCE public.kc_outboundseq;

CREATE SEQUENCE public.kc_outboundseq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999999
	START 1
	CACHE 1
	CYCLE;


-- public.komi_sequence definition

DROP SEQUENCE public.komi_sequence;

CREATE SEQUENCE public.komi_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999999
	START 1
	CACHE 1
	CYCLE;


-- public.table_seq_generator definition

DROP SEQUENCE public.table_seq_generator;

CREATE SEQUENCE public.table_seq_generator
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1000
	CACHE 1
	NO CYCLE;

------------------


-- public.kc_account_enquiry definition

DROP table

DROP TABLE public.kc_account_enquiry;

CREATE TABLE public.kc_account_enquiry (
	id int8 NOT NULL,
	account_no varchar(50) NULL,
	amount numeric(19, 2) NULL,
	call_status varchar(20) NULL,
	chnl_ref_id varchar(20) NULL,
	elapsed_time int8 NULL,
	error_message varchar(400) NULL,
	full_request_msg varchar(4000) NULL,
	full_response_msg varchar(4000) NULL,
	komi_trns_id varchar(20) NULL,
	orign_bank varchar(20) NULL,
	reason_code varchar(20) NULL,
	recpt_bank varchar(20) NULL,
	req_bizmsgid varchar(50) NULL,
	resp_bizmsgid varchar(50) NULL,
	response_code varchar(20) NULL,
	cihub_req_time timestamp NULL,
	CONSTRAINT kc_account_enquiry_pkey PRIMARY KEY (id)
);


-- public.kc_channel definition

DROP table

DROP TABLE public.kc_channel;

CREATE TABLE public.kc_channel (
	channel_id varchar(15) NOT NULL,
	channel_name varchar(100) NULL,
	channel_type varchar(10) NULL,
	create_dt timestamp NULL,
	daily_limit_amount numeric(19, 2) NULL,
	merchant_code varchar(255) NULL,
	modif_dt timestamp NULL,
	secret_key varchar(100) NULL,
	transaction_limit_amount numeric(19, 2) NULL,
	CONSTRAINT kc_channel_pkey PRIMARY KEY (channel_id)
);


-- public.kc_channel_transaction definition

DROP table

DROP TABLE public.kc_channel_transaction;

CREATE TABLE public.kc_channel_transaction (
	komi_trns_id varchar(20) NOT NULL,
	amount numeric(19, 2) NULL,
	call_status varchar(15) NULL,
	channel_id varchar(15) NULL,
	channel_ref_id varchar(20) NULL,
	elapsed_time int8 NULL,
	error_msg varchar(250) NULL,
	msg_name varchar(100) NULL,
	recpt_bank varchar(8) NULL,
	request_time timestamp NULL,
	response_code varchar(255) NULL,
	text_message varchar(1000) NULL,
	CONSTRAINT kc_channel_transaction_pkey PRIMARY KEY (komi_trns_id)
);


-- public.kc_corebank_transaction definition

DROP table

DROP TABLE public.kc_corebank_transaction;

CREATE TABLE public.kc_corebank_transaction (
	id int8 NOT NULL,
	credit_amount numeric(19, 2) NULL,
	cstm_account_name varchar(140) NULL,
	cstm_account_no varchar(50) NULL,
	cstm_account_type varchar(10) NULL,
	date_time varchar(50) NULL,
	debit_amount numeric(19, 2) NULL,
	fee_amount numeric(19, 2) NULL,
	komi_noref varchar(50) NULL,
	komi_trns_id varchar(50) NULL,
	orgnl_chnl_noref varchar(255) NULL,
	orgnl_date_time varchar(50) NULL,
	reason varchar(20) NULL,
	response varchar(20) NULL,
	retry_counter int4 NULL,
	transaction_type varchar(20) NULL,
	trns_date varchar(8) NULL,
	update_time timestamp NULL,
	full_text_request varchar(2000) NULL,
	CONSTRAINT kc_corebank_transaction_pkey PRIMARY KEY (id)
);


-- public.kc_credit_transfer definition

DROP table

DROP TABLE public.kc_credit_transfer;

CREATE TABLE public.kc_credit_transfer (
	id int8 NOT NULL,
	amount numeric(19, 2) NULL,
	call_status varchar(20) NULL,
	cihub_elapsed_time int8 NULL,
	cihub_req_time timestamp NULL,
	req_bizmsgid varchar(50) NULL,
	resp_bizmsgid varchar(50) NULL,
	create_dt timestamp NULL,
	creditor_acct_no varchar(50) NULL,
	creditor_acct_type varchar(10) NULL,
	creditor_id varchar(50) NULL,
	creditor_type varchar(10) NULL,
	debtor_acct_no varchar(50) NULL,
	debtor_acct_type varchar(10) NULL,
	debtor_id varchar(50) NULL,
	debtor_type varchar(10) NULL,
	error_message varchar(400) NULL,
	full_request_msg varchar(4000) NULL,
	full_response_msg varchar(4000) NULL,
	komi_trns_id varchar(50) NULL,
	last_update_dt timestamp NULL,
	msg_type varchar(50) NULL,
	orign_bank varchar(10) NULL,
	ps_counter int4 NULL,
	reason_code varchar(20) NULL,
	recpt_bank varchar(10) NULL,
	response_code varchar(20) NULL,
	reversal varchar(10) NULL,
	CONSTRAINT kc_credit_transfer_pkey PRIMARY KEY (id)
);


-- public.kc_domain_code definition

DROP table

DROP TABLE public.kc_domain_code;

CREATE TABLE public.kc_domain_code (
	id int8 NOT NULL,
	grp varchar(255) NULL,
	"key" varchar(255) NULL,
	value varchar(255) NULL,
	CONSTRAINT kc_domain_code_pkey PRIMARY KEY (id)
);


-- public.kc_fault_class definition

DROP table

DROP TABLE public.kc_fault_class;

CREATE TABLE public.kc_fault_class (
	id int8 NOT NULL,
	exception_class varchar(255) NULL,
	reason varchar(255) NULL,
	CONSTRAINT kc_fault_class_pkey PRIMARY KEY (id)
);


-- public.kc_inbound_counter definition

DROP table

DROP TABLE public.kc_inbound_counter;

CREATE TABLE public.kc_inbound_counter (
	tanggal int4 NOT NULL,
	last_number int4 NULL,
	CONSTRAINT kc_inbound_counter_pkey PRIMARY KEY (tanggal)
);


-- public.kc_message_counter definition

DROP table

DROP TABLE public.kc_message_counter;

CREATE TABLE public.kc_message_counter (
	tanggal int4 NOT NULL,
	last_number int4 NULL,
	CONSTRAINT kc_message_counter_pkey PRIMARY KEY (tanggal)
);


-- public.kc_notification_pool definition

DROP table

DROP TABLE public.kc_notification_pool;

CREATE TABLE public.kc_notification_pool (
	id int8 NOT NULL,
	ack_dt timestamp NULL,
	ack_status varchar(255) NULL,
	cre_dt timestamp NULL,
	customer_account varchar(255) NULL,
	customer_id varchar(255) NULL,
	customer_name varchar(255) NULL,
	destination varchar(255) NULL,
	distribution_channel varchar(255) NULL,
	email_addr varchar(255) NULL,
	event_ctgr varchar(255) NULL,
	event_grp varchar(255) NULL,
	message_desc varchar(255) NULL,
	notif_level varchar(255) NULL,
	phone_no varchar(255) NULL,
	ref_id varchar(255) NULL,
	urgency varchar(255) NULL,
	CONSTRAINT kc_notification_pool_pkey PRIMARY KEY (id)
);


-- public.kc_parameter definition

DROP table

DROP TABLE public.kc_parameter;

CREATE TABLE public.kc_parameter (
	id int4 NOT NULL,
	code varchar(255) NULL,
	"module" varchar(255) NULL,
	notes varchar(255) NULL,
	value varchar(255) NULL,
	CONSTRAINT kc_parameter_pkey PRIMARY KEY (id)
);


-- public.kc_payment_status definition

DROP table

DROP TABLE public.kc_payment_status;

CREATE TABLE public.kc_payment_status (
	id int8 NOT NULL,
	bizmsgid varchar(50) NULL,
	chnl_trx_id int8 NULL,
	cihub_elapsed_time int8 NULL,
	error_msg varchar(255) NULL,
	intern_ref_id varchar(255) NULL,
	komi_trns_id varchar(16) NULL,
	last_update_dt timestamp NULL,
	orgn_endtoendid varchar(50) NULL,
	request_dt timestamp NULL,
	request_full_message varchar(5000) NULL,
	response_full_message varchar(5000) NULL,
	retry_count int4 NOT NULL,
	saf varchar(255) NULL,
	status varchar(255) NULL,
	CONSTRAINT kc_payment_status_pkey PRIMARY KEY (id)
);


-- public.kc_proxy_mgmt definition

DROP table

DROP TABLE public.kc_proxy_mgmt;

CREATE TABLE public.kc_proxy_mgmt (
	id int8 NOT NULL,
	account_name varchar(100) NULL,
	account_number varchar(100) NULL,
	account_type varchar(10) NULL,
	call_status varchar(50) NULL,
	chnl_no_ref varchar(20) NULL,
	cihub_elapsed_time int8 NULL,
	customer_id varchar(100) NULL,
	customer_type varchar(10) NULL,
	display_name varchar(100) NULL,
	error_message varchar(400) NULL,
	full_request_mesg varchar(4000) NULL,
	full_response_mesg varchar(4000) NULL,
	komi_trns_id varchar(20) NULL,
	operation_type varchar(20) NULL,
	proxy_type varchar(20) NULL,
	proxy_value varchar(140) NULL,
	request_dt timestamp NULL,
	resident_status varchar(10) NULL,
	resp_status varchar(50) NULL,
	scnd_id_type varchar(10) NULL,
	scnd_value varchar(100) NULL,
	town_name varchar(10) NULL,
	CONSTRAINT kc_proxy_mgmt_pkey PRIMARY KEY (id)
);


-- public.kc_settlement definition

DROP table

DROP TABLE public.kc_settlement;

CREATE TABLE public.kc_settlement (
	id int8 NOT NULL,
	orgnl_crdt_trn_bizmsgid varchar(50) NULL,
	orign_bank varchar(20) NULL,
	recpt_bank varchar(20) NULL,
	settl_conf_bizmsgid varchar(50) NULL,
	corebank_response_id int8 NULL,
	crdt_account_no varchar(35) NULL,
	crdt_bank_account_no varchar(35) NULL,
	dbtr_account_no varchar(35) NULL,
	dbtr_bank_account_no varchar(35) NULL,
	full_message varchar(5000) NULL,
	receive_date timestamp NULL,
	CONSTRAINT kc_settlement_pkey PRIMARY KEY (id)
);


-- public.kc_status_reason definition

DROP table

DROP TABLE public.kc_status_reason;

CREATE TABLE public.kc_status_reason (
	status_reason_code varchar(20) NOT NULL,
	description varchar(200) NULL,
	status_code varchar(20) NULL,
	CONSTRAINT kc_status_reason_pkey PRIMARY KEY (status_reason_code)
);
