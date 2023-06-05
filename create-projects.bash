#!/usr/bin/env bash

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=athletes-service \
--package-name=com.sportsmanagement.athleteservice \
--groupId=com.sportsmanagement.athleteservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
athletes-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=coaches-service \
--package-name=com.sportsmanagement.coachservice \
--groupId=com.sportsmanagement.coachservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
coaches-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=teams-service \
--package-name=com.sportsmanagement.teamservice \
--groupId=com.sportsmanagement.teamservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
teams-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=events-service \
--package-name=com.sportsmanagement.eventservice \
--groupId=com.sportsmanagement.eventservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
events-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=api-gateway \
--package-name=com.sportsmanagement.apigateway \
--groupId=com.sportsmanagement.apigateway \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
api-gateway

