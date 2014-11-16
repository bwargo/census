package com.bwargo.census

import grails.converters.JSON
import org.bwargo.census.County

/**
 * Created by bwargo on 11/11/14.
 */
class CountyMarshaller {
    void register() {
        JSON.registerObjectMarshaller(County) { County county ->
            return [
                    id : county.id.toString(),
                    code : county.code,
                    name : county.name,
                    state : county.state
            ]
        }
    }
}
