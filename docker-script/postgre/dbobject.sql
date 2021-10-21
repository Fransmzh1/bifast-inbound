-- public.corebank_sequence definition

-- DROP SEQUENCE public.corebank_sequence;

CREATE SEQUENCE public.corebank_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	CYCLE;


-- public.hibernate_sequence definition

-- DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- public.table_seq_generator definition

-- DROP SEQUENCE public.table_seq_generator;

CREATE SEQUENCE public.table_seq_generator
	INCREMENT BY 1
	MINVALUE 5000
	MAXVALUE 9223372036854775807
	START 5000
	CACHE 1
	NO CYCLE;

-- public.account_enquiry definition

-- Drop table

-- DROP TABLE public.account_enquiry;

CREATE TABLE public.account_enquiry (
	id int8 NOT NULL,
	account_no varchar(50) NULL,
	amount numeric(19, 2) NULL,
	bizmsgid varchar(50) NULL,
	call_status varchar(255) NULL,
	cihub_elapsed_time int8 NULL,
	cihub_req_time timestamp NULL,
	error_message varchar(400) NULL,
	full_request_msg varchar(4000) NULL,
	full_response_msg varchar(4000) NULL,
	intr_ref_id varchar(20) NULL,
	komi_trns_id varchar(255) NULL,
	orign_bank varchar(20) NULL,
	recpt_bank varchar(20) NULL,
	resp_bizmsgid varchar(50) NULL,
	response_status varchar(20) NULL,
	chnl_trx_id int8 NULL,
	CONSTRAINT account_enquiry_pkey PRIMARY KEY (id)
);


-- public.channel definition

-- Drop table

-- DROP TABLE public.channel;

CREATE TABLE public.channel (
	channel_id varchar(255) NOT NULL,
	channel_name varchar(100) NULL,
	channel_type varchar(10) NULL,
	create_dt timestamp NULL,
	daily_limit_amount numeric(19, 2) NULL,
	modif_dt timestamp NULL,
	secret_key varchar(100) NULL,
	transaction_limit_amount numeric(19, 2) NULL,
	merchant_code varchar(255) NULL,
	CONSTRAINT channel_pkey PRIMARY KEY (channel_id)
);


-- public.channel_transaction definition

-- Drop table

-- DROP TABLE public.channel_transaction;

CREATE TABLE public.channel_transaction (
	id int8 NOT NULL,
	amount numeric(19, 2) NULL,
	call_status varchar(15) NULL,
	channel_id varchar(15) NULL,
	channel_ref_id varchar(20) NULL,
	creditor_account_number varchar(35) NULL,
	debtor_account_number varchar(35) NULL,
	elapsed_time int8 NULL,
	error_msg varchar(250) NULL,
	komi_trns_id varchar(8) NULL,
	msg_name varchar(100) NULL,
	recpt_bank varchar(8) NULL,
	request_time timestamp NULL,
	response_code varchar(255) NULL,
	CONSTRAINT channel_transaction_pkey PRIMARY KEY (id)
);


-- public.corebank_transaction definition

-- Drop table

-- DROP TABLE public.corebank_transaction;

CREATE TABLE public.corebank_transaction (
	id int8 NOT NULL,
	addt_info varchar(255) NULL,
	cb_trns_id int4 NULL,
	channel_id varchar(255) NULL,
	credit_amount numeric(19, 2) NULL,
	creditor_bank varchar(255) NULL,
	cstm_account_name varchar(255) NULL,
	cstm_account_no varchar(255) NULL,
	cstm_account_type varchar(255) NULL,
	date_time varchar(255) NULL,
	debit_amount numeric(19, 2) NULL,
	debtor_bank varchar(255) NULL,
	fee_amount numeric(19, 2) NULL,
	komi_trns_id varchar(255) NULL,
	orgnl_cb_trns_id int4 NULL,
	orgnl_date_time varchar(255) NULL,
	status varchar(255) NULL,
	terminal_id varchar(255) NULL,
	transaction_type varchar(255) NULL,
	trns_dt timestamp NULL,
	channel_ref_id varchar(255) NULL,
	chnl_trx_id int8 NULL,
	transaction_id int8 NOT NULL,
	CONSTRAINT corebank_transaction_pkey PRIMARY KEY (id)
);


-- public.credit_transfer definition

-- Drop table

-- DROP TABLE public.credit_transfer;

CREATE TABLE public.credit_transfer (
	id int8 NOT NULL,
	settlconf_bizmsgid varchar(255) NULL,
	amount numeric(19, 2) NULL,
	call_status varchar(20) NULL,
	cihub_elapsed_time int8 NULL,
	cihub_req_time timestamp NULL,
	cihub_resp_time timestamp NULL,
	crdttrn_req_bizmsgid varchar(255) NULL,
	crdttrn_resp_bizmsgid varchar(255) NULL,
	cre_dt timestamp NULL,
	creditor_acct_no varchar(255) NULL,
	creditor_acct_type varchar(255) NULL,
	creditor_id varchar(255) NULL,
	creditor_type varchar(255) NULL,
	debtor_acct_no varchar(255) NULL,
	debtor_acct_type varchar(255) NULL,
	debtor_id varchar(255) NULL,
	debtor_type varchar(255) NULL,
	full_request_msg varchar(4000) NULL,
	full_response_msg varchar(4000) NULL,
	komi_trns_id varchar(255) NULL,
	msg_type varchar(255) NULL,
	orign_bank varchar(255) NULL,
	recpt_bank varchar(255) NULL,
	resp_status varchar(20) NULL,
	reversal varchar(255) NULL,
	chnl_trx_id int8 NULL,
	intr_ref_id varchar(255) NULL,
	CONSTRAINT credit_transfer_pkey PRIMARY KEY (id)
);


-- public.domain_code definition

-- Drop table

-- DROP TABLE public.domain_code;

CREATE TABLE public.domain_code (
	id int8 NOT NULL,
	grp varchar(255) NULL,
	"key" varchar(255) NULL,
	value varchar(255) NULL,
	CONSTRAINT domain_code_pkey PRIMARY KEY (id)
);


-- public.fault_class definition

-- Drop table

-- DROP TABLE public.fault_class;

CREATE TABLE public.fault_class (
	id int8 NOT NULL,
	exception_class varchar(255) NULL,
	reason varchar(255) NULL,
	CONSTRAINT fault_class_pkey PRIMARY KEY (id)
);


-- public.fi_credit_transfer definition

-- Drop table

-- DROP TABLE public.fi_credit_transfer;

CREATE TABLE public.fi_credit_transfer (
	id int8 NOT NULL,
	settlconf_bizmsgid varchar(50) NULL,
	amount numeric(19, 2) NULL,
	call_status varchar(20) NULL,
	chnl_trx_id int8 NULL,
	cihub_elapsed_time int8 NULL,
	cihub_req_time timestamp NULL,
	cihub_resp_time timestamp NULL,
	cre_dt timestamp NULL,
	credit_bic varchar(255) NULL,
	debtor_bic varchar(255) NULL,
	full_request_msg varchar(4000) NULL,
	full_response_msg varchar(4000) NULL,
	intr_ref_id varchar(255) NULL,
	orign_bank varchar(255) NULL,
	req_bizmsgid varchar(50) NULL,
	resp_bizmsgid varchar(50) NULL,
	response_status varchar(20) NULL,
	saf varchar(20) NULL,
	saf_counter int4 NULL,
	CONSTRAINT fi_credit_transfer_pkey PRIMARY KEY (id)
);


-- public.inbound_counter definition

-- Drop table

-- DROP TABLE public.inbound_counter;

CREATE TABLE public.inbound_counter (
	tanggal int4 NOT NULL,
	last_number int4 NULL,
	CONSTRAINT inbound_counter_pkey PRIMARY KEY (tanggal)
);


-- public.inbound_message definition

-- Drop table

-- DROP TABLE public.inbound_message;

CREATE TABLE public.inbound_message (
	id int8 NOT NULL,
	bizmsgid varchar(50) NULL,
	cihub_request_time timestamp NULL,
	cihub_response_time timestamp NULL,
	copydupl varchar(20) NULL,
	errormsg varchar(500) NULL,
	orgn_bank varchar(20) NULL,
	full_request_msg varchar(4000) NULL,
	full_response_msg varchar(4000) NULL,
	msg_name varchar(50) NULL,
	resp_bizmsgidr varchar(50) NULL,
	resp_reject_msg varchar(255) NULL,
	resp_status varchar(255) NULL,
	CONSTRAINT inbound_message_pkey PRIMARY KEY (id)
);


-- public.m_bic definition

-- Drop table

-- DROP TABLE public.m_bic;

CREATE TABLE public.m_bic (
	bank_code varchar(255) NOT NULL,
	bank_name varchar(255) NULL,
	bic_code varchar(255) NULL,
	change_who varchar(255) NULL,
	created_date timestamp NULL,
	last_update_date timestamp NULL,
	CONSTRAINT m_bic_pkey PRIMARY KEY (bank_code)
);


-- public.message_counter definition

-- Drop table

-- DROP TABLE public.message_counter;

CREATE TABLE public.message_counter (
	tanggal int8 NOT NULL,
	last_number int8 NULL,
	CONSTRAINT message_counter_pkey PRIMARY KEY (tanggal)
);


-- public.notification_pool definition

-- Drop table

-- DROP TABLE public.notification_pool;

CREATE TABLE public.notification_pool (
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
	CONSTRAINT notification_pool_pkey PRIMARY KEY (id)
);


-- public.outbound_parameter definition

-- Drop table

-- DROP TABLE public.outbound_parameter;

CREATE TABLE public.outbound_parameter (
	code varchar(255) NOT NULL,
	notes varchar(255) NULL,
	value varchar(255) NULL,
	CONSTRAINT outbound_parameter_pkey PRIMARY KEY (code)
);


-- public."parameter" definition

-- Drop table

-- DROP TABLE public."parameter";

CREATE TABLE public."parameter" (
	id int4 NOT NULL,
	code varchar(255) NULL,
	"module" varchar(255) NULL,
	notes varchar(255) NULL,
	value varchar(255) NULL,
	CONSTRAINT parameter_pkey PRIMARY KEY (id)
);


-- public.payment_status definition

-- Drop table

-- DROP TABLE public.payment_status;

CREATE TABLE public.payment_status (
	id int8 NOT NULL,
	bizmsgid varchar(50) NULL,
	error_msg varchar(255) NULL,
	intern_ref_id varchar(255) NULL,
	orgn_endtoendid varchar(50) NULL,
	request_dt timestamp NULL,
	request_full_message varchar(5000) NULL,
	response_full_message varchar(5000) NULL,
	retry_count int4 NOT NULL,
	saf varchar(255) NULL,
	status varchar(255) NULL,
	chnl_trx_id int8 NULL,
	cihub_elapsed_time int8 NULL,
	komi_trns_id varchar(16) NULL,
	last_update_dt timestamp NULL,
	response_dt timestamp NULL,
	CONSTRAINT payment_status_pkey PRIMARY KEY (id)
);


-- public.proxy_message definition

-- Drop table

-- DROP TABLE public.proxy_message;

CREATE TABLE public.proxy_message (
	id int8 NOT NULL,
	account_name varchar(100) NULL,
	account_number varchar(255) NULL,
	account_type varchar(255) NULL,
	call_status varchar(50) NULL,
	customer_id varchar(255) NULL,
	customer_type varchar(255) NULL,
	display_name varchar(100) NULL,
	error_message varchar(400) NULL,
	full_request_mesg varchar(4000) NULL,
	full_response_mesg varchar(4000) NULL,
	intrn_ref_id varchar(255) NULL,
	operation_type varchar(255) NULL,
	proxy_type varchar(255) NULL,
	proxy_value varchar(255) NULL,
	request_dt timestamp NULL,
	resident_status varchar(255) NULL,
	resp_status varchar(50) NULL,
	response_dt timestamp NULL,
	scnd_id_type varchar(255) NULL,
	scnd_value varchar(255) NULL,
	town_name varchar(255) NULL,
	chnl_trx_id int8 NULL,
	cihub_elapsed_time int8 NULL,
	CONSTRAINT proxy_message_pkey PRIMARY KEY (id)
);


-- public.settlement definition

-- Drop table

-- DROP TABLE public.settlement;

CREATE TABLE public.settlement (
	id int8 NOT NULL,
	crdt_account_no varchar(35) NULL,
	crdt_bank_account_no varchar(35) NULL,
	dbtr_account_no varchar(35) NULL,
	dbtr_bank_account_no varchar(35) NULL,
	full_message varchar(5000) NULL,
	orgnl_crdt_trn_bizmsgid varchar(50) NULL,
	orign_bank varchar(20) NULL,
	receive_date timestamp NULL,
	recpt_bank varchar(20) NULL,
	settl_conf_bizmsgid varchar(50) NULL,
	corebank_response_id int8 NULL,
	CONSTRAINT settlement_pkey PRIMARY KEY (id)
);


-- public.status_reason definition

-- Drop table

-- DROP TABLE public.status_reason;

CREATE TABLE public.status_reason (
	status_reason_code varchar(255) NOT NULL,
	description varchar(255) NULL,
	status_code varchar(255) NULL,
	CONSTRAINT status_reason_pkey PRIMARY KEY (status_reason_code)
);
