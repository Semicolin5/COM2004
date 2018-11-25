#!/bin/bash

echo "running pdflatex on team_report.tex"
pdflatex team_report.tex

echo "viewing the team report as a pdf"
firefox team_report.pdf

