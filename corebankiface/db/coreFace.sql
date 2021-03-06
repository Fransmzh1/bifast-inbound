PGDMP         6                y            coreFace    10.10    10.10     ?
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            ?
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    41464    coreFace    DATABASE     ?   CREATE DATABASE "coreFace" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_Indonesia.1252' LC_CTYPE = 'English_Indonesia.1252';
    DROP DATABASE "coreFace";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            ?            1259    41668    account    TABLE     h  CREATE TABLE public.account (
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
       public         postgres    false    3            ?            1259    41676    credit_transfer_request    TABLE     T  CREATE TABLE public.credit_transfer_request (
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
       public         postgres    false    3            ?            1259    41684    debit_transfer_request    TABLE     i  CREATE TABLE public.debit_transfer_request (
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
       public         postgres    false    3            ?            1259    41510    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    3            ?
          0    41668    account 
   TABLE DATA               ?   COPY public.account (id, account_no, account_type, additional_info, amount, creditor_account_number, creditor_account_type, creditor_id, creditor_name, creditor_resident_status, creditor_status, creditor_town_name, creditor_type, intr_ref_id) FROM stdin;
    public       postgres    false    197   ^       ?
          0    41676    credit_transfer_request 
   TABLE DATA               ?   COPY public.credit_transfer_request (id, account_number, account_type, amount, creditor_name, payment_info, request_time, transaction_id) FROM stdin;
    public       postgres    false    198   ?       ?
          0    41684    debit_transfer_request 
   TABLE DATA               ?   COPY public.debit_transfer_request (id, account_number, account_type, amount, debitor_name, payment_info, request_time, transaction_id) FROM stdin;
    public       postgres    false    199   9                  0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 15, true);
            public       postgres    false    196            z
           2606    41675    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public         postgres    false    197            |
           2606    41683 4   credit_transfer_request credit_transfer_request_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.credit_transfer_request
    ADD CONSTRAINT credit_transfer_request_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.credit_transfer_request DROP CONSTRAINT credit_transfer_request_pkey;
       public         postgres    false    198            ~
           2606    41691 2   debit_transfer_request debit_transfer_request_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.debit_transfer_request
    ADD CONSTRAINT debit_transfer_request_pkey PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.debit_transfer_request DROP CONSTRAINT debit_transfer_request_pkey;
       public         postgres    false    199            ?
   k   x?e˱@0????)?r????A??&i$.?Og?p???(Kc????˭כ?ča(Ɉ~]n?+T}??4??p????F?;?????????S-?R????      ?
   P   x?34?4772600NgGggN# 0?32?????T(-*O?+??N,)UH)MT(?LO????401050261?????? ??U      ?
   Q   x??;
? ?z?^@y?1?]?O??F??z?8?H?R? ߨ???bӤ?~?=?
?a~?إ!?U??Apd?y枘??X?     