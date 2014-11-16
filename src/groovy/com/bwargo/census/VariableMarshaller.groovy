package com.bwargo.census

import grails.converters.JSON
import org.bwargo.census.Variable

/**
 * Created by bwargo on 11/10/14.
 */
class VariableMarshaller {
    void register() {
        JSON.registerObjectMarshaller(Variable) { Variable variable ->
            return [
                    id : variable.id.toString(),
                    label : variable.label,
                    name : variable.name,
                    concept : variable.concept,
                    predicateType : variable.predicateType,
                    year: variable.year
            ]
        }
    }
}
