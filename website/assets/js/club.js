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
    updateVotes();

});

function getClubDetails(club){
    $('.club-details').empty();

    function successCallback (data) {
        
        $('#club-name').text(data.name);
        str = "";
        str +=  '<div class="card-header">List of Users</div>';
        $.each(data.userList, function( index, value ) {
            str += '<li class="list-group-item">'+value.name+'</li>';
        });

        let clubDetails = $('.club-details');
        let details = '<div class="col-xs-12 col-md-7 card text-center">'+
        '<div class="card-header">'+ data.name +'</div>'+
        '<div class="card-body">'+
        '<img src="'+ data.image +'" class="card-img-top" style="max-width:200px; margin-bottom:20px;">'+
        '<p class="card-text">'+ data.description +'</p>'+
        '<button type="button" class="btn btn-primary mr-5" id="join">'+userInList(data.userList)+'</button>'+
        '<button type="button" class="btn btn-warning" id="back">Go Back</button>'+
        '</div>'+
        '</div>'+
        '<div class="col-xs-12 col-md-5 card"><ul class="list-group">'+ str +'</ul></div>';
        
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
        url: 'http://192.168.1.105:8080/go-go/club/ '+ club,
        async: true,
        data:{get_param : 'id', get_param : 'name', get_param : 'musicGenre', get_param : 'userList'},
        success: successCallback,
        error: errorCallback
    });
}

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



        $('<button type="button" class="btn btn-primary mt-4" id="submit-genre">Submit</button>').appendTo(select);

        $('#submit-genre').click(function(event){
            
            if($("input[type='radio'].options").is(':checked')) {
                vote = $("input[type='radio'].options:checked").val();
                console.log(vote);
            }

            function successCallback(data){
                userDto = data;
                voteOnGenre(vote, data);
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
        getClubDetails(urlParam("club"));
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
            username: dto.username,
            name: dto.name,
            club: dto.club
        }),
        success: successCallback,
        error: errorCallback
    });
}

function userInList(data){
    var string = "Go"

    $.each(data, function( index, value ) {
        if(value.username === localStorage.getItem('username')){
            console.log("contains!!!!!")
            string = "Leave";
        }
    });
    
    return string;

}

function updateVotes(){

    function successCallback (data) {
        let container = $('.votes-results');



        console.log(data);
    }

    function errorCallback(){
        console.log("error joining club");
    }
    
    $.ajax({
        url: 'http://192.168.1.104:8080/go-go/user/show-votes/',
        type: 'GET',
        async: true,
        data: {},
        success: successCallback,
        error: errorCallback
    });

}

function getCustomer(){
    userDto = null;

    function successCallback(data){
        console.log("DONE "+ data);
        return data;
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

function voteOnGenre(vote, userDto){
    var user = userDto;
    function voteSuccess(data){
        console.log(vote)
        console.log(data.firstname);
        updateVotes();
      }
    
      function voteError(data){
          console.log(data);
        console.log("error");
        console.log(vote);
        console.log(localStorage.getItem('username'));
      }
      
      $.ajax({
        url: 'http://192.168.1.105:8080/go-go/user/vote/'+vote ,
        type: 'PUT',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify({
            username: user.username,
            name: user.name,
            club: user.club
        }),
        success: voteSuccess,
        error: voteError
      });
}
