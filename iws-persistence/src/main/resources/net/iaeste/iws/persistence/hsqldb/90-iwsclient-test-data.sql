-- =============================================================================
-- Please add all test data here, for integration tests & demonstrations
-- =============================================================================

-- Austrian Test users, the NS is defined in the init_data script, UserId >= 7
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('39e6ff57-bcd9-4b5f-9b56-323a2434b5f8', 'ACTIVE', 'austria1', 'austria1.user@iaeste.org', '1a72c0aee217335849f53b734abcb4328cb6d3ddf51f03568e1d322f1e4e134c', 'User1', 'Austria');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('98cb1a37-1b53-4706-98df-1206afa8b1a5', 'ACTIVE', 'austria2', 'austria2.user@iaeste.org', '25881e68fd8be6d4702e0ad4226145d3bf2c0ff01cb993824a1ebba63e495936', 'User2', 'Austria');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('f65dd21e-611f-404d-9e35-a9724b05c90a', 'ACTIVE', 'austria3', 'austria3.user@iaeste.org', 'f87cba3df6b6c3867465a8aa5d9f9232aaea3e1a6e8e6c985e02681f4f499a9a', 'User3', 'Austria');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('1c22a778-755a-4ae1-84ca-ec9ab7b5b521', 'ACTIVE', 'austria4', 'austria4.user@iaeste.org', '0fe6501c94463f47c77d81dd5d53156663b5bcd8ff06a2f9fe47487b8a66717d', 'User4', 'Austria');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('bc9c9a21-e237-4d5d-aab0-6da3b06f28af', 'ACTIVE', 'austria5', 'austria5.user@iaeste.org', '49fcb4754387644d6b917bea2d1dabc5e557fda7e8f034101c6154679d422865', 'User5', 'Austria');

-- Adding the Austrian Users to the Austrian Member & National Groups
insert into user_to_group (user_id, group_id, role_id) values ( 7, 10, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 7, 11, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 7, 12, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 8, 10, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 8, 11, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 8, 12, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 9, 10, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 9, 11, 2);
insert into user_to_group (user_id, group_id, role_id) values ( 9, 12, 2);
insert into user_to_group (user_id, group_id, role_id) values (10, 10, 2);
insert into user_to_group (user_id, group_id, role_id) values (10, 11, 2);
insert into user_to_group (user_id, group_id, role_id) values (10, 12, 2);
insert into user_to_group (user_id, group_id, role_id) values (11, 10, 2);
insert into user_to_group (user_id, group_id, role_id) values (11, 11, 2);
insert into user_to_group (user_id, group_id, role_id) values (11, 12, 2);

-- Croatian Test users, the NS is defined in the init_data script, UserId >= 12
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('6a112a56-fb00-449a-a1b7-b07d79a81733', 'ACTIVE', 'croatia1', 'croatia1.user@iaeste.org', '7d50ce2dc79e9a017fe8160468619154379f1e3605b08011cbc2abf2e591edd6', 'User1', 'Croatia');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('18093bba-3e24-4ddb-8e3b-db4d4f9a5381', 'ACTIVE', 'croatia2', 'croatia2.user@iaeste.org', 'f43b026ca33b17e44dd9b248aee32fb1f17a0294a59bbc68949efd4a85f4c849', 'User2', 'Croatia');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('21ea1fc3-c6b7-4e5e-914e-8c0e3537c0f6', 'ACTIVE', 'croatia3', 'croatia3.user@iaeste.org', '4c97697fc3c210ac987380bc91961e22e7c199dddf2399a56a930ef820ce9f1e', 'User3', 'Croatia');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('d0865ed9-e9d6-4cf0-8626-b813797958b8', 'ACTIVE', 'croatia4', 'croatia4.user@iaeste.org', '90a1f8166d689dcf31f9ad68df103adcd6e16df19d02064cae3e4793170eba20', 'User4', 'Croatia');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('f57a1c4a-ab45-4b5f-9b3d-93a1f43711e3', 'ACTIVE', 'croatia5', 'croatia5.user@iaeste.org', '8078dfc2a4fbc953a994b8d2a1f7314e444ebc108d22e3a8694adc8f7281faf7', 'User5', 'Croatia');

-- Adding the Croatian Users to the Croatian Member & National Groups
insert into user_to_group (user_id, group_id, role_id) values (12, 13, 2);
insert into user_to_group (user_id, group_id, role_id) values (12, 14, 2);
insert into user_to_group (user_id, group_id, role_id) values (12, 15, 2);
insert into user_to_group (user_id, group_id, role_id) values (13, 13, 2);
insert into user_to_group (user_id, group_id, role_id) values (13, 14, 2);
insert into user_to_group (user_id, group_id, role_id) values (13, 15, 2);
insert into user_to_group (user_id, group_id, role_id) values (14, 13, 2);
insert into user_to_group (user_id, group_id, role_id) values (14, 14, 2);
insert into user_to_group (user_id, group_id, role_id) values (14, 15, 2);
insert into user_to_group (user_id, group_id, role_id) values (15, 13, 2);
insert into user_to_group (user_id, group_id, role_id) values (15, 14, 2);
insert into user_to_group (user_id, group_id, role_id) values (15, 15, 2);
insert into user_to_group (user_id, group_id, role_id) values (16, 13, 2);
insert into user_to_group (user_id, group_id, role_id) values (16, 14, 2);
insert into user_to_group (user_id, group_id, role_id) values (16, 15, 2);

-- German Test users, the NS is defined in the init_data script, UserId >= 17
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('63459109-550a-43ee-afa8-10ef3f0e609d', 'ACTIVE', 'germany1', 'germany1.user@iaeste.org', '8475a2e11b1b2669d453655e729079724c9fc0d617b3b7bb295239841ff8ac4d', 'User1', 'Germany');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('ee75749a-c484-4775-920e-98bdcbb6beeb', 'ACTIVE', 'germany2', 'germany2.user@iaeste.org', 'd37c63ad15b819c6bf5f1a9f935331de5b6ff80e662b8a2564571beec1548c18', 'User2', 'Germany');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('6b7541d4-e81b-4833-82eb-c49db934760e', 'ACTIVE', 'germany3', 'germany3.user@iaeste.org', 'e4e162db7d885bdcacf9375300e5e7e1fc264a04cc37fadf4a4e5bcc89f11791', 'User3', 'Germany');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('8d44cd1e-8484-47c2-844d-ccf01efab735', 'ACTIVE', 'germany4', 'germany4.user@iaeste.org', 'de40554bc4c14ae46a0d1772187a6427c64e88b07e90a9c63907f4a005e510ab', 'User4', 'Germany');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('83eee10e-4653-4420-b723-56a4df4f8b4e', 'ACTIVE', 'germany5', 'germany5.user@iaeste.org', 'dd656825487c25f247b12245d589e4439ec30d5f1462135c6e23db80ca707ca3', 'User5', 'Germany');

-- Adding the German Users to the German Member & National Groups
insert into user_to_group (user_id, group_id, role_id) values (17, 19, 2);
insert into user_to_group (user_id, group_id, role_id) values (17, 20, 2);
insert into user_to_group (user_id, group_id, role_id) values (17, 21, 2);
insert into user_to_group (user_id, group_id, role_id) values (18, 19, 2);
insert into user_to_group (user_id, group_id, role_id) values (18, 20, 2);
insert into user_to_group (user_id, group_id, role_id) values (18, 21, 2);
insert into user_to_group (user_id, group_id, role_id) values (19, 19, 2);
insert into user_to_group (user_id, group_id, role_id) values (19, 20, 2);
insert into user_to_group (user_id, group_id, role_id) values (19, 21, 2);
insert into user_to_group (user_id, group_id, role_id) values (20, 19, 2);
insert into user_to_group (user_id, group_id, role_id) values (20, 20, 2);
insert into user_to_group (user_id, group_id, role_id) values (20, 21, 2);
insert into user_to_group (user_id, group_id, role_id) values (21, 19, 2);
insert into user_to_group (user_id, group_id, role_id) values (21, 20, 2);
insert into user_to_group (user_id, group_id, role_id) values (21, 21, 2);

-- Hungarian Test users, the NS is defined in the init_data script, UserId >= 22
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('3f80737f-5236-454d-8f6f-24173ca26cdb', 'ACTIVE', 'hungary1', 'hungary1.user@iaeste.org', '19fb4417f129a87c2a40ff60f4c0db14d43afb028d3d409c0f9bee6998f99cc1', 'User1', 'Hungary');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('b6baf2d1-0ad5-49d7-8126-995391c71cbc', 'ACTIVE', 'hungary2', 'hungary2.user@iaeste.org', '035ca18f9240db0031702df4aa98828dcbcdfd68a0d49e6028eab7713b6d401f', 'User2', 'Hungary');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('bc48f823-c8d4-42fc-8311-1bb7902b69a6', 'ACTIVE', 'hungary3', 'hungary3.user@iaeste.org', '5f65549d55ffffe88f1bca7fdca1711a8c341c7ff00e1d79376a50c125cba563', 'User3', 'Hungary');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('a61f849f-5863-4b78-8954-c349f3422f3a', 'ACTIVE', 'hungary4', 'hungary4.user@iaeste.org', '8a4caaf668fa9770bb5df3031f4349ea955bc8bc6b81feda48afd547f1ed4d48', 'User4', 'Hungary');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('1cab0fd3-89a9-462e-aa1c-e844576c551b', 'ACTIVE', 'hungary5', 'hungary5.user@iaeste.org', '0d76ccea08d283aab0c26b79e6c0b2e5e52e5b1586931d6423d1d518af03b04e', 'User5', 'Hungary');

-- Adding the Hungarian Users to the Hungarian Member & National Groups
insert into user_to_group (user_id, group_id, role_id) values (22, 25, 2);
insert into user_to_group (user_id, group_id, role_id) values (22, 26, 2);
insert into user_to_group (user_id, group_id, role_id) values (22, 27, 2);
insert into user_to_group (user_id, group_id, role_id) values (23, 25, 2);
insert into user_to_group (user_id, group_id, role_id) values (23, 26, 2);
insert into user_to_group (user_id, group_id, role_id) values (23, 27, 2);
insert into user_to_group (user_id, group_id, role_id) values (24, 25, 2);
insert into user_to_group (user_id, group_id, role_id) values (24, 26, 2);
insert into user_to_group (user_id, group_id, role_id) values (24, 27, 2);
insert into user_to_group (user_id, group_id, role_id) values (25, 25, 2);
insert into user_to_group (user_id, group_id, role_id) values (25, 26, 2);
insert into user_to_group (user_id, group_id, role_id) values (25, 27, 2);
insert into user_to_group (user_id, group_id, role_id) values (26, 25, 2);
insert into user_to_group (user_id, group_id, role_id) values (26, 26, 2);
insert into user_to_group (user_id, group_id, role_id) values (26, 27, 2);

-- Polish Test users, the NS is defined in the init_data script, UserId >= 27
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('b2acb9cd-7787-4c24-a34a-79527d2ffa8b', 'ACTIVE', 'poland1', 'poland1.user@iaeste.org', 'dbc24290ec385e2f64273a3d5705719791afc6e07a913ffeb438b7aaa6416f39', 'User1', 'Poland');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('b98c8639-cb61-46c1-b0af-74c14f2505c7', 'ACTIVE', 'poland2', 'poland2.user@iaeste.org', '5ea6f3c38511755d1da08bcf045819dbe76e3615f61da93ae479cf56adf70f1a', 'User2', 'Poland');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('a3c2e480-be1c-4e8d-b2fc-01711d5d8a6d', 'ACTIVE', 'poland3', 'poland3.user@iaeste.org', 'd33980bd6447d4990f5cb68fdb81fcc326df455cb0bafbd7ebfde0ec8daf9c2c', 'User3', 'Poland');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('9069e18a-5f67-43d5-977a-2e199dcc543b', 'ACTIVE', 'poland4', 'poland4.user@iaeste.org', 'd069e568ab639b69c5d0c124260d0ab509b86e98cba344e257439d8f54b2cfb8', 'User4', 'Poland');
insert into users (external_id, status, username, alias, password, firstname, lastname) values ('2a8db514-60a6-4c41-a188-51080aab8f76', 'ACTIVE', 'poland5', 'poland5.user@iaeste.org', 'b600159d9cc150e02f5504c9e236b46edb3f7e6130c231fda0bbe785598b1720', 'User5', 'Poland');

-- Adding the Polish Users to the Polish Member & National Groups
insert into user_to_group (user_id, group_id, role_id) values (27, 22, 2);
insert into user_to_group (user_id, group_id, role_id) values (27, 23, 2);
insert into user_to_group (user_id, group_id, role_id) values (27, 24, 2);
insert into user_to_group (user_id, group_id, role_id) values (28, 22, 2);
insert into user_to_group (user_id, group_id, role_id) values (28, 23, 2);
insert into user_to_group (user_id, group_id, role_id) values (28, 24, 2);
insert into user_to_group (user_id, group_id, role_id) values (29, 22, 2);
insert into user_to_group (user_id, group_id, role_id) values (29, 23, 2);
insert into user_to_group (user_id, group_id, role_id) values (29, 24, 2);
insert into user_to_group (user_id, group_id, role_id) values (30, 22, 2);
insert into user_to_group (user_id, group_id, role_id) values (30, 23, 2);
insert into user_to_group (user_id, group_id, role_id) values (30, 24, 2);
insert into user_to_group (user_id, group_id, role_id) values (31, 22, 2);
insert into user_to_group (user_id, group_id, role_id) values (31, 23, 2);
insert into user_to_group (user_id, group_id, role_id) values (31, 24, 2);

-- Add some offers for testing..
--- not postgres doesn't support the way how the nomination date is calculates. use this for PG: CURRENT_DATE + '3 month'::INTERVAL
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(1, 'AT-2013-0001-AB', '27d21139-c478-4c30-be57-aba0aa57ac2d', FALSE, 'EUR', 38.5, 7.7, 'approx. 30', 'Vienna University of Technology', 'Karlsplatz 13', '1040 Wien', 'University', 9000, 'www.tuwien.ac.at', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', NULL, NULL, NULL, NULL, NULL, NULL, 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'VIE', 'Karlsplatz', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Experience in JAVA', 1250.00, 'MONTHLY', FALSE, 'Working on a project in the field of science to visualize potential threads to economy and counter fight decreasing numbers', 'Vienna', 'R', 'B', 'IT|MATHEMATICS', 'BUSINESS_INFORMATICS', 11, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(2, 'AT-2013-0002-AB', '42dbdd0b-625a-4b97-b2c6-bf8f43bdc2b2', FALSE, 'EUR', 38.5, 7.7, 'approx. 30', 'Vienna University of Technology', 'Karlsplatz 13', '1040 Wien', 'University', 9000, 'www.tuwien.ac.at', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'GERMAN', 'G', 'O', 'HUNGARIAN', 'F', 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'VIE', 'Karlsplatz', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), '', 1250.00, 'MONTHLY', FALSE, 'Work on a simulation of nicotine addiction on monkeys', 'Vienna', 'R', 'B|M', 'IT|CHEMISTRY', 'BUSINESS_INFORMATICS', 11, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(3, 'AT-2013-0003-AB', '2768e03a-1574-4063-8d1f-3aae55df8cdd', FALSE, 'EUR', 38.5, 7.7, 'approx. 30', 'Vienna University of Technology', 'Karlsplatz 13', '1040 Wien', 'University', 9000, 'www.tuwien.ac.at', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'GERMAN', 'G', 'A', 'HUNGARIAN', 'F', 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'VIE', 'Karlsplatz', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Has built three houses', 1250.00, 'MONTHLY', FALSE, 'World first dog hotel', 'Vienna', 'R', 'B|M|E', 'CIVIL_ENGINEERING', '', 11, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(4, 'AT-2013-0004-AB', '636c7603-6706-460f-82e7-cba97298edeb', FALSE, 'EUR', 38.5, 7.7, 'approx. 30', 'Vienna University of Technology', 'Karlsplatz 13', '1040 Wien', 'University', 9000, 'www.tuwien.ac.at', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'GERMAN', 'G', NULL, NULL, NULL, 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'VIE', 'Karlsplatz', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Good tongue and great appetite', 1250.00, 'MONTHLY', FALSE, 'Inspection of food\nDetermination of parameters\nCreation of tasteless narcotic', 'Vienna', 'R', 'B', 'FOOD_SCIENCE', '', 11, 'SHARED');

INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(5, 'HR-2013-0001', '88debcd6-7b50-4320-a3a4-9945070c48c0', FALSE, 'HRK', 38.5, 7.7, 'approx. 10', 'University of Zagreb', 'Marshal Tito Square 14', '10000, Zagreb', 'University', 5000, 'www.unizg.hr', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', NULL, NULL, NULL, NULL, NULL, NULL, 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'ZAG', 'Zagreb Glavni Kolod', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Experience in JAVA', 1250.00, 'MONTHLY', FALSE, 'Working on a project in the field of science to visualize potential threads to economy and counter fight decreasing numbers', 'Zagreb', 'R', 'B', 'IT|MATHEMATICS', 'BUSINESS_INFORMATICS', 14, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(6, 'HR-2013-0002', '2b1005b7-c7a3-46b2-b127-c621c2c411b2', FALSE, 'HRK', 38.5, 7.7, 'approx. 10', 'University of Zagreb', 'Marshal Tito Square 14', '10000, Zagreb', 'University', 5000, 'www.unizg.hr', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'CROATIAN', 'G', 'O', 'HUNGARIAN', 'F', 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'ZAG', 'Zagreb Glavni Kolod', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), '', 1250.00, 'MONTHLY', FALSE, 'Work on a simulation of nicotine addiction on monkeys', 'Zagreb', 'R', 'B', 'IT|CHEMISTRY', '', 14, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(7, 'HR-2013-0003', '3ce2a1c2-98e3-4289-90b4-adf286f4c5f3', FALSE, 'HRK', 38.5, 7.7, 'approx. 10', 'University of Zagreb', 'Marshal Tito Square 14', '10000, Zagreb', 'University', 5000, 'www.unizg.hr', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'CROATIAN', 'G', 'A', 'HUNGARIAN', 'F', 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'ZAG', 'Zagreb Glavni Kolod', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Has built three houses', 1250.00, 'MONTHLY', FALSE, 'World first dog hotel', 'Zagreb', 'R', 'B', 'CIVIL_ENGINEERING', '', 14, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(8, 'HR-2013-0004', '081bcb91-04ce-4301-9bdf-81784750a96a', FALSE, 'HRK', 38.5, 7.7, 'approx. 10', 'University of Zagreb', 'Marshal Tito Square 14', '10000, Zagreb', 'University', 5000, 'www.unizg.hr', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'CROATIAN', 'G', NULL, NULL, NULL, 500, 'MONTHLY', 'IAESTE', 300, 'MONTHLY', 6, 12, 'ZAG', 'Zagreb Glavni Kolod', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Good tongue and great appetite', 1250.00, 'MONTHLY', FALSE, 'Inspection of food\nDetermination of parameters\nCreation of tasteless narcotic', 'Zagreb', 'R', 'B', 'FOOD_SCIENCE', '', 14, 'SHARED');

INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(9, 'DE-2013-0010-BE', '930e1616-b214-499c-9531-b833fcc2202b', FALSE, 'EUR', 38.5, 7.7, 'see homepage', 'Ludwig Maximilian University of Munich', 'Geschwister-Scholl-Platz 1', '80539 Munich', 'University', 15000, 'www.en.uni-muenchen.de', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', NULL, NULL, NULL, NULL, NULL, NULL, 400, 'MONTHLY', 'IAESTE', 200, 'MONTHLY', 6, 12, 'BER', 'U3/U6 Universität', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Experience in JAVA', 700.00, 'MONTHLY', FALSE, 'Working on a project in the field of science to visualize potential threads to economy and counter fight decreasing numbers', 'Berlin', 'R', 'B', 'IT|MATHEMATICS', 'BUSINESS_INFORMATICS', 20, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(10, 'DE-2013-0020-BE', '0ec09a52-bfe5-4d4b-b598-e873fcf25cb3', FALSE, 'EUR', 38.5, 7.7, 'see homepage', 'Ludwig Maximilian University of Munich', 'Geschwister-Scholl-Platz 1', '80539 Munich', 'University', 15000, 'www.en.uni-muenchen.de', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'GERMAN', 'G', 'O', 'POLISH', 'F', 400, 'MONTHLY', 'IAESTE', 200, 'MONTHLY', 6, 12, 'BER', 'U3/U6 Universität', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), '', 700.00, 'MONTHLY', FALSE, 'Work on a simulation of nicotine addiction on monkeys', 'Berlin', 'R', 'B', 'IT|CHEMISTRY', 'BUSINESS_INFORMATICS', 20, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(11, 'DE-2013-0030-BE', '267a0959-06fb-4ffa-be8f-c7fb38ee8c1b', FALSE, 'EUR', 38.5, 7.7, 'see homepage', 'Ludwig Maximilian University of Munich', 'Geschwister-Scholl-Platz 1', '80539 Munich', 'University', 15000, 'www.en.uni-muenchen.de', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'GERMAN', 'G', 'A', 'POLISH', 'F', 400, 'MONTHLY', 'IAESTE', 200, 'MONTHLY', 6, 12, 'BER', 'U3/U6 Universität', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Has built three houses', 700.00, 'MONTHLY', FALSE, 'World first dog hotel', 'Berlin', 'R', 'B', 'CIVIL_ENGINEERING', '', 20, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(12, 'DE-2013-0040-BE', 'b2b3356c-5b41-474f-a3a1-ef27f0d107d3', FALSE, 'EUR', 38.5, 7.7, 'see homepage', 'Ludwig Maximilian University of Munich', 'Geschwister-Scholl-Platz 1', '80539 Munich', 'University', 15000, 'www.en.uni-muenchen.de', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'GERMAN', 'G', NULL, NULL, NULL, 400, 'MONTHLY', 'IAESTE', 200, 'MONTHLY', 6, 12, 'BER', 'U3/U6 Universität', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Good tongue and great appetite', 700.00, 'MONTHLY', FALSE, 'Inspection of food\nDetermination of parameters\nCreation of tasteless narcotic', 'Berlin', 'R', 'B', 'FOOD_SCIENCE', '', 20, 'SHARED');

INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(13, 'PL-2013-0010-WZ', '60aeb42d-471b-43e8-8e5c-1e7f1573d9ea', FALSE, 'PLN', 35.5, 7.7, '0', 'University of Warsaw', 'Krakowskie Przedmieście 26/28', '00-927 Warsaw', 'University', 8000, 'www.uw.edu.pl/en/', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', NULL, NULL, NULL, NULL, NULL, NULL, 800, 'MONTHLY', 'IAESTE', 500, 'MONTHLY', 6, 10, 'WAW', 'Metro Świętokrzyska', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Experience in JAVA', 1400.00, 'MONTHLY', FALSE, 'Working on a project in the field of science to visualize potential threads to economy and counter fight decreasing numbers', 'Warsaw', 'R', 'B', 'IT|MATHEMATICS', 'BUSINESS_INFORMATICS', 22, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(14, 'PL-2013-0020-WS', '09123feb-6f0e-44cc-bf62-749b243d3d5d', FALSE, 'PLN', 35.5, 7.7, '0', 'University of Warsaw', 'Krakowskie Przedmieście 26/28', '00-927 Warsaw', 'University', 8000, 'www.uw.edu.pl/en/', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'RUSSIAN', 'G', 'O', 'POLISH', 'F', 800, 'MONTHLY', 'IAESTE', 500, 'MONTHLY', 6, 10, 'WAW', 'Metro Świętokrzyska', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), '', 1400.00, 'MONTHLY', FALSE, 'Work on a simulation of nicotine addiction on monkeys', 'Warsaw', 'R', 'B', 'IT|CHEMISTRY', 'BUSINESS_INFORMATICS', 22, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(15, 'PL-2013-0030-WZ', '97e2ffab-7e44-4b87-a894-bb3b59a7a299', FALSE, 'PLN', 35.5, 7.7, '0', 'University of Warsaw', 'Krakowskie Przedmieście 26/28', '00-927 Warsaw', 'University', 8000, 'www.uw.edu.pl/en/', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'RUSSIAN', 'G', 'A', 'POLISH', 'F', 800, 'MONTHLY', 'IAESTE', 500, 'MONTHLY', 6, 10, 'WAW', 'Metro Świętokrzyska', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Has built three houses', 1400.00, 'MONTHLY', FALSE, 'World first dog hotel', 'Warsaw', 'R', 'B', 'CIVIL_ENGINEERING', '', 22, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(16, 'PL-2013-0040-WS', '353bc1bf-2ac3-429d-9c9e-534dec476aa9', FALSE, 'PLN', 35.5, 7.7, '0', 'University of Warsaw', 'Krakowskie Przedmieście 26/28', '00-927 Warsaw', 'University', 8000, 'www.uw.edu.pl/en/', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'RUSSIAN', 'G', NULL, NULL, NULL, 800, 'MONTHLY', 'IAESTE', 500, 'MONTHLY', 6, 10, 'WAW', 'Metro Świętokrzyska', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Good tongue and great appetite', 1400.00, 'MONTHLY', FALSE, 'Inspection of food\nDetermination of parameters\nCreation of tasteless narcotic', 'Warsaw', 'R', 'B', 'FOOD_SCIENCE', '', 22, 'SHARED');

INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(17, 'HU-2013-0001', 'e53bc2bf-7ae6-4b62-8964-c7a4d9f47a1b', FALSE, 'HUF', 35.5, 7.7, '0', 'Budapest University of Technology and Economics', 'Műegyetem rkp. 3-9.', 'H-1111 Budapest', 'University', 10000, 'english.www.bme.hu', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', NULL, NULL, NULL, NULL, NULL, NULL, 2000, 'MONTHLY', 'IAESTE', 1000, 'MONTHLY', 6, 10, 'BUD', 'Szent Gellért tér', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Experience in JAVA', 3500.00, 'MONTHLY', FALSE, 'Working on a project in the field of science to visualize potential threads to economy and counter fight decreasing numbers', 'Budapest', 'R', 'B', 'IT|MATHEMATICS', 'BUSINESS_INFORMATICS', 25, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(18, 'HU-2013-0002', '3c1ff1b3-1f5e-44b5-9c33-6b7398c25d3d', FALSE, 'HUF', 35.5, 7.7, '0', 'Budapest University of Technology and Economics', 'Műegyetem rkp. 3-9.', 'H-1111 Budapest', 'University', 10000, 'english.www.bme.hu', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'HUNGARIAN', 'G', 'O', 'POLISH', 'F', 2000, 'MONTHLY', 'IAESTE', 1000, 'MONTHLY', 6, 10, 'BUD', 'Szent Gellért tér', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), '', 3500.00, 'MONTHLY', FALSE, 'Work on a simulation of nicotine addiction on monkeys', 'Budapest', 'R', 'B', 'IT|CHEMISTRY', 'BUSINESS_INFORMATICS', 25, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(19, 'HU-2013-0003', 'cab649c0-470d-493e-a837-2355fe755ba9', FALSE, 'HUF', 35.5, 7.7, '0', 'Budapest University of Technology and Economics', 'Műegyetem rkp. 3-9.', 'H-1111 Budapest', 'University', 10000, 'english.www.bme.hu', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'HUNGARIAN', 'G', 'A', 'POLISH', 'F', 2000, 'MONTHLY', 'IAESTE', 1000, 'MONTHLY', 6, 10, 'BUD', 'Szent Gellért tér', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Has built three houses', 3500.00, 'MONTHLY', FALSE, 'World first dog hotel', 'Budapest', 'R', 'B', 'CIVIL_ENGINEERING', '', 25, 'SHARED');
INSERT INTO offers (id, ref_no, external_id, canteen, currency, weekly_hours, daily_hours, deduction, employer_name, employer_address, employer_address_2, employer_business, employer_employees_cnt, employer_website, from_date, to_date, from_date_2, to_date_2, unavailable_from, unavailable_to, language_1, language_1_level, language_1_op, language_2, language_2_level, language_2_op, language_3, language_3_level, living_cost, living_cost_frequency, lodging_by, lodging_cost, lodging_cost_frequency, min_weeks, max_weeks, nearest_airport, nearest_pub_transport, nomination_deadline, other_requirements, payment, payment_frequency, prev_training_req, work_description, working_place, work_type, study_levels, study_fields, specializations, group_id, status) VALUES
(20, 'HU-0013-0004', '3482517b-19ce-40e4-a607-982b1618cfa1', FALSE, 'HUF', 35.5, 7.7, '0', 'Budapest University of Technology and Economics', 'Műegyetem rkp. 3-9.', 'H-1111 Budapest', 'University', 10000, 'english.www.bme.hu', '2013-06-01', '2013-09-30', NULL, NULL, NULL, NULL, 'ENGLISH', 'E', 'A', 'HUNGARIAN', 'G', NULL, NULL, NULL, 2000, 'MONTHLY', 'IAESTE', 1000, 'MONTHLY', 6, 10, 'BUD', 'Szent Gellért tér', DATE_ADD ( TODAY(), INTERVAL 3 MONTH ), 'Good tongue and great appetite', 3500.00, 'MONTHLY', FALSE, 'Inspection of food\nDetermination of parameters\nCreation of tasteless narcotic', 'Budapest', 'R', 'B', 'FOOD_SCIENCE', '', 25, 'SHARED');

INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (1, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (1, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (1, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (1, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (2, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (2, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (2, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (2, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (3, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (3, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (3, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (3, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (4, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (4, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (4, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (4, 25, 7, 7);

INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (5, 11, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (5, 20, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (5, 22, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (5, 25, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (6, 10, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (6, 20, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (6, 22, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (6, 25, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (7, 11, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (7, 20, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (7, 22, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (7, 25, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (8, 11, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (8, 20, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (8, 22, 22, 22);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (8, 25, 22, 22);

INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (9, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (9, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (9, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (9, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (10, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (10, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (10, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (10, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (11, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (11, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (11, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (11, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (12, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (12, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (12, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (12, 25, 7, 7);

INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (13, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (13, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (13, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (13, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (14, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (14, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (14, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (14, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (15, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (15, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (15, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (15, 25, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (16, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (16, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (16, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (16, 25, 7, 7);

INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (17, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (17, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (17, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (17, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (18, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (18, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (18, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (18, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (19, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (19, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (19, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (19, 22, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (20, 11, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (20, 14, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (20, 20, 7, 7);
INSERT INTO offer_to_group (offer_id, group_id, modified_by, created_by) VALUES (20, 22, 7, 7);