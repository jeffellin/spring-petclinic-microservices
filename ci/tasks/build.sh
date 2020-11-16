#!/bin/bash

set -eux

env 

export ROOT_FOLDER="$( pwd )"

VERSION=$(cat version/version)
SCRIPT_DIR=$(dirname "$0")
M2_HOME="${ROOT_FOLDER}/.m2"
M2_CACHE="${ROOT_FOLDER}/maven"
source ${SCRIPT_DIR}/mvn-tools.sh



generate_settings
ls

pushd source-code/$APP_NAME
	mvn clean package -DskipTests
popd

mkdir packed-release/target
cp source-code/docker/Dockerfile packed-release/target/
cp source-code/$APP_NAME/target/$APP_NAME-2.3.2.jar packed-release/target/$APP_NAME.jar
