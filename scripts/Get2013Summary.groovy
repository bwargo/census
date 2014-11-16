includeTargets << grailsScript("_GrailsInit")

@Grab(group='com.gmongo', module='gmongo', version='0.9.3')
import com.gmongo.GMongo
target(get2013Summary: "Grabs Summary data variables from American Community Survey 2013") {

}

setDefaultTarget(get2013Summary)
