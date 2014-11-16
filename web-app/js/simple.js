/**
 * Created by bwargo on 11/8/14.
 */

//original request example
/* $(document).ready(function() {
    $.ajax({
        url: "http://rest-service.guides.spring.io/greeting"
    }).then(function(data) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
    });
}); */
$(document).ready(function() {
 $.ajax({
     url: "http://localhost:8080/census/state/all"
     }).then(function(data) {
     var jsonData = data.states;
     var listItems= $('select#states').html();
     for (var i = 0; i < jsonData.length; i++) {
         listItems+= "<option value='" + jsonData[i].fips + "'>" + jsonData[i].name + "</option>";
     }
     $('select#states').html(listItems);
        });
 $.ajax({
    url: "http://localhost:8080/census/variable/ancestry"
    }).then(function(data) {
        var jsonData = data.variables;
        var listItems= $('select#ancestry').html();
        for (var i = 0; i < jsonData.length; i++) {
            listItems+= "<option value='" + jsonData[i].name + "'>" + jsonData[i].label + "</option>";
        }
        $('select#ancestry').html(listItems);
    });
 });

$(document).on('change','select#states',function(){

    var stateSelected = $('#states option:selected').val();
    var varSelected = $('#ancestry option:selected').val();
    var placeSelected = $('#places option:selected').val();
    var countySelected = $('#counties option:selected').val();
    updatePlaces(stateSelected);
    updateCounties(stateSelected);
    console.log("states " + stateSelected + " "+ varSelected);
    //"B04006_070E" - slovak
    if(stateSelected != -1 && varSelected != -1) {
        updateMap(stateSelected, countySelected, placeSelected, varSelected);
    }

});

$(document).on('change','select#ancestry',function(){
    var stateSelected = $('#states option:selected').val();
    var varSelected = $('#ancestry option:selected').val();
    var placeSelected = $('#places option:selected').val();
    var countySelected = $('#counties option:selected').val();
    console.log("ancestry state:" +stateSelected + " county:"+countySelected+ " place:"+placeSelected+ " var:" + varSelected);
    //"B04006_070E" - slovak
    if(stateSelected != -1 && varSelected != -1){
        if(placeSelected != null && placeSelected != -1) {
            updateMap(stateSelected, null, placeSelected, varSelected);
        }
        if(countySelected != null && countySelected != -1){
            updateMap(stateSelected, countySelected, null, varSelected);
        }
    }
});

$(document).on('change','select#counties',function(){
    var stateSelected = $('#states option:selected').val();
    var countySelected = $('#counties option:selected').val();
    var varSelected = $('#ancestry option:selected').val();
    console.log("counties " + stateSelected + " "+ varSelected);
    $('select#places option[value="-1"]').attr("selected",true);
    if(stateSelected != -1 && countySelected != -1 && varSelected != -1) {
        updateMap(stateSelected, countySelected, null, varSelected);
    }

});

$(document).on('change','select#places',function(){
    var stateSelected = $('#states option:selected').val();
    var placeSelected = $('#places option:selected').val();
    var varSelected = $('#ancestry option:selected').val();
    console.log("places " + placeSelected + " "+ varSelected);
    $('select#counties option[value="-1"]').attr("selected",true);
    if(stateSelected != -1 && placeSelected != -1 && varSelected != -1) {
        updateMap(stateSelected, null,placeSelected, varSelected);
    }

});

function updateMap(state,county,place,variable){
    console.log("updateMap called: variable is "+variable);
    var callURL = "http://localhost:8080/census/simple/get?state="+state+"&variable="+variable;

    if(county && county != -1){
        callURL += "&county="+county;
    }
    if(place && place != -1){
        callURL += "&place="+place;
    }
    console.log(callURL);
    $.ajax({
        url: callURL
    }).then(function(data) {
        //console.log(data);
        var jsonData = data.results;
        //console.log(jsonData);
        var listItems= "<dl>";
        if(jsonData) {
            for (var i = 0; i < jsonData.length; i++) {
                //var key = Object.keys(jsonData[i])[0];
                listItems += "<dd>" + jsonData[i].variable + " people reporting " + " " + jsonData[i].label + " in " + jsonData[i].NAME + "</dd>";
            }
            $('#results').html(listItems + "</dl>");
        }
    });
}

function updatePlaces(state){
    if(state == -1){
        $('select#places').empty();
    }else {
        $.ajax({
            url: "http://localhost:8080/census/place/all/" + state
        }).then(function (placeData) {
            var jsonData = placeData.results;
            if(jsonData) {
                //var listItems = $('select#places').html();
                var listItems = "<option value='-1' selected='selected'>Select...</option>";
                for (var i = 0; i < jsonData.length; i++) {
                    listItems += "<option value='" + jsonData[i].place + "'>" + jsonData[i].NAME + "</option>";
                }
                $('select#places').html(listItems);
            }
        });
    }
}

function updateCounties(state){
    if(state == -1){
        $('select#counties').empty();
    }else {
        $.ajax({
            url: "http://localhost:8080/census/county/all/" + state
        }).then(function (data) {
            var jsonData = data.results;
            if(jsonData) {
                //var listItems = $('select#counties').html();
                var listItems = "<option value='-1' selected='selected'>Select...</option>";
                for (var i = 0; i < jsonData.length; i++) {
                    listItems += "<option value='" + jsonData[i].county + "'>" + jsonData[i].NAME + "</option>";
                }
                $('select#counties').html(listItems);
            }
        });
    }
}