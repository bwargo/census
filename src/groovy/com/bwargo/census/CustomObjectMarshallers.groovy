package com.bwargo.census

class CustomObjectMarshallers {
    List marshallers = []

    def register() {
        marshallers.each{ it.register() }
    }
}
