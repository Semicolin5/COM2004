#!/bin/bash

echo "dropping the tables"
sudo mysql < reset_database.sql

echo "Creating the database"
sudo mysql test_db < build_database.sql

echo "Inserting example items"
sudo mysql test_db < insert_test_items.sql
