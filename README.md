census
======

Geo-Ancestry application that acts as a wrapper for api.census.gov but also uses REST, Spring, MongoDB, and eventually Angular with Google Map APIs

Required
Java 1.7
Grails 2.4.3 
Mongo 2.6.3

Before running grails application, you must import the Census Variables using <code>importACS_SummaryVariables.groovy</code> and <code>importANSI_StateCodes.groovy</code> and <code>importANSI_CountyCodes.groovy</code>. This helps the application look up the labels/names of census variables and is a quick alternative to getting this info dynamically from api.census.gov everytime a resource is hit.

This is a very early version of the app and is not fully tested as of yet.  Major updates are soon to follow.

