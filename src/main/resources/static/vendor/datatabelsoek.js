
    $(document).ready( function () {
        var brukernavn = document.getElementById("innlogetbruker").title;
        $('#meldingTabel').DataTable({
            paging: false,
            searching: false,
            ordering:  false,
            "ajax": {
                "url": "/meldinger/hvisMeldingeneMine",
                "type": "get",
                "dataType": "json",
                "contentType": "application/json",
                "dataSrc": "",
                "data": {'username': brukernavn}
            },
            "columns": [
                {"data": "meldingId", "width": "30%"},
                {"data": "frabruker.name","width": "20%"},
                {"data": "melding", "width": "50%"}
            ]
        });


    });

