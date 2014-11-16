package org.bwargo.census

@Grab(group='com.gmongo', module='gmongo', version='0.9.3')
import com.gmongo.GMongo
import groovy.json.JsonSlurper


def mongo = new GMongo("localhost:27017")
def db = mongo.getDB("census")
boolean write = true

try {
    println "Starting 2012 import..."
    def slurper2012 = new JsonSlurper().parse("http://api.census.gov/data/2012/acs1/variables.json".toURL())
    int i = 0
    Map variables = (Map) slurper2012.getAt("variables")
    variables.each { v ->
        //skip first two results
        if (v.key.equals("for").or(v.key.equals("in"))) {
            return
        }
        if(write) {
            db['variable'].save([name: v.key, concept: v.value.concept, label: v.value.label, predicateType: v.value.predicateType, year: "2012"])
            i++
        }
    }
    println(i+" documents written to variable collection for 2012")

    println "Starting 2013 import..."
    def slurper = new JsonSlurper().parse("http://api.census.gov/data/2013/acs1/variables.json".toURL())
    variables = (Map) slurper.getAt("variables")
    i = 0
    variables.each { v ->
        //skip first two results
        if(v.key.equals("for").or(v.key.equals("in"))){
            return
        }
        if(write) {
            db['variable'].save([name: v.key, concept: v.value.concept, label: v.value.label, predicateType: v.value.predicateType, year: "2013"])
            i++
        }
    }
    println(i+" documents written to variable collection for 2013")

}catch(e){
    println(e.getMessage())
    println(e.getStackTrace())
    return
}