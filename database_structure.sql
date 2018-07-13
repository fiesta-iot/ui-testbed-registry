DROP DATABASE IF EXISTS fiesta_iot_rr;
CREATE DATABASE fiesta_iot_rr;
USE fiesta_iot_rr;
SET time_zone = "+00:00";

DROP TABLE IF EXISTS `register_devices`;

CREATE TABLE `register_devices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_response` MEDIUMTEXT,
  `upload_content` longblob,
  `upload_content_content_type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `register_id` varchar(255) DEFAULT NULL,
  `annotated_resource_description` MEDIUMTEXT,
  `register_testbeds_id` bigint(20) DEFAULT NULL,
  `testbed_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `register_testbeds`;

CREATE TABLE `register_testbeds` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `get_api_key` varchar(255) DEFAULT NULL,
  `get_last_observations_url` varchar(255) DEFAULT NULL,
  `get_observations_url` varchar(255) DEFAULT NULL,
  `iri` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `push_api_key` varchar(255) DEFAULT NULL,
  `push_last_observations_url` varchar(255) DEFAULT NULL,
  `push_observations_url` varchar(255) DEFAULT NULL,
  `annotated_observation` MEDIUMTEXT NOT NULL,
  `annotated_resource_description` MEDIUMTEXT NOT NULL,
  `resource_id` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `register_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iri_UNIQUE` (`iri`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

