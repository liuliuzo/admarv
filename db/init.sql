INSERT INTO sys_user (user_name, real_name, password, salt, avatar, birthday, sex, email, phone, org_code, status, del_flag, third_id, third_type, telephone,fb_token, create_by, create_time, update_by, update_time)
VALUES ('john_doe', 'John Doe', 'password123', 'salty123', 'https://example.com/avatar.jpg', '1990-01-01', 1, 'john@example.com', '1234567890', 'org123', 1, 0, 'third123', 'type1', '9876543210', 'access token','admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP);
INSERT INTO sys_user (user_name, real_name, password, salt, avatar, birthday, sex, email, phone, org_code, status, del_flag, third_id, third_type, telephone,fb_token, create_by, create_time, update_by, update_time)
VALUES ('jane_smith', 'Jane Smith', 'p@ssw0rd', 'salty456', 'https://example.com/avatar2.jpg', '1985-05-10', 2, 'jane@example.com', '9876543210', 'org456', 1, 0, 'third456', 'type2', '1234567890','access token','admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP);

INSERT INTO `lead_gen` (`lead_id`,`name`, `email`, `cntct`, `crte_tm`, `lead_stat`, `regn`, `flwp_stat`, `rsrc`, `form_id`,`owner`, `del_flag`)
VALUES ('001','Jane Smith', 'jane.smith@example.com', '9876543210', '2023-07-07 11:00:00', 'Potential', 'Canada', 'In Progress', 'Referral', '456DEF','liuliu', 0);
INSERT INTO `lead_gen` (`lead_id`,`name`, `email`, `cntct`, `crte_tm`, `lead_stat`, `regn`, `flwp_stat`, `rsrc`, `form_id`,`owner`,`del_flag`)
VALUES ('002','Alice Johnson', 'alice.johnson@example.com', '5554443333', '2023-07-07 12:00:00', 'Qualified', 'Australia', 'Completed', 'Advertisement', '789GHI','liuliu', 0);

INSERT INTO campaigns_insights (account_currency, account_id, account_name, actions, ad_name, adset_id, adset_name, clicks, country, cpc)
VALUES ('USD', '123456789', 'Example Account', 10, 'Example Ad', '987654321', 'Example Ad Set', 100, 'United States', 1.23);
INSERT INTO campaigns_insights (account_currency, account_id, account_name, actions, ad_name, adset_id, adset_name, clicks, country, cpc)
VALUES ('EUR', '987654321', 'Sample Account', 20, 'Sample Ad', '123456789', 'Sample Ad Set', 200, 'Germany', 0.98);