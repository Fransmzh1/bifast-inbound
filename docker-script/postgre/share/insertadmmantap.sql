--
-- Data for Name: cdid_owner; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cdid_owner (ownname, created_at, update_at, ownstatus, owntype) OVERRIDING SYSTEM VALUE VALUES ('PT Metrodata Tbk', '2021-05-23 21:03:23.783466', NULL, 1, 1);


INSERT INTO public.cdid_dbcorellation (description, created_at, updated_at) OVERRIDING SYSTEM VALUE VALUES ('cdid_owner w mst_biodata (One on One)', '2021-04-29 11:41:05.279345', NULL);
INSERT INTO public.cdid_dbcorellation (description, created_at, updated_at) OVERRIDING SYSTEM VALUE VALUES ('cdid_tenant w mst_biodata (One on One)', '2021-04-29 12:42:14.136858', NULL);
INSERT INTO public.cdid_dbcorellation (description, created_at, updated_at) OVERRIDING SYSTEM VALUE VALUES ('cdid_tenant_user w mst_biodata (One on One)', '2021-05-05 15:12:41.462291', NULL);

--
-- Data for Name: cdid_tenant; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cdid_tenant (tnname, created_at, update_at, tnstatus, tntype, cdidowner, tnflag, tnparentid, cdtenant) OVERRIDING SYSTEM VALUE VALUES ('PT Bank Mantap', '2021-10-11 21:39:42.974186', NULL, 1, 2, 1, 1, 0, '9111');
INSERT INTO public.mst_biodata_corell (bioname, bioemailactive, biophoneactive, bioaddress, bioidcorel, bionik, bionpwp, biocorelobjid, created_at, updated_at, bioidtipenik) OVERRIDING SYSTEM VALUE VALUES ('PT Bank Mantap', 'admin@bankmantap.co.id', '5554444', 'Bintaro Sektor 101', 2, NULL, NULL, 1, '2021-07-26 11:48:05.108862', NULL, 1);
INSERT INTO public.cdid_tenant_user (fullname, userid, pwd, creator_stat, creator_byid, created_at, updated_at, idtenant, idsubtenant, leveltenant, active, total_attempt, locked_at, last_login, last_change_password) OVERRIDING SYSTEM VALUE VALUES ('SYS ADMIN', 'sysmantap', '$2a$04$gppTUJsKVAeBgCn21ohDpuJbnWFCs5clGhucss6v7uOhumBRIizcO', 0, 0, '2021-07-26 11:55:59.447974', NULL, 1, 0, 0, 1, 0, NULL, NULL, NULL);
INSERT INTO public.mst_biodata_corell (bioname, bioemailactive, biophoneactive, bioaddress, bioidcorel, bionik, bionpwp, biocorelobjid, created_at, updated_at, bioidtipenik) OVERRIDING SYSTEM VALUE VALUES ('SYS ADMIN', 'sysadmin@bankmantap.co.id', '5554444', 'Bintaro Sektor 101', 3, NULL, NULL, 1, '2021-07-26 11:48:05.108862', NULL, 1);

INSERT INTO public.mst_application (appname, applabel, created_at, updated_at, description, apptype, routelink) OVERRIDING SYSTEM VALUE VALUES ('default', 'Default', '2021-10-11 21:52:27.791504', NULL, 'Krakatoa Administer', 0, '/home');
INSERT INTO public.mst_application (appname, applabel, created_at, updated_at, description, apptype, routelink) OVERRIDING SYSTEM VALUE VALUES ('komi', 'KOMI', '2021-10-11 21:52:27.791504', NULL, 'This contain all modules for Komi', 1, 'http://182.169.41.153:3013//#/');

INSERT INTO public.cdid_liscenses (licensetokens, licensejson, created_byid, created_at, updated_at, expiredate, cdid_tenant, id_application, paidstatus, active, defaultactive) OVERRIDING SYSTEM VALUE VALUES ('asdfasdfasdfasdfasdfasdfasdfasdf', NULL, 0, '2021-05-23 22:16:31.344523', NULL, '2022-05-23 00:00:00', 1, 1, 1, 1, 0);
INSERT INTO public.cdid_liscenses (licensetokens, licensejson, created_byid, created_at, updated_at, expiredate, cdid_tenant, id_application, paidstatus, active, defaultactive) OVERRIDING SYSTEM VALUE VALUES ('asdfasdfdghjefgdfgbdfgb', NULL, 0, '2021-05-24 20:58:08.487401', NULL, '2022-05-24 00:00:00', 1, 2, 1, 1, 0);

--
-- Data for Name: mst_module; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO public.mst_module (modulename, created_byid, created_at, updated_at, status, idapplication, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Users Management', 0, '2021-08-10 21:55:41.156872', NULL, 1, 2, 'BIFAST001', 1);
INSERT INTO public.mst_module (modulename, created_byid, created_at, updated_at, status, idapplication, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Group & ACL Komi', 0, '2021-08-10 21:55:41.156872', NULL, 1, 2, 'BIFAST002', 1);
INSERT INTO public.mst_module (modulename, created_byid, created_at, updated_at, status, idapplication, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Settings', 0, '2021-08-10 21:58:07.849014', NULL, 1, 2, 'BIFAST003', 1);
INSERT INTO public.mst_module (modulename, created_byid, created_at, updated_at, status, idapplication, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Master', 0, '2021-08-10 21:58:07.849014', NULL, 1, 2, 'BIFAST004', 1);
INSERT INTO public.mst_module (modulename, created_byid, created_at, updated_at, status, idapplication, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Rekonsiliasi', 0, '2021-08-10 21:58:07.849014', NULL, 1, 2, 'BIFAST005', 1);
INSERT INTO public.mst_module (modulename, created_byid, created_at, updated_at, status, idapplication, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Monitoring', 0, '2021-09-22 12:39:26.750561', NULL, 1, 2, 'BIFAST006', 1);
INSERT INTO public.mst_module (modulename, created_byid, created_at, updated_at, status, idapplication, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Reports', 0, '2021-09-22 12:39:26.750561', NULL, 1, 2, 'BIFAST007', 1);


INSERT INTO public.mst_moduleby_tenant (modulename, created_byid, created_at, updated_at, status, idapplication, idowner, idtenant, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Users Management', 0, '2021-08-10 21:55:41.156872', NULL, 1, 2, 0, 1, 'BIFAST001', 1);
INSERT INTO public.mst_moduleby_tenant (modulename, created_byid, created_at, updated_at, status, idapplication, idowner, idtenant, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Group & ACL Komi', 0, '2021-08-10 21:55:41.156872', NULL, 1, 2, 0, 1, 'BIFAST002', 1);
INSERT INTO public.mst_moduleby_tenant (modulename, created_byid, created_at, updated_at, status, idapplication, idowner, idtenant, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Settings', 0, '2021-08-10 21:58:07.849014', NULL, 1, 2, 0, 1, 'BIFAST003', 1);
INSERT INTO public.mst_moduleby_tenant (modulename, created_byid, created_at, updated_at, status, idapplication, idowner, idtenant, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Master', 0, '2021-08-10 21:58:07.849014', NULL, 1, 2, 0, 1, 'BIFAST004', 1);
INSERT INTO public.mst_moduleby_tenant (modulename, created_byid, created_at, updated_at, status, idapplication, idowner, idtenant, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Rekonsiliasi', 0, '2021-08-10 21:58:07.849014', NULL, 1, 2, 0, 1, 'BIFAST005', 1);
INSERT INTO public.mst_moduleby_tenant (modulename, created_byid, created_at, updated_at, status, idapplication, idowner, idtenant, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Monitoring', 0, '2021-09-22 12:39:26.750561', NULL, 1, 2, 0, 1, 'BIFAST006', 1);
INSERT INTO public.mst_moduleby_tenant (modulename, created_byid, created_at, updated_at, status, idapplication, idowner, idtenant, modulecode, moduletype) OVERRIDING SYSTEM VALUE VALUES ('Reports', 0, '2021-09-22 12:39:26.750561', NULL, 1, 2, 0, 1, 'BIFAST007', 1);


INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifusers', 'Users', '/mgm/user/userslist', NULL, NULL, 1, 0, '2021-08-10 22:24:57.01936+07', NULL, 1, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifgroup', 'Group & ACL', '/mgm/acl/grouplist', NULL, NULL, 1, 0, '2021-08-10 22:24:57.01936+07', NULL, 1, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifsysparam', 'System Parameter', '/mgm/system/sysparam', NULL, NULL, 1, 0, '2021-08-10 22:24:57.01936+07', NULL, 1, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifbicadmin', 'Bank Participant', '/mgm/settings/bicadminlist', NULL, NULL, 1, 0, '2021-08-10 22:24:57.01936+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifcostmgm', 'Transaction Cost', '/mgm/settings/trxcostlist', NULL, NULL, 1, 0, '2021-09-22 13:12:41.073758+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifproxymtn', 'Proxy Management', '/mgm/settings/proxyaliaslist', NULL, NULL, 1, 0, '2021-08-10 22:24:57.01936+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifparamset', 'System parameters', '/mgm/settings/sysparamslist', NULL, NULL, 1, 0, '2021-09-22 12:33:44.988659+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifbranch', 'Branch', '/mgm/settings/branchlist', NULL, NULL, 1, 0, '2021-09-23 20:02:35.621307+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifprefund', 'Prefund Management', '/mgm/settings/prefunddashboard', NULL, NULL, 1, 0, '2021-09-22 12:58:27.483051+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifparamset', 'Network Management', '/mgm/settings/netmgm', NULL, NULL, 1, 0, '2021-09-22 12:36:36.720081+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifchtype', 'Channel Type', '/mgm/master/channeltypelist', NULL, NULL, 1, 0, '2021-09-22 13:00:18.038262+07', NULL, 4, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifprxtype', 'Proxy Type', '/mgm/master/proxytypelist', NULL, NULL, 1, 0, '2021-09-22 13:02:41.053669+07', NULL, 4, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifcusttype', 'Customer Type', '/mgm/master/customertype', NULL, NULL, 1, 0, '2021-09-24 23:02:09.731973+07', NULL, 4, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifmapacctype', 'Account Type', '/mgm/master/accounttype', NULL, NULL, 1, 0, '2021-09-22 13:04:10.110123+07', NULL, 4, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifmapidtype', 'Identity Type', '/mgm/master/idtype', NULL, NULL, 1, 0, '2021-09-22 13:04:10.110123+07', NULL, 4, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifmapresdtype', 'Resident Status', '/mgm/master/resident', NULL, NULL, 1, 0, '2021-09-22 13:08:48.060909+07', NULL, 4, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifmaplimit', 'Limit', '/mgm/master/limitlist', NULL, NULL, 1, 0, '2021-09-22 13:10:18.410027+07', NULL, 4, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifsyslog', 'System log', '/mgm/monitor/systemevent', NULL, NULL, 1, 0, '2021-09-27 13:32:51.702509+07', NULL, 6, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('biftransmonitoring', 'Transaction', '/mgm/monitor/transmonitoringlist', NULL, NULL, 1, 0, '2021-08-10 22:24:57.01936+07', NULL, 6, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('biflogevent', 'Log Event', '/mgm/monitor/eventlog', NULL, NULL, 1, 0, '2021-09-27 13:30:28.079028+07', NULL, 6, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifreport1', 'Transaction Report', '/mgm/report/transaction', NULL, NULL, 1, 0, '2021-09-27 13:30:28.079028+07', NULL, 7, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifsmtpsett', 'SMTP Config', '/mgm/settings/smtpconfig', NULL, NULL, 1, 0, '2021-10-12 20:39:49.728434+07', NULL, 3, NULL);
INSERT INTO public.mst_menu (name, title, routelink, routepath, iconcode, defaultid, created_byid, created_at, updated_at, idmodule, description) OVERRIDING SYSTEM VALUE VALUES ('bifmapnotif', 'Admin Notifications', '/mgm/master/adminnotificationlist', NULL, NULL, 1, 0, '2021-10-14 03:26:27.868107+07', NULL, 4, NULL);
