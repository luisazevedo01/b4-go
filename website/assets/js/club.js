if(localStorage.getItem('username') == null){
    window.location.replace("login.html");
}

$(document).ready(function(){

    urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.search);
    
        return (results !== null) ? results[1] || 0 : false;
    }

    getClubDetails(urlParam("club"));
    getGenres();

});

function getClubDetails(club){

    function successCallback (data) {
        
        $('#club-name').text(data.name);
        str = "";
        str +=  '<div class="card-header">List of Users</div>';
        $.each(data.userList, function( index, value ) {
            str += '<li class="list-group-item">'+value.name+'</li>';
        });

        let clubDetails = $('.club-details');
        let details = '<div class="card text-center">'+
        '<div class="card-header">'+ data.name +'</div>'+
        '<div class="card-body">'+
        '<img src="'+ data.image +'" class="card-img-top" style="max-width:200px; margin-bottom:20px;">'+
        '<p class="card-text">'+ data.description +'</p>'+
        '<button type="button" class="btn btn-primary mr-5" id="join">Join</button>'+
        '<button type="button" class="btn btn-warning" id="back">Go Back</button>'+
        '</div>'+
        '</div>'+
        '<div class="card mt-5"><ul class="list-group">'+ str +'</ul></div>';
        
        $(details).appendTo(clubDetails);

        $('#join').click(function(event){
            getCustomerDto();
        });

        $('#back').click(function(event){
            window.location.href = "index.html";
        });
    }

    function errorCallback(){
        alert("There was an error loading clubs");
    }
    
    $.ajax({
        url: 'http://192.168.1.104:8080/go-go/club/ '+ club,
        async: true,
        data:{get_param : 'id', get_param : 'name', get_param : 'musicGenre', get_param : 'userList'},
        success: successCallback,
        error: errorCallback
    });
}
/*
function getVotes(){

    function successCallback (data) {
        console.log(data);
        console.log(data.name);

        $('#club-name').text(data.name);
        str = "";
        str +=  '<div class="card-header">List of Users</div>';
        $.each(data.userList, function( index, value ) {
            str += '<li class="list-group-item">'+value.name+'</li>';
        });

        console.log(str);

        let clubDetails = $('.club-details');
        let details = '<div class="card text-center">'+
        '<div class="card-header">'+ data.name +'</div>'+
        '<div class="card-body">'+
        '<h5 class="card-title">Special title treatment</h5>'+
        '<p class="card-text">With supporting text below as a natural lead-in to additional content.</p>'+
        '<button type="button" class="btn btn-primary mr-5" id="join">Join</button>'+
        '<button type="button" class="btn btn-primary" id="back">Go Back</button>'+
        '</div>'+
        '</div>'+
        '<div class="card mt-5"><ul class="list-group">'+ str +'</ul></div>';
        
        $(details).appendTo(clubDetails);

        $('#back').click(function(event){
            console.log("um dois");
            window.location.href = "index.html";
        });

    }

    function errorCallback(){
        alert("There was an error loading clubs");
    }
    
    $.ajax({
        url: 'http://192.168.1.104:8080/go-go/user/vote',
        async: true,
        data:{get_param : 'id', get_param : 'name', get_param : 'musicGenre', get_param : 'userList'},
        success: successCallback,
        error: errorCallback
    });

}*/

function getCustomerDto(){
    userDto = null;

    function successCallback(data){
        console.log("DONE "+ data);
        userDto = data;
        joinClub(data);
    }

    function errorCallback(){
        console.log("ERRO GET GENRES");
    }
    
    $.ajax({
        url: 'http://192.168.1.105:8080/go-go/user/'+localStorage.getItem('username'),
        async: true,
        data:{},
        success: successCallback,
        error: errorCallback
    });



}

function getGenres(){

    function successCallback (data) {
        console.log(data);
        let select = $('#selectGenre');

        $.each(data,function(index,element){
            let genre = data[index];
            genre = genre.charAt(0).toUpperCase() + genre.slice(1)
            let option = '<div class="form-check">'+
            '<input class="form-check-input options" type="radio" name="exampleRadios" id="exampleRadios'+index+'" value="'+ genre +'" checked>'+
            '<label class="form-check-label" for="exampleRadios'+index+'">'+ genre +'</label></div>';
            $(option).appendTo(select);

            console.log(genre);
        });



        $('<button type="button" class="btn btn-primary" id="submit-genre">Submit</button>').appendTo(select);

        $('#submit-genre').click(function(event){

            if($("input[type='radio'].options").is(':checked')) {
                var vote = $("input[type='radio'].options:checked").val();
                console.log(vote);
            }

            function voteSuccess(data){
                console.log(data);
                localStorage.setItem('username',$('#username-login').val());
                window.location.href = "index.html";
              }
            
              function voteError(data){
                  console.log(data);
                console.log("error");
                console.log(vote);
                console.log(localStorage.getItem('username'));
              }
              
              $.ajax({
                url: 'http://192.168.1.104:8080/go-go/user/vote/',
                type: 'POST',
                async: true,
                contentType: 'application/json',
                data: JSON.stringify({
                    username: localStorage.getItem('username'),
                    musicGenre: vote
                }),
                success: voteSuccess,
                error: voteError
              });


        });
    }

    function errorCallback(){
        console.log("ERRO GET GENRES");
    }
    
    $.ajax({
        url: 'http://192.168.1.105:8080/go-go/genre/',
        async: true,
        data:{},
        success: successCallback,
        error: errorCallback
    });
    
}

function joinClub(dto){

    function successCallback (data) {
        console.log(dto);
        console.log(data.name);
    }

    function errorCallback(){
        console.log("error joining club");
    }
    
    $.ajax({
        url: 'http://192.168.1.105:8080/go-go/club/' + urlParam("club"),
        type: 'PUT',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify({
            username: dto
        }),
        success: successCallback,
        error: errorCallback
    });
}

