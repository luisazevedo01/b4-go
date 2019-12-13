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
        str +=  '<div class="card-header title">List of Users</div>';
        $.each(data.userList, function( index, value ) {
            str += '<li class="list-group-item">'+value.name+'</li>';
        });

        let clubDetails = $('.club-details');
        let details = '<div class="col-xs-12 col-md-7 card text-center">'+
        '<div class="card-header title">'+ data.name +'</div>'+
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
        url: 'http://192.168.1.104:8080/go-go/club/ '+ club,
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
        url: 'http://192.168.1.104:8080/go-go/user/'+localStorage.getItem('username'),
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
        $('<h1 class="title">Poll</h1>').appendTo(select);
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
                url: 'http://192.168.1.104:8080/go-go/user/'+localStorage.getItem('username'),
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
        url: 'http://192.168.1.104:8080/go-go/genre/',
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
        url: 'http://192.168.1.104:8080/go-go/club/' + urlParam("club"),
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
        $('.votes-results').empty();
        let container = $('.votes-results');
        $('<h1 class="title text-center">Results</h1>').appendTo(container);

        console.log(data);
        console.log(data[1]);
        console.log(data.JAZZ);
        let bars ='<p>ROCK</p>'+
        '<div class="progress">'+
          '<div class="progress-bar progress-bar-striped" role="progressbar" style="width: '+data.ROCK+'%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>'+
        '</div>'+
        '<p>POP</p>'+
        '<div class="progress">'+
          '<div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: '+data.POP+'%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>'+
        '</div>'+
        '<p>JAZZ</p>'+
        '<div class="progress">'+
          '<div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: '+data.JAZZ+'%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>'+
        '</div>'+
        '<p>RAP</p>'+
        '<div class="progress">'+
          '<div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: '+data.RAP+'%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>'+
        '</div>'+
        '<p>PIMBA</p>'+
        '<div class="progress">'+
          '<div class="progress-bar progress-bar-striped bg-danger" role="progressbar" style="width: '+data.PIMBA+'%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>'+
        '</div>';

        $(bars).appendTo(container);

        console.log(data);
    }

    function errorCallback(){
        console.log("error joining club");
    }
    
    $.ajax({
        url: 'http://192.168.1.104:8080/go-go/user/bar-counter',
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
        url: 'http://192.168.1.104:8080/go-go/user/'+localStorage.getItem('username'),
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
        console.log(user.name);
        console.log(user);
        updateVotes();
      }
    
      function voteError(data){
        console.log(data);
        console.log("error");
        console.log(vote);
        console.log(localStorage.getItem('username'));
      }
      
      $.ajax({
        url: 'http://192.168.1.104:8080/go-go/user/vote/'+vote ,
        type: 'POST',
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
