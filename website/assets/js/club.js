$(document).ready(function(){

    urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.search);
    
        return (results !== null) ? results[1] || 0 : false;
    }

    console.log(urlParam("club"));


});

function getClubDetails(club){

    function successCallback (data) {
        console.log(data);
        if(data[0] != null){

            $.each(data,function(index,element){
                let cards = $('#clubs');
                
                let card = '<div class="col">'+
                '<div class="card"> '+
                '<img src="https://i.stack.imgur.com/jZhAM.png" class="card-img-top" alt="..."> '+
                '<div class="card-body">'+
                '<h5 class="card-title">'+ element.name +'</h5>' +
                '<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card\'s content.</p>'+
                '<a href="#" class="btn btn-primary">Go somewhere</a>'+
                '</div></div></div>';
                
                $(card).appendTo(cards);
                console.log(element.id);
            });
        } else {
            let cards = $('#clubs');
            $('<div class="col-md-12 text-center mt-5"> No clubs found with this genre </div>').appendTo(cards);
        }
    }

    function errorCallback(){
        alert("There was an error loading clubs");
    }
    
    $.ajax({
        url: 'http://192.168.1.105:8080/go-go/club/genre/'+genre,
        async: true,
        data:{get_param : 'id', get_param : 'name', get_param : 'musicGenre', get_param : 'userList'},
        success: successCallback,
        error: errorCallback
    });

}


