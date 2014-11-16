package org.bwargo.census

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import org.springframework.http.ResponseEntity

@Transactional
class SimpleService {

    def rest = new RestBuilder()

    static host = ""
    static key = ""

    private stateCode = 042 //PA

    SimpleService() {}

    SimpleService(String host) {
        this.host = host
    }

    def ResponseEntity basicCall(String variable, String forVar, String inVar){
        //String finalURL = host + "?key=" + key + "&get=" + variable + "&for="+forVar+"&in="+inVar
        String finalURL = host + "?get=" + variable + "&for="+forVar+"&in="+inVar
        log.info("Final URL before REST call: " + finalURL)
        def resp = rest.get(finalURL)
        //resp.json instanceof JSONObject

        return resp.responseEntity
        //return [status:responseEntity.statusCode,body:map] as JSON;
    }

    def ResponseEntity getCountyData(String county, String variable) {
        String finalURL = host + "?get=" + variable + ",NAME&for=county:"+county

        log.debug("Final URL before REST call: " + finalURL)
        def resp = rest.get(finalURL)
        //resp.json instanceof JSONObject

        return resp.responseEntity
        //return [status:responseEntity.statusCode,body:map] as JSON;
    }

    def ArrayList formatAsJSON(ArrayList rawResults, String variable) {
        List keys = rawResults.remove(0)

        List results = new ArrayList()
        for (rawResult in rawResults) {
            Map result = new HashMap()
            for (i in 0..3) {
                if(keys[i].equals(variable)){
                    //lookup variable label
                    String label = lookupLabelByName(keys[i].toString())
                    result.put("label",label)
                    //replacing the variable name with "variable" until i figure out better frontend solution for accessing the json

                    result.put("variable", checkForNull(rawResult[i]))
                }else {
                    result.put(keys[i], rawResult[i])
                }
            }
            results.add(result)
        }
        return results
    }

    def checkForNull(Object o) {
        if(!o){
            return 0
        }
        return o
    }

    def String lookupLabelByName(String v) {

        Variable variable = (Variable) Variable.findByNameAndYear(v,"2013")
        if (variable) {
            return variable.label
        }
        return v
    }
}
