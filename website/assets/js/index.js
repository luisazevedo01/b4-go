//SELECT GET CURRENT OPTION
$('#selectGenre').on('change', function(e) {
    console.log(this.options[e.target.selectedIndex].text);
    console.log(this.options[e.target.selectedIndex].index);
    
    let clubsDiv = $('#clubs');
    
    if(this.options[e.target.selectedIndex].index == 0){
        clubsDiv.empty();
        getAllClubs();
        return;
    }

    clubsDiv.empty();
    getGenreClubs(this.options[e.target.selectedIndex].text);
});

$(document).ready(function(){
    getGenres();
    getAllClubs();
});

function getGenres(){

    function successCallback (data) {
        console.log(data);
        let select = $('#selectGenre');
        select.empty();
        $('<option selected>Choose a music style</option>').appendTo(select);
        $.each(data,function(index,element){
            let genre = data[index];
            genre = genre.charAt(0).toUpperCase() + genre.slice(1)
            let option = '<option class="option">' + genre + '</option>';
            $(option).appendTo(select);

            console.log(genre);
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

function getAllClubs(){
    
    function successCallback (data) {
        console.log(data);
        $.each(data,function(index,element){
            let cards = $('#clubs');
            
            let card = '<div class="col">'+
            '<div class="card"> '+
            '<img src="https://i.stack.imgur.com/jZhAM.png" class="card-img-top" alt=""> '+
            '<div class="card-body">'+
            '<h5 class="card-title">'+ element.name +'</h5>' +
            '<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card\'s content.</p>'+
            '<a href="club.html?club='+ element.id + '" class="btn btn-primary club-button">View Details</a>'+
            '</div></div></div>';
            
            $(card).appendTo(cards);
            console.log(element.id);
        });
        
    }

    function errorCallback(){
        alert("There was an error loading clubs");
    }
    
    $.ajax({
        url: 'http://192.168.1.105:8080/go-go/club/',
        async: true,
        data:{get_param : 'id', get_param : 'name', get_param : 'musicGenre', get_param : 'userList'},
        success: successCallback,
        error: errorCallback
    });
}

function getGenreClubs(genre){
    
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
                '<a href="club.html?club='+ element.id + '" class="btn btn-primary">Go somewhere</a>'+
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