$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/change"
    }).then(function(data, status, jqxhr) {
       $('.change-id').append(data.change);
       console.log(jqxhr);
    });
});
