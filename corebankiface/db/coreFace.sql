PGDMP                         y            coreFace    10.10    10.10 %    -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            0           1262    41464    coreFace    DATABASE     �   CREATE DATABASE "coreFace" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_Indonesia.1252' LC_CTYPE = 'English_Indonesia.1252';
    DROP DATABASE "coreFace";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            1           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            2           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    41599    account    TABLE     h  CREATE TABLE public.account (
    id bigint NOT NULL,
    account_no character varying(255),
    account_type character varying(255),
    additional_info character varying(255),
    amount numeric(19,2),
    creditor_account_number character varying(255),
    creditor_account_type character varying(255),
    creditor_id character varying(255),
    creditor_name character varying(255),
    creditor_resident_status character varying(255),
    creditor_status character varying(255),
    creditor_town_name character varying(255),
    creditor_type character varying(255),
    intr_ref_id character varying(255)
);
    DROP TABLE public.account;
       public         postgres    false    3            �            1259    41607    account_enquiry    TABLE     �  CREATE TABLE public.account_enquiry (
    id bigint NOT NULL,
    account_no character varying(255),
    amount numeric(19,2),
    cre_dt timestamp without time zone,
    direction character varying(255),
    intr_ref_id character varying(255),
    orign_bank character varying(255),
    recpt_bank character varying(255),
    req_biz_msg_id character varying(255),
    resp_status character varying(255)
);
 #   DROP TABLE public.account_enquiry;
       public         postgres    false    3            �            1259    41615    credit_transfer    TABLE     �  CREATE TABLE public.credit_transfer (
    id bigint NOT NULL,
    settlconf_bizmsgid character varying(255),
    acctenq_req_bismsgid character varying(255),
    acct_enqr_resp_bizmsgid character varying(255),
    acct_enqr_resp_status character varying(255),
    amount numeric(19,2),
    crdttrn_req_bizmsgid character varying(255),
    crdttrn_resp_bizmsgid character varying(255),
    crdttrn_resp_status character varying(255),
    cre_dt timestamp without time zone,
    creditor_acct_no character varying(255),
    debtor_acct_no character varying(255),
    direction character varying(255),
    intr_ref_id character varying(255),
    msg_type character varying(255),
    orign_bank character varying(255),
    recpt_bank character varying(255)
);
 #   DROP TABLE public.credit_transfer;
       public         postgres    false    3            �            1259    41623    credit_transfer_request    TABLE     T  CREATE TABLE public.credit_transfer_request (
    id bigint NOT NULL,
    account_number character varying(255),
    account_type character varying(255),
    amount character varying(255),
    creditor_name character varying(255),
    payment_info character varying(255),
    request_time date,
    transaction_id character varying(255)
);
 +   DROP TABLE public.credit_transfer_request;
       public         postgres    false    3            �            1259    41631    debit_transfer_request    TABLE     i  CREATE TABLE public.debit_transfer_request (
    id bigint NOT NULL,
    account_number character varying(255),
    account_type character varying(255),
    amount character varying(255),
    debitor_name character varying(255),
    payment_info character varying(255),
    request_time timestamp without time zone,
    transaction_id character varying(255)
);
 *   DROP TABLE public.debit_transfer_request;
       public         postgres    false    3            �            1259    41510    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    3            �            1259    41639    inbound_message    TABLE     �  CREATE TABLE public.inbound_message (
    id bigint NOT NULL,
    bizmsgid character varying(255),
    bizsvc character varying(255),
    copy_msg character varying(255),
    orgn_bank character varying(255),
    full_msg character varying(5000),
    msg_name character varying(255),
    dupl character varying(255),
    receive_dt timestamp without time zone,
    resp_msg character varying(5000)
);
 #   DROP TABLE public.inbound_message;
       public         postgres    false    3            �            1259    41647    message_counter    TABLE     _   CREATE TABLE public.message_counter (
    tanggal integer NOT NULL,
    last_number integer
);
 #   DROP TABLE public.message_counter;
       public         postgres    false    3            �            1259    41652    outbound_message    TABLE     �  CREATE TABLE public.outbound_message (
    id bigint NOT NULL,
    bizmsgid character varying(255),
    full_message character varying(5000),
    http_response_code character varying(255),
    message_name character varying(255),
    resp_bizmsgid character varying(255),
    resp_msg character varying(5000),
    saf_counter integer,
    send_time timestamp without time zone,
    recpt_bank character varying(255)
);
 $   DROP TABLE public.outbound_message;
       public         postgres    false    3            �            1259    41660    settlement_proc    TABLE     �  CREATE TABLE public.settlement_proc (
    id bigint NOT NULL,
    ack character varying(255),
    for_reversal character varying(255),
    orgnl_crdt_trn_bizmsgid character varying(255),
    orign_bank character varying(255),
    recpt_bank character varying(255),
    reversal_bizmsgid character varying(255),
    settl_conf_bizmsgid character varying(255),
    settl_conf_msg_name character varying(255)
);
 #   DROP TABLE public.settlement_proc;
       public         postgres    false    3            "          0    41599    account 
   TABLE DATA               �   COPY public.account (id, account_no, account_type, additional_info, amount, creditor_account_number, creditor_account_type, creditor_id, creditor_name, creditor_resident_status, creditor_status, creditor_town_name, creditor_type, intr_ref_id) FROM stdin;
    public       postgres    false    197   k4       #          0    41607    account_enquiry 
   TABLE DATA               �   COPY public.account_enquiry (id, account_no, amount, cre_dt, direction, intr_ref_id, orign_bank, recpt_bank, req_biz_msg_id, resp_status) FROM stdin;
    public       postgres    false    198   �4       $          0    41615    credit_transfer 
   TABLE DATA               5  COPY public.credit_transfer (id, settlconf_bizmsgid, acctenq_req_bismsgid, acct_enqr_resp_bizmsgid, acct_enqr_resp_status, amount, crdttrn_req_bizmsgid, crdttrn_resp_bizmsgid, crdttrn_resp_status, cre_dt, creditor_acct_no, debtor_acct_no, direction, intr_ref_id, msg_type, orign_bank, recpt_bank) FROM stdin;
    public       postgres    false    199   "5       %          0    41623    credit_transfer_request 
   TABLE DATA               �   COPY public.credit_transfer_request (id, account_number, account_type, amount, creditor_name, payment_info, request_time, transaction_id) FROM stdin;
    public       postgres    false    200   ?5       &          0    41631    debit_transfer_request 
   TABLE DATA               �   COPY public.debit_transfer_request (id, account_number, account_type, amount, debitor_name, payment_info, request_time, transaction_id) FROM stdin;
    public       postgres    false    201   \5       '          0    41639    inbound_message 
   TABLE DATA               �   COPY public.inbound_message (id, bizmsgid, bizsvc, copy_msg, orgn_bank, full_msg, msg_name, dupl, receive_dt, resp_msg) FROM stdin;
    public       postgres    false    202   �5       (          0    41647    message_counter 
   TABLE DATA               ?   COPY public.message_counter (tanggal, last_number) FROM stdin;
    public       postgres    false    203   �5       )          0    41652    outbound_message 
   TABLE DATA               �   COPY public.outbound_message (id, bizmsgid, full_message, http_response_code, message_name, resp_bizmsgid, resp_msg, saf_counter, send_time, recpt_bank) FROM stdin;
    public       postgres    false    204    6       *          0    41660    settlement_proc 
   TABLE DATA               �   COPY public.settlement_proc (id, ack, for_reversal, orgnl_crdt_trn_bizmsgid, orign_bank, recpt_bank, reversal_bizmsgid, settl_conf_bizmsgid, settl_conf_msg_name) FROM stdin;
    public       postgres    false    205   6       3           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 13, true);
            public       postgres    false    196            �
           2606    41614 $   account_enquiry account_enquiry_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.account_enquiry
    ADD CONSTRAINT account_enquiry_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.account_enquiry DROP CONSTRAINT account_enquiry_pkey;
       public         postgres    false    198            �
           2606    41606    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public         postgres    false    197            �
           2606    41622 $   credit_transfer credit_transfer_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.credit_transfer
    ADD CONSTRAINT credit_transfer_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.credit_transfer DROP CONSTRAINT credit_transfer_pkey;
       public         postgres    false    199            �
           2606    41630 4   credit_transfer_request credit_transfer_request_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.credit_transfer_request
    ADD CONSTRAINT credit_transfer_request_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.credit_transfer_request DROP CONSTRAINT credit_transfer_request_pkey;
       public         postgres    false    200            �
           2606    41638 2   debit_transfer_request debit_transfer_request_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.debit_transfer_request
    ADD CONSTRAINT debit_transfer_request_pkey PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.debit_transfer_request DROP CONSTRAINT debit_transfer_request_pkey;
       public         postgres    false    201            �
           2606    41646 $   inbound_message inbound_message_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.inbound_message
    ADD CONSTRAINT inbound_message_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.inbound_message DROP CONSTRAINT inbound_message_pkey;
       public         postgres    false    202            �
           2606    41651 $   message_counter message_counter_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.message_counter
    ADD CONSTRAINT message_counter_pkey PRIMARY KEY (tanggal);
 N   ALTER TABLE ONLY public.message_counter DROP CONSTRAINT message_counter_pkey;
       public         postgres    false    203            �
           2606    41659 &   outbound_message outbound_message_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.outbound_message
    ADD CONSTRAINT outbound_message_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.outbound_message DROP CONSTRAINT outbound_message_pkey;
       public         postgres    false    204            �
           2606    41667 $   settlement_proc settlement_proc_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.settlement_proc
    ADD CONSTRAINT settlement_proc_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.settlement_proc DROP CONSTRAINT settlement_proc_pkey;
       public         postgres    false    205            "   g   x�3�4772600NgGgg��ĒR���D����DN#�30@VP�Y�X��ihdlb
��~.�
 �3(�83%5����9�3̕�+1;��$�'Ə+F��� M      #   0   x�3�4772600N#�30���!S#cS(��b���� .#
�      $      x������ � �      %      x������ � �      &   Z   x���;
� E�z\�P�|�`�Ӥ�!��?.��^�̔�(`ճV,�.�����ݏ��+	pPxF-v	���Ɨ�{�����1l      '      x������ � �      (      x������ � �      )      x������ � �      *      x������ � �     