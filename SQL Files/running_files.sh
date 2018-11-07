#!/bin/bash

echo "dropping the tables"
sudo mysql < reset_database.sql

echo "Creating the database"
sudo mysql db < build_database.sql

echo "Inserting example items"
sudo mysql db < insert_test_items.sql
