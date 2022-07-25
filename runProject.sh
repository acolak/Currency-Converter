#!/bin/sh

# exit when any command fails
set -e

echo "WORKING DIRECTORY: $(pwd)"

echo "1. Maven Clean and Package"
./mvnw clean package

echo "3. Docker building for project"
docker-compose up --build

echo "4. SUCCESS"