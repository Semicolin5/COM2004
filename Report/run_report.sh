#!/bin/bash

echo "running pdflatex on team_report.tex"
pdflatex teamReport.tex

echo "viewing the team report as a pdf"
firefox teamReport.pdf

