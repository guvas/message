function sendMelding(){
    var tilbruker = document.getElementById("tilbruker").value;
    var melding = document.getElementById("melding").value;
    var username = document.getElementById("username").value;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/meldinger/sendMelding",
        data: 'tilbruker='+tilbruker+'&melding='+ melding+'&username='+ username,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4> Meldingen er send til "+tilbruker+"<pre>";
            $('#feedback').html(json);


        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);



        }
    });

}