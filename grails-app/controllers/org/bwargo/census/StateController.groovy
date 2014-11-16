package org.bwargo.census

import grails.converters.JSON

class StateController {

    def index() {}

    def all(){
        def states = State.list()
        render(["states": states] as JSON)
        //def states = State.list()
        //return new ModelAndView("/simple/simple", [ states : states ])
    }
}
