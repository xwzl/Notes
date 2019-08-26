/*
 Navicat Premium Data Transfer

 Source Server         : 172.16.159.20
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 172.16.159.20:3306
 Source Schema         : xuweizhi

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 03/07/2019 10:24:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for absence
-- ----------------------------
DROP TABLE IF EXISTS `absence`;
CREATE TABLE `absence`  (
  `student_id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`student_id`, `date`) USING BTREE,
  CONSTRAINT `absence_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of absence
-- ----------------------------
INSERT INTO `absence` VALUES (3, '2012-09-03');
INSERT INTO `absence` VALUES (5, '2012-09-03');
INSERT INTO `absence` VALUES (10, '2012-09-06');
INSERT INTO `absence` VALUES (10, '2012-09-09');
INSERT INTO `absence` VALUES (17, '2012-09-07');
INSERT INTO `absence` VALUES (20, '2012-09-07');

-- ----------------------------
-- Table structure for grade_event
-- ----------------------------
DROP TABLE IF EXISTS `grade_event`;
CREATE TABLE `grade_event`  (
  `date` date NOT NULL,
  `category` enum('T','Q') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `event_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade_event
-- ----------------------------
INSERT INTO `grade_event` VALUES ('2012-09-03', 'Q', 1);
INSERT INTO `grade_event` VALUES ('2012-09-06', 'Q', 2);
INSERT INTO `grade_event` VALUES ('2012-09-09', 'T', 3);
INSERT INTO `grade_event` VALUES ('2012-09-16', 'Q', 4);
INSERT INTO `grade_event` VALUES ('2012-09-23', 'Q', 5);
INSERT INTO `grade_event` VALUES ('2012-10-01', 'T', 6);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `member_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `last_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `suffix` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `expiration` date NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `street` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `zip` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `interests` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'Solow', 'Jeanne', NULL, '2013-11-15', 'jeanne_s@earth.com', '16 Ludden Dr.', 'Austin', 'TX', '33347', '964-665-8735', 'Great Depression,Spanish-American War,Westward movement,Civil Rights,Sports');
INSERT INTO `member` VALUES (2, 'Lundsten', 'August', NULL, '2015-08-11', 'august.lundsten@pluto.com', '13 O\'Malley St.', 'Bogalusa', 'LA', '96127', '196-481-0897', 'Korean War,World War I,Civil Rights');
INSERT INTO `member` VALUES (3, 'Erdmann', 'Kay', NULL, '2013-01-14', NULL, '42 Fremont Av.', 'Grenada', 'MI', '44953', '493-610-3215', 'Education,Roosevelt,Presidential politics');
INSERT INTO `member` VALUES (4, 'Arbogast', 'Ruth', NULL, '2015-02-10', 'arbogast.ruth@mars.net', '95 Carnwood Rd.', 'Paris', 'IL', '31483', '539-907-5225', 'Colonial period,Social Security,Constitution,Lincoln,Gold rush');
INSERT INTO `member` VALUES (5, 'Dorfman', 'Carol', NULL, '2016-09-15', 'c.dorfman@uranus.net', '21 Winnemac Av.', 'Trenton', 'MO', '99887', '597-290-3955', 'Revolutionary War,Cold War,immigration');
INSERT INTO `member` VALUES (6, 'Eliason', 'Jessica', NULL, '2013-12-27', 'jessica.eliason@pluto.com', '60 Century Av.', 'Osborne', 'KS', '63198', '896-268-0569', 'World War I,Korean War');
INSERT INTO `member` VALUES (7, 'Sawyer', 'Dennis', NULL, '2014-11-21', 's_dennis@jupiter.com', '48 Jenifer St.', 'Denver', 'CO', '23728', '775-983-4182', 'Industrial revolution,Great Depression,Armed services,Education');
INSERT INTO `member` VALUES (8, 'Phillips', 'Donald', 'III', '2013-03-05', 'd_phillips@neptune.org', '13 Lake St.', 'San Antonio', 'TX', '87154', '898-166-9341', 'Revolutionary War,Abolition,Armed services,Lincoln,Gold rush');
INSERT INTO `member` VALUES (9, 'Anderson', 'Marcia', NULL, '2014-02-10', NULL, '31 Bigelow Rd.', 'Cedar Rapids', 'IA', '45150', '445-539-3384', 'Armed services,immigration,Gold rush,Cold War,Education');
INSERT INTO `member` VALUES (10, 'Kilgore', 'Leonard', NULL, '2016-05-09', NULL, '19 Pagelow Ln.', 'Beloit', 'WI', '16235', '963-699-2715', 'Spanish-American War,Founding fathers,World War I,Presidential politics');
INSERT INTO `member` VALUES (11, 'Lederman', 'Judith', NULL, '2017-07-09', 'judith_lederman@mercury.net', '218 N. Sherman Av.', 'Appleton', 'WI', '63330', '380-077-6632', 'World War II,Great Depression,War of 1812,Spanish-American War,Vietnam War');
INSERT INTO `member` VALUES (12, 'Bodell', 'Bonnie', NULL, '2015-02-10', NULL, '674 Marledge St.', 'Geneva', 'IL', '34619', '790-449-4910', 'War of 1812,Spanish-American War,Korean War,World War I,Constitution');
INSERT INTO `member` VALUES (13, 'Reusch', 'Diane', NULL, '2013-03-14', 'reusch_d@venus.org', '111 Dogwood Pl.', 'Burlington', 'IL', '03211', '964-681-8054', 'Vietnam War,Roosevelt');
INSERT INTO `member` VALUES (14, 'Hilby', 'Bernard', NULL, '2014-06-04', 'bernard.hilby@saturn.org', '111 West St.', 'Clinton', 'IA', '46161', '333-263-2057', 'Westward movement,Cold War');
INSERT INTO `member` VALUES (15, 'Propper', 'Harvey', NULL, '2014-04-03', 'harvey_propper@pluto.com', '853 Van Hise Av.', 'Lansing', 'MI', '39980', '184-832-6901', 'Industrial revolution,Founding fathers,Great Depression,Constitution,Presidential politics');
INSERT INTO `member` VALUES (16, 'Michaels', 'Amanda', NULL, '2015-10-01', NULL, '805 Chase Blvd.', 'South Bend', 'IN', '58751', '295-458-1334', 'Abolition,Roosevelt,Vietnam War,Social Security');
INSERT INTO `member` VALUES (17, 'Hagler', 'Carolyn', NULL, '2016-10-13', 'hagler.c@mars.net', '834 Woods Edge Rd.', 'Fort Wayne', 'IN', '65594', '828-991-7354', 'Lincoln,Civil Rights,Gold rush,Revolutionary War,Civil War');
INSERT INTO `member` VALUES (18, 'York', 'Mark', 'II', '2012-08-24', 'york_mark@earth.com', '449 Meyer Av.', 'Peoria', 'IL', '90108', '845-137-2175', 'Cold War,immigration');
INSERT INTO `member` VALUES (19, 'Feit', 'Daniel', NULL, '2014-05-04', 'daniel.feit@pluto.com', '181 E. Washington Av.', 'Stockton', 'CA', '90255', '167-064-7158', 'Colonial period,Vietnam War,Korean War,Presidential politics');
INSERT INTO `member` VALUES (20, 'Overland', 'Roland', NULL, NULL, NULL, '949 Mineral Point Rd.', 'Minot', 'ND', '45600', '232-732-1438', 'Gold rush,immigration,Spanish-American War');
INSERT INTO `member` VALUES (21, 'Lacke', 'Bruce', NULL, '2012-10-10', NULL, '617 West Lawn Av.', 'Aberdeen', 'SD', '97919', '171-132-0360', 'Vietnam War,Education');
INSERT INTO `member` VALUES (22, 'Hurst', 'Sally', NULL, '2013-12-31', 'hurst.s@mars.net', '201 Laurel Ln.', 'Warren', 'MN', '37373', '553-257-4344', 'Spanish-American War');
INSERT INTO `member` VALUES (23, 'Pitas', 'Clifton', NULL, '2016-02-22', 'clifton_p@saturn.org', '713 Quincy Av.', 'Duluth', 'MN', '84708', '857-652-7766', 'Industrial revolution,Great Depression,Armed services');
INSERT INTO `member` VALUES (24, 'Wheeler', 'Mae', NULL, '2014-05-26', 'mae.wheeler@venus.org', '238 Mendota Ct.', 'Atlanta', 'GA', '78446', '636-995-4174', 'Education,Cold War,Lincoln,Social Security');
INSERT INTO `member` VALUES (25, 'Nelson', 'Anthony', NULL, '2017-06-10', 'nelson.anthony@venus.org', '739 Hayes Rd.', 'Akron', 'OH', '21256', '844-967-6564', 'Lincoln,Roosevelt,Spanish-American War,World War II');
INSERT INTO `member` VALUES (26, 'Jones', 'Richard', NULL, '2013-01-27', NULL, '206 Colladay Point Dr.', 'Syracuse', 'NY', '01227', '839-320-5769', 'Roosevelt,Abolition,Social Security');
INSERT INTO `member` VALUES (27, 'Moorhead', 'Susan', NULL, '2013-05-31', 'susan.m@venus.org', '462 Raymond Rd.', 'New York', 'NY', '82057', '256-439-4270', 'Revolutionary War,Spanish-American War,World War I,Founding fathers,Gold rush');
INSERT INTO `member` VALUES (28, 'Lugaro', 'Ken', NULL, '2016-06-15', 'ken_l@venus.org', '100 W. Gorham', 'Caribou', 'ME', '72410', '312-149-2847', 'Industrial revolution');
INSERT INTO `member` VALUES (29, 'Hennessy', 'Jim', NULL, '2014-01-08', NULL, '222 Miami Pass', 'Brattleboro', 'NH', '60633', '665-455-5472', 'Founding fathers');
INSERT INTO `member` VALUES (30, 'Pernetti', 'Jeffrey', NULL, '2015-05-24', 'jeffrey_pernetti@saturn.org', '627 Laramie Ct.', 'Montpelier', 'VT', '20537', '603-395-5806', 'Revolutionary War');
INSERT INTO `member` VALUES (31, 'Colby', 'Amy', NULL, '2017-03-01', 'colby_a@saturn.org', '275 Big Sky Dr.', 'Pottsville', 'PA', '24191', '983-484-0425', 'World War I,immigration,Vietnam War,Constitution');
INSERT INTO `member` VALUES (32, 'Vincent', 'Edward', NULL, NULL, 'v.edward@saturn.org', '960 Brody Dr.', 'Elkins', 'WV', '99473', '631-122-4209', 'Spanish-American War,Founding fathers');
INSERT INTO `member` VALUES (33, 'Nemke', 'Joel', NULL, '2015-12-19', 'joel.nemke@uranus.net', '243 Windsor Rd.', 'Greensbora', 'NC', '24400', '801-878-6704', 'Great Depression,Civil War');
INSERT INTO `member` VALUES (34, 'Gjertson', 'Herbert', NULL, '2016-01-07', 'herbert_gjertson@mars.net', '279 Clark St.', 'Lake City', 'SC', '59674', '477-095-3642', 'Founding fathers');
INSERT INTO `member` VALUES (35, 'Clift', 'Melissa', NULL, '2015-12-02', 'clift.m@uranus.net', '279 Vernon Av.', 'Waycross', 'GA', '38817', '172-030-9435', 'Spanish-American War');
INSERT INTO `member` VALUES (36, 'Neumeyer', 'Rick', NULL, '2017-05-01', 'n_rick@uranus.net', '664 Sunrise Tr.', 'Fort Myers', 'FL', '25372', '399-169-0661', 'World War I,Education');
INSERT INTO `member` VALUES (37, 'Hughes', 'Max', 'Jr.', '2016-09-16', NULL, '814 Ridge Rd.', 'Huntsville', 'AL', '84310', '374-364-4212', 'Vietnam War,World War II');
INSERT INTO `member` VALUES (38, 'Haug', 'Linda', NULL, '2017-01-22', 'linda.haug@pluto.com', '746 White Aspen Rd.', 'Red Bank', 'TN', '22540', '948-014-3619', 'Revolutionary War,Sports,Civil War');
INSERT INTO `member` VALUES (39, 'Mitchell', 'Eugene', NULL, '2017-04-08', 'mitchell_e@saturn.org', '38 Rustling Oaks Ln.', 'Hazard', 'KY', '66948', '299-337-0004', 'Presidential politics,World War II,Great Depression,Lincoln,Roosevelt');
INSERT INTO `member` VALUES (40, 'Brower', 'Paul', NULL, '2016-10-04', 'paul_brower@saturn.org', '238 Barber Dr.', 'Ocean City', 'MD', '55179', '821-905-7597', 'Armed services,Gold rush,Civil Rights');
INSERT INTO `member` VALUES (41, 'Welch', 'Jacob', NULL, NULL, 'welch.jacob@jupiter.com', '512 Madison St.', 'Wilmington', 'NJ', '10507', '913-715-6335', 'Westward movement');
INSERT INTO `member` VALUES (42, 'Schenk', 'Cindy', NULL, '2013-03-22', NULL, '542 W. Hudson Rd.', 'Waterbury', 'CT', '98847', '681-415-6637', 'Founding fathers,Education,Great Depression,Armed services');
INSERT INTO `member` VALUES (43, 'Brown', 'Gary', NULL, '2016-11-17', 'gary_brown@pluto.com', '342 Marathon Dr.', 'Providence', 'RI', '14536', '612-355-2509', 'Cold War,Founding fathers,Civil Rights');
INSERT INTO `member` VALUES (44, 'Williams', 'Darrell', NULL, '2015-03-31', 'w_darrell@uranus.net', '228 E. Johnson St.', 'Springfield', 'MA', '90181', '878-397-4390', 'Spanish-American War,Revolutionary War,Presidential politics,Sports');
INSERT INTO `member` VALUES (45, 'Block', 'Christopher', NULL, '2015-07-03', 'christopher_b@mercury.net', '606 Cumberland Ln.', 'Bruneau', 'ID', '58790', '015-680-8696', 'Colonial period,Spanish-American War,Civil Rights,Education,Presidential politics');
INSERT INTO `member` VALUES (46, 'Thompson', 'Joan', NULL, '2015-04-26', 'joan_thompson@venus.org', '182 Spaight St.', 'Roy', 'NM', '25129', '798-188-9411', 'Colonial period,Presidential politics,Abolition,Civil War,Roosevelt');
INSERT INTO `member` VALUES (47, 'Bookstaff', 'Barbara', NULL, '2014-10-07', 'bookstaff.b@earth.com', '289 Lancashier Ct.', 'Durango', 'CO', '17762', '175-857-5726', 'Civil War,Industrial revolution');
INSERT INTO `member` VALUES (48, 'Kirby', 'Timothy', NULL, NULL, NULL, '298 Hollister Av.', 'Kayenta', 'AZ', '82432', '844-673-6051', 'Colonial period,immigration,Civil War');
INSERT INTO `member` VALUES (49, 'Simmons', 'David', NULL, '2016-08-31', 'simmons.david@mercury.net', '793 S. Henry St.', 'Trona', 'CA', '35986', '645-327-1588', 'Civil War,Colonial period');
INSERT INTO `member` VALUES (50, 'Renko', 'Jan', NULL, '2017-04-27', 'jan_r@earth.com', '296 Dunn Av.', 'Fallon', 'NV', '04507', '344-923-2885', 'Lincoln,Founding fathers,War of 1812');
INSERT INTO `member` VALUES (51, 'Godfriaux', 'Harlan', NULL, '2012-12-20', 'godfriaux_harlan@earth.com', '1100 State Rd.', 'Provo', 'UT', '04896', '077-406-7491', 'World War I,Social Security');
INSERT INTO `member` VALUES (52, 'Little', 'Margaret', NULL, '2012-10-16', NULL, '8702 Gannon Rd.', 'Worland', 'WY', '46326', '817-461-1949', 'World War I,Civil Rights,Armed services');
INSERT INTO `member` VALUES (53, 'Weiss', 'Nicole', NULL, '2015-11-20', 'nicole.w@mars.net', '4488 E. Harmony Dr.', 'Burns', 'OR', '92532', '898-181-7231', 'World War II,Sports,Spanish-American War,World War I,Civil Rights');
INSERT INTO `member` VALUES (54, 'Olson', 'Maureen', NULL, '2014-06-07', 'maureen_olson@venus.org', '8388 W. Holt Rd.', 'Moses Lake', 'WA', '32534', '936-060-5258', 'War of 1812,Roosevelt,Great Depression,Education');
INSERT INTO `member` VALUES (55, 'Cutrell', 'Michelle', NULL, '2014-02-20', NULL, '1702 Lynne Tr.', 'Crow Agency', 'MT', '26764', '454-457-6125', 'Great Depression,Roosevelt,Korean War,Social Security');
INSERT INTO `member` VALUES (56, 'Matthews', 'Bill', 'Sr.', '2015-09-15', 'matthews.b@saturn.org', '9902 Mound St.', 'Fairbanks', 'AK', '54214', '743-150-3797', 'Colonial period,Revolutionary War');
INSERT INTO `member` VALUES (57, 'Desmond', 'Clifford', NULL, '2015-06-21', 'clifford.d@mars.net', '4939 Clemons Av.', 'Kalaheo', 'HI', '26295', '776-381-1029', 'Revolutionary War,World War II,Sports');
INSERT INTO `member` VALUES (58, 'Karn', 'Simon', NULL, '2016-06-25', 'k.simon@mars.net', '5664 Northridge Ter.', 'Detroit', 'MI', '34483', '712-898-0397', 'Social Security,Armed services');
INSERT INTO `member` VALUES (59, 'Puntillo', 'Cheryl', NULL, '2016-12-08', 'puntillo.c@saturn.org', '1270 Kupfer Rd.', 'Los Angeles', 'CA', '66350', '057-300-6645', 'Education,Cold War,Lincoln,Great Depression,Civil War');
INSERT INTO `member` VALUES (60, 'Camosy', 'Alan', NULL, '2015-08-23', 'alan.camosy@pluto.com', '15 Kenwood Cir.', 'Dallas', 'TX', '49786', '443-837-6502', 'Colonial period,Armed services');
INSERT INTO `member` VALUES (61, 'Fassbinder', 'Valerie', NULL, '2013-07-16', NULL, '5364 Kingston Dr.', 'Chicago', 'IL', '92813', '316-294-6204', 'Social Security');
INSERT INTO `member` VALUES (62, 'Mcbride', 'Zachary', NULL, '2015-05-28', 'mcbride.zachary@venus.org', '7849 Martinsville Rd.', 'Philadelphia', 'PA', '44712', '041-786-7072', 'Sports,Founding fathers,Civil Rights,Great Depression');
INSERT INTO `member` VALUES (63, 'Artel', 'Mike', NULL, '2016-04-16', 'mike_artel@venus.org', '4264 Lovering Rd.', 'Miami', 'FL', '12777', '075-961-0712', 'Civil Rights,Education,Revolutionary War');
INSERT INTO `member` VALUES (64, 'Grum', 'Brenda', NULL, '2017-02-28', 'brenda.g@neptune.org', '8835 School Rd.', 'New Orleans', 'LA', '88929', '119-173-2910', 'Social Security,Korean War,Civil War,Presidential politics,Roosevelt');
INSERT INTO `member` VALUES (65, 'Schauer', 'Alma', NULL, '2013-04-25', 'alma_schauer@venus.org', '9625 Topeka Tr.', 'Mobile', 'AL', '87779', '520-883-0715', 'Cold War,Industrial revolution,Gold rush,Colonial period');
INSERT INTO `member` VALUES (66, 'Kohn', 'Jane', NULL, '2016-04-03', 'kohn.j@mercury.net', '4961 Pertzborn Dr.', 'Milwaukee', 'WI', '56155', '248-993-0148', 'War of 1812');
INSERT INTO `member` VALUES (67, 'Page', 'Sarah', NULL, '2015-02-06', 'p_sarah@saturn.org', '34 Harvest Ln.', 'St. Paul', 'MN', '02590', '520-343-3572', 'Vietnam War,immigration,Industrial revolution');
INSERT INTO `member` VALUES (68, 'Popham', 'Robert', NULL, '2015-06-11', NULL, '26 Arizona Cir.', 'Portland', 'OR', '60820', '896-249-4929', 'Westward movement,Constitution,Armed services,Civil Rights,Abolition');
INSERT INTO `member` VALUES (69, 'Segovia', 'Brian', NULL, '2017-06-15', 'brian_s@mars.net', '7814 Indian Mound Dr.', 'Seattle', 'WA', '31340', '198-008-3769', 'Constitution,Industrial revolution,Vietnam War,Colonial period,Sports');
INSERT INTO `member` VALUES (70, 'Freeman', 'Vincent', NULL, '2014-07-07', 'freeman.vincent@venus.org', '7 Nightingale Ct.', 'Cody', 'WY', '45797', '820-681-3578', 'World War II,Presidential politics');
INSERT INTO `member` VALUES (71, 'Vines', 'Toby', NULL, '2013-04-18', 't.vines@pluto.com', '2750 Oakview Dr.', 'Coral Gables', 'FL', '20248', '718-155-2528', 'Abolition,Gold rush,World War II');
INSERT INTO `member` VALUES (72, 'Walton', 'Philp', NULL, '2015-10-09', NULL, '8527 Manitowoc Pkwy.', 'Lincoln', 'NE', '68799', '112-725-0105', 'Social Security,Founding fathers');
INSERT INTO `member` VALUES (73, 'Abbs', 'Ron', NULL, '2016-10-25', 'ron.abbs@neptune.org', '77 Beech Ct.', 'Harrisburg', 'PA', '61511', '502-098-0216', 'Revolutionary War,Spanish-American War,Colonial period,Gold rush,Lincoln');
INSERT INTO `member` VALUES (74, 'Grogan', 'Vladimir', NULL, '2012-10-25', 'vladimir_grogan@earth.com', '3263 Gilbert Rd.', 'Ithaca', 'NY', '99357', '332-511-5038', 'Great Depression,Spanish-American War');
INSERT INTO `member` VALUES (75, 'Elgar', 'Clarence', 'Jr.', '2016-03-22', 'clarence.elgar@mercury.net', '4162 Lakewood Blvd.', 'Lewiston', 'ME', '48157', '992-123-4497', 'Sports,Armed services');
INSERT INTO `member` VALUES (76, 'Johnson', 'Robin', NULL, '2017-06-08', 'robin_j@neptune.org', '5606 McKenna Blvd.', 'Lynchburg', 'VA', '22514', '518-780-8914', 'Constitution,Civil War,Cold War,immigration,Civil Rights');
INSERT INTO `member` VALUES (77, 'Damron', 'Sandra', NULL, '2013-07-05', 's.damron@saturn.org', '5024 Craig Av.', 'Lima', 'OH', '10716', '132-700-4450', 'Sports');
INSERT INTO `member` VALUES (78, 'Dahl', 'Andrew', NULL, '2016-12-27', 'andrew.dahl@venus.org', '9658 Lynchburg Tr.', 'Fort Wayne', 'IN', '49606', '169-982-0224', 'War of 1812');
INSERT INTO `member` VALUES (79, 'Albright', 'Warren', NULL, NULL, NULL, '3740 Privett Rd.', 'Dodge City', 'KS', '08952', '364-454-4966', 'Social Security,Revolutionary War,Colonial period,Vietnam War');
INSERT INTO `member` VALUES (80, 'Beckett', 'Luther', NULL, '2014-06-06', 'luther.b@mars.net', '148 Greenbriar Dr.', 'Sonora', 'TX', '52841', '778-028-6040', 'Spanish-American War');
INSERT INTO `member` VALUES (81, 'Brooks', 'Carl', NULL, '2015-09-12', 'brooks_carl@jupiter.com', '8755 Dapin Rd.', 'Sarasota', 'FL', '19735', '514-906-3111', 'War of 1812,Vietnam War,Civil Rights,World War II,Gold rush');
INSERT INTO `member` VALUES (82, 'Edwards', 'John', NULL, '2012-09-12', 'john_edwards@venus.org', '2218 Heath Av.', 'Dothan', 'AL', '98158', '442-861-2459', 'Founding fathers,Spanish-American War,Korean War,Vietnam War');
INSERT INTO `member` VALUES (83, 'Brophy', 'Vickie', NULL, '2016-07-13', 'vickie_b@uranus.net', '1919 Jay Dr.', 'Alexandria', 'LA', '28794', '596-490-7991', 'War of 1812,Vietnam War,Korean War,Founding fathers');
INSERT INTO `member` VALUES (84, 'Aronson', 'Nancy', NULL, '2017-06-16', 'nancy.a@mercury.net', '1254 Stagecoach Tr.', 'Ashland', 'KY', '43979', '414-089-0344', 'War of 1812,Civil War');
INSERT INTO `member` VALUES (85, 'Fiorelli', 'Neil', NULL, '2015-11-07', 'fiorelli.neil@mercury.net', '5599 Constitution Dr.', 'Ashland', 'WI', '85083', '379-922-7719', 'Social Security,Spanish-American War,Cold War,World War I');
INSERT INTO `member` VALUES (86, 'Robson', 'Chris', NULL, '2012-10-28', 'chris_r@venus.org', '8229 Ravenswood Rd.', 'Chicago', 'IL', '73252', '052-949-4117', 'Korean War,World War I');
INSERT INTO `member` VALUES (87, 'Morris', 'Andrea', NULL, '2015-03-23', NULL, '530 W. Wilson St.', 'New York', 'NY', '45606', '158-240-4142', 'World War II');
INSERT INTO `member` VALUES (88, 'Pierson', 'Stanley', NULL, NULL, 'pierson.s@jupiter.com', '3810 Northbrook Dr.', 'Los Angeles', 'CA', '51336', '157-304-8749', 'World War I,Sports');
INSERT INTO `member` VALUES (89, 'Garner', 'Steve', NULL, '2012-08-03', 'g.steve@pluto.com', '48 Walworth Ct.', 'Denver', 'CO', '96363', '765-848-4515', 'Gold rush');
INSERT INTO `member` VALUES (90, 'Stehle', 'Joseph', NULL, '2015-11-13', 's.joseph@venus.org', '3688 N. Franklin St.', 'San Antonio', 'TX', '92419', '217-542-0789', 'Industrial revolution,Lincoln,Gold rush,Civil War');
INSERT INTO `member` VALUES (91, 'Downing', 'Fred', NULL, '2016-05-23', 'fred_downing@mercury.net', '54 Klamer Rd.', 'Austin', 'TX', '81042', '601-143-5252', 'Great Depression');
INSERT INTO `member` VALUES (92, 'Gladden', 'Jerome', NULL, '2016-06-19', NULL, '62 Gust Rd.', 'Detroit', 'MI', '74586', '083-144-0721', 'Korean War,Cold War,Abolition,Spanish-American War');
INSERT INTO `member` VALUES (93, 'Forman', 'Kevin', NULL, '2013-08-25', 'kevin.forman@neptune.org', '60 Drake St.', 'Miami', 'FL', '92344', '259-329-7863', 'Presidential politics');
INSERT INTO `member` VALUES (94, 'Harrington', 'James', NULL, '2017-07-19', 'james.harrington@earth.com', '155 Admiral Dr.', 'Atlanta', 'GA', '75541', '677-105-5966', 'Roosevelt,Civil War,Lincoln,Civil Rights,immigration');
INSERT INTO `member` VALUES (95, 'Alvis', 'Michael', 'IV', '2014-04-17', 'alvis_michael@mars.net', '176 Sand Hill Rd.', 'Philadelphia', 'PA', '38728', '203-319-3633', 'Education,War of 1812,World War II,Armed services');
INSERT INTO `member` VALUES (96, 'Caputo', 'Eileen', NULL, '2014-01-30', 'caputo_e@uranus.net', '151 Westport Rd.', 'Seattle', 'WA', '50175', '781-213-8580', 'World War II,Westward movement');
INSERT INTO `member` VALUES (97, 'Harrison', 'Marita', NULL, '2014-11-07', 'marita_harrison@earth.com', '64 Delaware Dr.', 'Portland', 'OR', '57577', '927-099-6116', 'Great Depression,Founding fathers,Gold rush,Korean War');
INSERT INTO `member` VALUES (98, 'Smith', 'Laura', NULL, '2016-05-27', 's.laura@neptune.org', '5 Post Rd.', 'San Francisco', 'CA', '75247', '724-664-7207', 'Armed services,immigration,Civil Rights,Great Depression,Vietnam War');
INSERT INTO `member` VALUES (99, 'Sprague', 'Earl', NULL, '2014-04-18', NULL, '145 N. Thompson Dr.', 'Oakland', 'CA', '12801', '678-463-3510', 'Lincoln,Korean War,Westward movement');
INSERT INTO `member` VALUES (100, 'Worthington', 'Ralph', NULL, '2015-05-01', 'ralph_worthington@jupiter.com', '25 Upman St.', 'Laramie', 'WY', '49984', '126-109-9886', 'Civil Rights,War of 1812,Korean War,Sports,Colonial period');
INSERT INTO `member` VALUES (101, 'Corning', 'Sonya', NULL, '2016-09-16', 'sonya.c@jupiter.com', '14 Crown Rd.', 'Lincoln', 'NE', '76293', '835-693-7968', 'Civil War,Sports,Armed services,Spanish-American War,Social Security');
INSERT INTO `member` VALUES (102, 'Clark', 'Dale', NULL, '2017-08-23', NULL, '958 Sigmont Blvd.', 'Fort Worth', 'TX', '83720', '365-784-5634', 'Vietnam War,Civil Rights,Roosevelt,Lincoln');

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`  (
  `pict_id` int(11) NOT NULL,
  `pict_data` mediumblob NULL,
  PRIMARY KEY (`pict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for president
-- ----------------------------
DROP TABLE IF EXISTS `president`;
CREATE TABLE `president`  (
  `last_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `suffix` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `birth` date NOT NULL,
  `death` date NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of president
-- ----------------------------
INSERT INTO `president` VALUES ('Washington', 'George', NULL, 'Wakefield', 'VA', '1732-02-22', '1799-12-14');
INSERT INTO `president` VALUES ('Adams', 'John', NULL, 'Braintree', 'MA', '1735-10-30', '1826-07-04');
INSERT INTO `president` VALUES ('Jefferson', 'Thomas', NULL, 'Albemarle County', 'VA', '1743-04-13', '1826-07-04');
INSERT INTO `president` VALUES ('Madison', 'James', NULL, 'Port Conway', 'VA', '1751-03-16', '1836-06-28');
INSERT INTO `president` VALUES ('Monroe', 'James', NULL, 'Westmoreland County', 'VA', '1758-04-28', '1831-07-04');
INSERT INTO `president` VALUES ('Adams', 'John Quincy', NULL, 'Braintree', 'MA', '1767-07-11', '1848-02-23');
INSERT INTO `president` VALUES ('Jackson', 'Andrew', NULL, 'Waxhaw settlement', 'SC', '1767-03-15', '1845-06-08');
INSERT INTO `president` VALUES ('Van Buren', 'Martin', NULL, 'Kinderhook', 'NY', '1782-12-05', '1862-07-24');
INSERT INTO `president` VALUES ('Harrison', 'William H.', NULL, 'Berkeley', 'VA', '1773-02-09', '1841-04-04');
INSERT INTO `president` VALUES ('Tyler', 'John', NULL, 'Greenway', 'VA', '1790-03-29', '1862-01-18');
INSERT INTO `president` VALUES ('Polk', 'James K.', NULL, 'Pineville', 'NC', '1795-11-02', '1849-06-15');
INSERT INTO `president` VALUES ('Taylor', 'Zachary', NULL, 'Orange County', 'VA', '1784-11-24', '1850-07-09');
INSERT INTO `president` VALUES ('Fillmore', 'Millard', NULL, 'Locke', 'NY', '1800-01-07', '1874-03-08');
INSERT INTO `president` VALUES ('Pierce', 'Franklin', NULL, 'Hillsboro', 'NH', '1804-11-23', '1869-10-08');
INSERT INTO `president` VALUES ('Buchanan', 'James', NULL, 'Mercersburg', 'PA', '1791-04-23', '1868-06-01');
INSERT INTO `president` VALUES ('Lincoln', 'Abraham', NULL, 'Hodgenville', 'KY', '1809-02-12', '1865-04-15');
INSERT INTO `president` VALUES ('Johnson', 'Andrew', NULL, 'Raleigh', 'NC', '1808-12-29', '1875-07-31');
INSERT INTO `president` VALUES ('Grant', 'Ulysses S.', NULL, 'Point Pleasant', 'OH', '1822-04-27', '1885-07-23');
INSERT INTO `president` VALUES ('Hayes', 'Rutherford B.', NULL, 'Delaware', 'OH', '1822-10-04', '1893-01-17');
INSERT INTO `president` VALUES ('Garfield', 'James A.', NULL, 'Orange', 'OH', '1831-11-19', '1881-09-19');
INSERT INTO `president` VALUES ('Arthur', 'Chester A.', NULL, 'Fairfield', 'VT', '1829-10-05', '1886-11-18');
INSERT INTO `president` VALUES ('Cleveland', 'Grover', NULL, 'Caldwell', 'NJ', '1837-03-18', '1908-06-24');
INSERT INTO `president` VALUES ('Harrison', 'Benjamin', NULL, 'North Bend', 'OH', '1833-08-20', '1901-03-13');
INSERT INTO `president` VALUES ('McKinley', 'William', NULL, 'Niles', 'OH', '1843-01-29', '1901-09-14');
INSERT INTO `president` VALUES ('Roosevelt', 'Theodore', NULL, 'New York', 'NY', '1858-10-27', '1919-01-06');
INSERT INTO `president` VALUES ('Taft', 'William H.', NULL, 'Cincinnati', 'OH', '1857-09-15', '1930-03-08');
INSERT INTO `president` VALUES ('Wilson', 'Woodrow', NULL, 'Staunton', 'VA', '1856-12-19', '1924-02-03');
INSERT INTO `president` VALUES ('Harding', 'Warren G.', NULL, 'Blooming Grove', 'OH', '1865-11-02', '1923-08-02');
INSERT INTO `president` VALUES ('Coolidge', 'Calvin', NULL, 'Plymouth Notch', 'VT', '1872-07-04', '1933-01-05');
INSERT INTO `president` VALUES ('Hoover', 'Herbert C.', NULL, 'West Branch', 'IA', '1874-08-10', '1964-10-20');
INSERT INTO `president` VALUES ('Roosevelt', 'Franklin D.', NULL, 'Hyde Park', 'NY', '1882-01-30', '1945-04-12');
INSERT INTO `president` VALUES ('Truman', 'Harry S', NULL, 'Lamar', 'MO', '1884-05-08', '1972-12-26');
INSERT INTO `president` VALUES ('Eisenhower', 'Dwight D.', NULL, 'Denison', 'TX', '1890-10-14', '1969-03-28');
INSERT INTO `president` VALUES ('Kennedy', 'John F.', NULL, 'Brookline', 'MA', '1917-05-29', '1963-11-22');
INSERT INTO `president` VALUES ('Johnson', 'Lyndon B.', NULL, 'Stonewall', 'TX', '1908-08-27', '1973-01-22');
INSERT INTO `president` VALUES ('Nixon', 'Richard M.', NULL, 'Yorba Linda', 'CA', '1913-01-09', '1994-04-22');
INSERT INTO `president` VALUES ('Ford', 'Gerald R.', NULL, 'Omaha', 'NE', '1913-07-14', '2006-12-26');
INSERT INTO `president` VALUES ('Carter', 'James E.', 'Jr.', 'Plains', 'GA', '1924-10-01', NULL);
INSERT INTO `president` VALUES ('Reagan', 'Ronald W.', NULL, 'Tampico', 'IL', '1911-02-06', '2004-06-05');
INSERT INTO `president` VALUES ('Bush', 'George H.W.', NULL, 'Milton', 'MA', '1924-06-12', NULL);
INSERT INTO `president` VALUES ('Clinton', 'William J.', NULL, 'Hope', 'AR', '1946-08-19', NULL);
INSERT INTO `president` VALUES ('Bush', 'George W.', NULL, 'New Haven', 'CT', '1946-07-06', NULL);
INSERT INTO `president` VALUES ('Obama', 'Barack H.', NULL, 'Honolulu', 'HI', '1961-08-04', NULL);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `student_id` int(10) UNSIGNED NOT NULL,
  `event_id` int(10) UNSIGNED NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`event_id`, `student_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `grade_event` (`event_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 1, 20);
INSERT INTO `score` VALUES (3, 1, 20);
INSERT INTO `score` VALUES (4, 1, 18);
INSERT INTO `score` VALUES (5, 1, 13);
INSERT INTO `score` VALUES (6, 1, 18);
INSERT INTO `score` VALUES (7, 1, 14);
INSERT INTO `score` VALUES (8, 1, 14);
INSERT INTO `score` VALUES (9, 1, 11);
INSERT INTO `score` VALUES (10, 1, 19);
INSERT INTO `score` VALUES (11, 1, 18);
INSERT INTO `score` VALUES (12, 1, 19);
INSERT INTO `score` VALUES (14, 1, 11);
INSERT INTO `score` VALUES (15, 1, 20);
INSERT INTO `score` VALUES (16, 1, 18);
INSERT INTO `score` VALUES (17, 1, 9);
INSERT INTO `score` VALUES (18, 1, 20);
INSERT INTO `score` VALUES (19, 1, 9);
INSERT INTO `score` VALUES (20, 1, 9);
INSERT INTO `score` VALUES (21, 1, 13);
INSERT INTO `score` VALUES (22, 1, 13);
INSERT INTO `score` VALUES (23, 1, 16);
INSERT INTO `score` VALUES (24, 1, 11);
INSERT INTO `score` VALUES (25, 1, 19);
INSERT INTO `score` VALUES (26, 1, 10);
INSERT INTO `score` VALUES (27, 1, 15);
INSERT INTO `score` VALUES (28, 1, 15);
INSERT INTO `score` VALUES (29, 1, 19);
INSERT INTO `score` VALUES (30, 1, 17);
INSERT INTO `score` VALUES (31, 1, 11);
INSERT INTO `score` VALUES (1, 2, 17);
INSERT INTO `score` VALUES (2, 2, 8);
INSERT INTO `score` VALUES (3, 2, 13);
INSERT INTO `score` VALUES (4, 2, 13);
INSERT INTO `score` VALUES (5, 2, 17);
INSERT INTO `score` VALUES (6, 2, 13);
INSERT INTO `score` VALUES (7, 2, 17);
INSERT INTO `score` VALUES (8, 2, 8);
INSERT INTO `score` VALUES (9, 2, 19);
INSERT INTO `score` VALUES (10, 2, 18);
INSERT INTO `score` VALUES (11, 2, 15);
INSERT INTO `score` VALUES (12, 2, 19);
INSERT INTO `score` VALUES (13, 2, 18);
INSERT INTO `score` VALUES (14, 2, 18);
INSERT INTO `score` VALUES (15, 2, 16);
INSERT INTO `score` VALUES (16, 2, 9);
INSERT INTO `score` VALUES (17, 2, 13);
INSERT INTO `score` VALUES (18, 2, 9);
INSERT INTO `score` VALUES (19, 2, 11);
INSERT INTO `score` VALUES (21, 2, 12);
INSERT INTO `score` VALUES (22, 2, 10);
INSERT INTO `score` VALUES (23, 2, 17);
INSERT INTO `score` VALUES (24, 2, 19);
INSERT INTO `score` VALUES (25, 2, 10);
INSERT INTO `score` VALUES (26, 2, 18);
INSERT INTO `score` VALUES (27, 2, 8);
INSERT INTO `score` VALUES (28, 2, 13);
INSERT INTO `score` VALUES (29, 2, 16);
INSERT INTO `score` VALUES (30, 2, 12);
INSERT INTO `score` VALUES (31, 2, 19);
INSERT INTO `score` VALUES (1, 3, 88);
INSERT INTO `score` VALUES (2, 3, 84);
INSERT INTO `score` VALUES (3, 3, 69);
INSERT INTO `score` VALUES (4, 3, 71);
INSERT INTO `score` VALUES (5, 3, 97);
INSERT INTO `score` VALUES (6, 3, 83);
INSERT INTO `score` VALUES (7, 3, 88);
INSERT INTO `score` VALUES (8, 3, 75);
INSERT INTO `score` VALUES (9, 3, 83);
INSERT INTO `score` VALUES (10, 3, 72);
INSERT INTO `score` VALUES (11, 3, 74);
INSERT INTO `score` VALUES (12, 3, 77);
INSERT INTO `score` VALUES (13, 3, 67);
INSERT INTO `score` VALUES (14, 3, 68);
INSERT INTO `score` VALUES (15, 3, 75);
INSERT INTO `score` VALUES (16, 3, 60);
INSERT INTO `score` VALUES (17, 3, 79);
INSERT INTO `score` VALUES (18, 3, 96);
INSERT INTO `score` VALUES (19, 3, 79);
INSERT INTO `score` VALUES (20, 3, 76);
INSERT INTO `score` VALUES (21, 3, 91);
INSERT INTO `score` VALUES (22, 3, 81);
INSERT INTO `score` VALUES (23, 3, 81);
INSERT INTO `score` VALUES (24, 3, 62);
INSERT INTO `score` VALUES (25, 3, 79);
INSERT INTO `score` VALUES (26, 3, 86);
INSERT INTO `score` VALUES (27, 3, 90);
INSERT INTO `score` VALUES (28, 3, 68);
INSERT INTO `score` VALUES (29, 3, 66);
INSERT INTO `score` VALUES (30, 3, 79);
INSERT INTO `score` VALUES (31, 3, 81);
INSERT INTO `score` VALUES (2, 4, 7);
INSERT INTO `score` VALUES (3, 4, 17);
INSERT INTO `score` VALUES (4, 4, 16);
INSERT INTO `score` VALUES (5, 4, 20);
INSERT INTO `score` VALUES (6, 4, 9);
INSERT INTO `score` VALUES (7, 4, 19);
INSERT INTO `score` VALUES (8, 4, 12);
INSERT INTO `score` VALUES (9, 4, 17);
INSERT INTO `score` VALUES (10, 4, 12);
INSERT INTO `score` VALUES (11, 4, 16);
INSERT INTO `score` VALUES (12, 4, 13);
INSERT INTO `score` VALUES (13, 4, 8);
INSERT INTO `score` VALUES (14, 4, 11);
INSERT INTO `score` VALUES (15, 4, 9);
INSERT INTO `score` VALUES (16, 4, 20);
INSERT INTO `score` VALUES (18, 4, 11);
INSERT INTO `score` VALUES (19, 4, 15);
INSERT INTO `score` VALUES (20, 4, 17);
INSERT INTO `score` VALUES (21, 4, 13);
INSERT INTO `score` VALUES (22, 4, 20);
INSERT INTO `score` VALUES (23, 4, 13);
INSERT INTO `score` VALUES (24, 4, 12);
INSERT INTO `score` VALUES (25, 4, 10);
INSERT INTO `score` VALUES (26, 4, 15);
INSERT INTO `score` VALUES (28, 4, 17);
INSERT INTO `score` VALUES (30, 4, 11);
INSERT INTO `score` VALUES (31, 4, 19);
INSERT INTO `score` VALUES (1, 5, 15);
INSERT INTO `score` VALUES (2, 5, 12);
INSERT INTO `score` VALUES (3, 5, 11);
INSERT INTO `score` VALUES (5, 5, 13);
INSERT INTO `score` VALUES (6, 5, 18);
INSERT INTO `score` VALUES (7, 5, 14);
INSERT INTO `score` VALUES (8, 5, 18);
INSERT INTO `score` VALUES (9, 5, 13);
INSERT INTO `score` VALUES (10, 5, 14);
INSERT INTO `score` VALUES (11, 5, 18);
INSERT INTO `score` VALUES (12, 5, 8);
INSERT INTO `score` VALUES (13, 5, 8);
INSERT INTO `score` VALUES (14, 5, 16);
INSERT INTO `score` VALUES (15, 5, 13);
INSERT INTO `score` VALUES (16, 5, 15);
INSERT INTO `score` VALUES (17, 5, 11);
INSERT INTO `score` VALUES (18, 5, 18);
INSERT INTO `score` VALUES (19, 5, 18);
INSERT INTO `score` VALUES (20, 5, 14);
INSERT INTO `score` VALUES (21, 5, 17);
INSERT INTO `score` VALUES (22, 5, 17);
INSERT INTO `score` VALUES (23, 5, 15);
INSERT INTO `score` VALUES (25, 5, 14);
INSERT INTO `score` VALUES (26, 5, 8);
INSERT INTO `score` VALUES (28, 5, 20);
INSERT INTO `score` VALUES (29, 5, 16);
INSERT INTO `score` VALUES (31, 5, 9);
INSERT INTO `score` VALUES (1, 6, 100);
INSERT INTO `score` VALUES (2, 6, 91);
INSERT INTO `score` VALUES (3, 6, 94);
INSERT INTO `score` VALUES (4, 6, 74);
INSERT INTO `score` VALUES (5, 6, 97);
INSERT INTO `score` VALUES (6, 6, 89);
INSERT INTO `score` VALUES (7, 6, 76);
INSERT INTO `score` VALUES (8, 6, 65);
INSERT INTO `score` VALUES (9, 6, 73);
INSERT INTO `score` VALUES (10, 6, 63);
INSERT INTO `score` VALUES (11, 6, 98);
INSERT INTO `score` VALUES (12, 6, 75);
INSERT INTO `score` VALUES (14, 6, 77);
INSERT INTO `score` VALUES (15, 6, 62);
INSERT INTO `score` VALUES (16, 6, 98);
INSERT INTO `score` VALUES (17, 6, 94);
INSERT INTO `score` VALUES (18, 6, 94);
INSERT INTO `score` VALUES (19, 6, 74);
INSERT INTO `score` VALUES (20, 6, 62);
INSERT INTO `score` VALUES (21, 6, 73);
INSERT INTO `score` VALUES (22, 6, 95);
INSERT INTO `score` VALUES (24, 6, 68);
INSERT INTO `score` VALUES (25, 6, 85);
INSERT INTO `score` VALUES (26, 6, 91);
INSERT INTO `score` VALUES (27, 6, 70);
INSERT INTO `score` VALUES (28, 6, 77);
INSERT INTO `score` VALUES (29, 6, 66);
INSERT INTO `score` VALUES (30, 6, 68);
INSERT INTO `score` VALUES (31, 6, 76);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sex` enum('F','M') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('Megan', 'F', 1);
INSERT INTO `student` VALUES ('Joseph', 'M', 2);
INSERT INTO `student` VALUES ('Kyle', 'M', 3);
INSERT INTO `student` VALUES ('Katie', 'F', 4);
INSERT INTO `student` VALUES ('Abby', 'F', 5);
INSERT INTO `student` VALUES ('Nathan', 'M', 6);
INSERT INTO `student` VALUES ('Liesl', 'F', 7);
INSERT INTO `student` VALUES ('Ian', 'M', 8);
INSERT INTO `student` VALUES ('Colin', 'M', 9);
INSERT INTO `student` VALUES ('Peter', 'M', 10);
INSERT INTO `student` VALUES ('Michael', 'M', 11);
INSERT INTO `student` VALUES ('Thomas', 'M', 12);
INSERT INTO `student` VALUES ('Devri', 'F', 13);
INSERT INTO `student` VALUES ('Ben', 'M', 14);
INSERT INTO `student` VALUES ('Aubrey', 'F', 15);
INSERT INTO `student` VALUES ('Rebecca', 'F', 16);
INSERT INTO `student` VALUES ('Will', 'M', 17);
INSERT INTO `student` VALUES ('Max', 'M', 18);
INSERT INTO `student` VALUES ('Rianne', 'F', 19);
INSERT INTO `student` VALUES ('Avery', 'F', 20);
INSERT INTO `student` VALUES ('Lauren', 'F', 21);
INSERT INTO `student` VALUES ('Becca', 'F', 22);
INSERT INTO `student` VALUES ('Gregory', 'M', 23);
INSERT INTO `student` VALUES ('Sarah', 'F', 24);
INSERT INTO `student` VALUES ('Robbie', 'M', 25);
INSERT INTO `student` VALUES ('Keaton', 'M', 26);
INSERT INTO `student` VALUES ('Carter', 'M', 27);
INSERT INTO `student` VALUES ('Teddy', 'M', 28);
INSERT INTO `student` VALUES ('Gabrielle', 'F', 29);
INSERT INTO `student` VALUES ('Grace', 'F', 30);
INSERT INTO `student` VALUES ('Emily', 'F', 31);

SET FOREIGN_KEY_CHECKS = 1;
