package org.bwargo.census

import grails.persistence.Entity
import org.bson.types.ObjectId

@Entity
class County {

    static mapWith = "mongo"

    ObjectId id
    String code
    String state
    String name

    //Point location

    static mapping = {
        name index: true
        //location geoIndex:'2dsphere'
    }

    static constraints = {
        name blank: false
        //location nullable:false
    }
}
