package org.bwargo.census

import groovy.json.JsonSlurper
import org.springframework.http.ResponseEntity

/**
 * Created by bwargo on 11/9/14.
 */
class ResponseParser {

    ResponseParser(){}
    def slurper = new JsonSlurper()

    def List handleResponse(ResponseEntity responseEntity) {
        if (responseEntity.statusCode.value.equals(200)) {
            return slurper.parseText(responseEntity.body.asType(String)) as List

        } else
            return []
    }
}
