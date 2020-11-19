#!/bin/bash
fly -t local sp -p config-service -c spring-demo-pipeline.yml -v module=spring-petclinic-config-server
fly -t local sp -p vets-service -c spring-demo-pipeline.yml -v module=spring-petclinic-vets-service
fly -t local sp -p visits-service -c spring-demo-pipeline.yml -v module=spring-petclinic-visits-service
fly -t local sp -p customer-service -c spring-demo-pipeline.yml -v module=spring-petclinic-customers-service
fly -t local sp -p gateway-service -c spring-demo-pipeline.yml -v module=spring-petclinic-api-gateway
fly -t local sp -p httpbin-service -c spring-demo-pipeline.yml -v module=spring-petclinic-httpbin
