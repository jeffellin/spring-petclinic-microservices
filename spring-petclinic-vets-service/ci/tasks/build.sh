#!/bin/bash

set -eux
export ROOT_FOLDER="$( pwd )"

VERSION=$(cat version/version)
SCRIPT_DIR=$(dirname "$0")
M2_HOME="${ROOT_FOLDER}/.m2"
M2_CACHE="${ROOT_FOLDER}/maven"
source ${SCRIPT_DIR}/mvn-tools.sh



generate_settings
ls

pushd source-code/spring-petclinic-vets-service
	mvn clean package -DskipTests
popd

mkdir packed-release/target
cp source-code/spring-petclinic-vets-service/docker/Dockerfile packed-release/target/
cp source-code/spring-petclinic-vets-service/target/spring-petclinic-vets-service-2.3.2.jar packed-release/target/spring-petclinic-vets-service.jar
