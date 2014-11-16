package org.bwargo.census

@Grab(group='com.gmongo', module='gmongo', version='0.9.3')
import com.gmongo.GMongo


def mongo = new GMongo("localhost:27017")
def db = mongo.getDB("census")
boolean write = true
int i = 0
try {
    println "Starting States import..."
    File stateFile = new File("state.txt")
    stateFile.newWriter()
    stateFile << new URL ("http://www.census.gov/geo/reference/docs/state.txt").getText()
    stateFile.eachLine { line ->
        String[] list_str = line.split("\\|")
        if(list_str[0].isInteger()){
            //println(list_str[0])

            if(write) {
                db['state'].save([fipsCode: list_str[0], abbreviation: list_str[1], name: list_str[2], gnisCode: list_str[3]])
                i++
            }
        }
    }

    println(i+ " documents written to state collection.")

}catch(e){
    println(e.getMessage())
    println(e.getStackTrace())
    return
}
