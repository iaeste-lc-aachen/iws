-- =============================================================================
-- Please add all test data here, for integration tests & demonstrations
-- =============================================================================

-- Austrian Test users, the NS is defined in the init_data script, UserId >= 7
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('0bc63396-fe74-43e4-862d-eee34d7daccb', 'ACTIVE', 'austria1', 'User1.Austria@iaeste.org', '08c7aa9018550471b60735843a332f84c516d6ae87e41ae9924371cf3fe5e9c6', '2a5cd593-fb35-4a23-98c5-9fb0586b648e', 'User1', 'Austria');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('0a59f299-418c-484f-953a-f4f6595b1f85', 'ACTIVE', 'austria2', 'User2.Austria@iaeste.org', '8c9be1fbf47ac883b763a4712501867e089477d319d4fa50f0b7d69c2e4183b7', '290b178e-e28a-4a73-b9cf-033f76c44724', 'User2', 'Austria');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('f9f411ca-e402-4ce1-8e71-bd11f31477c9', 'ACTIVE', 'austria3', 'User3.Austria@iaeste.org', 'bfe7ec4aa79d777db242c6814c66ec69c751c636c8745c7c8f287a42254c7463', 'e7b1873f-3b86-4f14-bbe5-fecd741d4fd2', 'User3', 'Austria');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('aac1f567-a8c9-4f89-8657-1d8a2703cb25', 'ACTIVE', 'austria4', 'User4.Austria@iaeste.org', 'd5009d5002ae63db78ed57fb74ddd241c800a9edc3c0b85beae0dbf9673332ef', 'e1226e01-d3fa-4991-8261-5ac11f305c55', 'User4', 'Austria');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('8462f1a5-2d44-41f1-a6d4-ccf42535ea13', 'ACTIVE', 'austria5', 'User5.Austria@iaeste.org', '0381c5f712a279d1d248b239d276747b0e48aec459f786123846471f4d1c8547', '69368394-6838-47c8-9ca1-03af9b345eeb', 'User5', 'Austria');

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
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('c704bfbe-d4fb-4287-b919-1431aff63227', 'ACTIVE', 'croatia1', 'User1.Croatia@iaeste.org', '07ac41023065b87b593de90e70284f2a151521c7b5ec25c8e4369c4580fe63d8', 'e43cf17c-2a64-4067-ac20-29ddd11ce5aa', 'User1', 'Croatia');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('9b5be8ed-ffd4-40c0-9454-c2251b0c71f6', 'ACTIVE', 'croatia2', 'User2.Croatia@iaeste.org', 'a81a376cb8672c6824ad5902d11dc6430de4aadd62b5534e083a15683f15ae18', '3311aee1-5835-41e3-951c-cd1a063e25a8', 'User2', 'Croatia');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('db298f39-69e9-475a-a249-8df52fe242fc', 'ACTIVE', 'croatia3', 'User3.Croatia@iaeste.org', 'a8f3e254a650e73bc1175fb4a253b67b8380f8eb2c6712e878c526921313994a', 'a8c1681e-8971-4d3b-9e41-46d633537241', 'User3', 'Croatia');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('db519b15-b62e-4957-a1e6-5be4a05771b8', 'ACTIVE', 'croatia4', 'User4.Croatia@iaeste.org', '312fafcbe31853caa8c990801d59259f141df55063398ddf4fb19737a6b9c018', '265744c9-97b3-4455-a9bb-83c61430e4b4', 'User4', 'Croatia');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('e3aeecb0-f3ac-4167-a2fb-af0ae531a9ab', 'ACTIVE', 'croatia5', 'User5.Croatia@iaeste.org', '7ccf6607b4ba68dd128239547aa53481e8956906a4ef1f0ae46e6a3dd4b7da19', '052c3d46-efd3-4e3f-96fb-71655bf20214', 'User5', 'Croatia');

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
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('ae185d31-a741-4834-b389-f1c7d91acea0', 'ACTIVE', 'germany1', 'User1.Germany@iaeste.org', '80a7db78e44fc661a3a9671dc78426d2373312dad2fabfeae9c58ced79f89662', 'd738df6e-6b1c-4dbb-8131-d1fc998811a7', 'User1', 'Germany');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('733a5dbf-69e9-4fb8-921b-ad6feaaa768d', 'ACTIVE', 'germany2', 'User2.Germany@iaeste.org', 'e2d2289442f282371bf136f32d7316d2f63c5174c2d1b4aba3a7a455955bf199', 'a94f0439-3cc5-4599-9e14-327934ce0c57', 'User2', 'Germany');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('819625ae-ea41-44f7-94e9-5a7cdd8a09bb', 'ACTIVE', 'germany3', 'User3.Germany@iaeste.org', '335513e2057b6ef9ba9fb0dfaef1705855b10187782cb3bf01fcec682837f4db', '00fcd10b-37f3-4597-9d88-23d823d7375a', 'User3', 'Germany');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('ffdca160-f94c-49ae-870a-a0b22e50db23', 'ACTIVE', 'germany4', 'User4.Germany@iaeste.org', '2f3924eeb7738724ae548a5a47ea4553f7de424a1c8181aae5f1eaada9a2ab56', 'fc5db0e0-46fa-4c4f-8162-ed635f308885', 'User4', 'Germany');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('f86e5987-b678-4e21-bb40-943a3776a772', 'ACTIVE', 'germany5', 'User5.Germany@iaeste.org', 'f9193d6185a170c11d08e7f00eb5f81206ad889ddb98d61e18fe673e19adfa47', '4603c12d-e752-486b-a5e3-8f2bcd47d4c7', 'User5', 'Germany');

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
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('ec484356-6f29-4e28-8e28-64e00a7ded15', 'ACTIVE', 'hungary1', 'User1.Hungary@iaeste.org', '4e757d5002ec67f1c8f0e8a9ad4aad71a2b8aa048c61409571191030704af17d', '61455e41-1bcb-4a1b-a68f-95547a900aef', 'User1', 'Hungary');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('3bab4a6e-bb27-4b1b-878c-c2406364edc7', 'ACTIVE', 'hungary2', 'User2.Hungary@iaeste.org', '999bfde5b2b68cc2f9da40e612c3a6305ceb5472da1a44f5e5d252ab74ce6e5b', '2f39df16-f1d6-4895-9b71-7516a50a4c84', 'User2', 'Hungary');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('8ce04c0f-fdab-4897-9f79-d554bbaf80d6', 'ACTIVE', 'hungary3', 'User3.Hungary@iaeste.org', '609b246c318c79dee74a035ca01f3466a7c6d44881a20847ec1815908abd0938', '62f0f9e7-f2bf-426d-abff-3e18e175adfb', 'User3', 'Hungary');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('3e9f6834-1dc9-4759-8dab-584d505e8f3b', 'ACTIVE', 'hungary4', 'User4.Hungary@iaeste.org', 'ea7cbe5836273db72a97e13542b5a579146856e68e2889472dd5ab5012b9973f', 'efe8186c-f10c-4c52-84e9-d54e48653473', 'User4', 'Hungary');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('ed50b96a-5618-4aae-af61-9645ac86231c', 'ACTIVE', 'hungary5', 'User5.Hungary@iaeste.org', 'e5dcb1a8046ea99fccfd05e5a8a540bad061ae001244256c02ddf0255d8b5246', '94f5b68a-62d0-4922-813c-d866711f2d40', 'User5', 'Hungary');

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
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('5abd951a-c14a-460c-a71a-9f41ca731d2c', 'ACTIVE', 'poland1', 'User1.Poland@iaeste.org', '1c566cd798e1bab70a4bc3d76c9c39ec8476c4f8e9b57db4b86a633f43722c88', '2f8af0f9-6f1b-455b-ba59-8a059814612d', 'User1', 'Poland');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('86b75961-2c70-4468-9f6c-f6f3094573dc', 'ACTIVE', 'poland2', 'User2.Poland@iaeste.org', 'a9169ee9a0d86f7a2d9a767f40d62c203359c3d9181288afbf1d1b966d569eba', '3283370f-fdd7-4890-86b9-e5474254c2f4', 'User2', 'Poland');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('1ff46c85-bd16-42a3-af81-60f08a952426', 'ACTIVE', 'poland3', 'User3.Poland@iaeste.org', '199591f357aadaff38f13603511e7ea096043f3536d9684040a9c39ceb87fb9a', '39c602c7-0370-42c9-9aa0-bb8c5b5e30f8', 'User3', 'Poland');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('acbf3011-40a6-4c0b-af91-f882a13c5895', 'ACTIVE', 'poland4', 'User4.Poland@iaeste.org', 'e6365025db4e6684a040d6d3939b9d2f43069a4ceebe4869ee45d8afaaf76036', 'bb2bd2fa-8aeb-4521-aa60-94f90465ec4e', 'User4', 'Poland');
insert into users (external_id, status, username, alias, password, salt, firstname, lastname) values ('f635f833-2174-4170-9e1c-f763ed87e80a', 'ACTIVE', 'poland5', 'User5.Poland@iaeste.org', 'd59785d96cbcf9498a52871f75f4b46e2895cfa1962dab6e6781ea32a0c5251f', '9743cac8-795c-4488-943e-c1f249c3176d', 'User5', 'Poland');

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
