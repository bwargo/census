package org.bwargo.census

@Grab(group = 'com.gmongo', module = 'gmongo', version = '0.9.3')
import com.gmongo.GMongo
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonSlurper

def mongo = new GMongo("localhost:27017")
def db = mongo.getDB("census")
boolean write = true
int i = 0
int stateCode = 42 //PA
def rest = new RestBuilder()
try {
    println "Starting County import..."

    String finalURL = "http://api.census.gov/data/2013/acs1/?key=d630ed4bf2adc05b83316e0f10ea1d6113bc9070&get=B04006_070E,NAME&for=county:*&in=state:" + stateCode

    def resp = rest.get(finalURL)
    def slurper = new JsonSlurper()
    def results = slurper.parseText(resp.body.asType(String))
    results.each { result ->

        if (result[2].equals("state")) {
            return
        }
        println(result[1])
        if (write) {
            db['county'].save([code: result[3], name: result[1], state: result[2]])
            i++
        }
    }
    println(i + " documents written to county collection.")

} catch (e) {
    println(e.getMessage() + "\n" + e.getStackTrace())
    return
}
