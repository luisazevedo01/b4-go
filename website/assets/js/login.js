if(localStorage.getItem('username') != null){
  window.location.replace("index.html");
}

$(document).ready(function(){

  $('#register').hide();

  $('#register-button').click(function(){
    $('#register').fadeToggle(500);
    $('#login').hide();
  });

  $('#cancel-button').click(function(){
    $('#login').fadeToggle(500);
    $('#register').hide();
  });

  $('#register-submit').click(function(event){
    function addSuccess(data){
      
      console.log(data);
      $('#register').hide();
      $('#login').show();
    }

    function addError(data){
        console.log(data);
      }
      
    $.ajax({
        url: 'http://192.168.1.104:8080/go-go/user/register/',
        type: 'POST',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify({
            username: $('#username').val(),
            password: $('#password').val(),
            name: $('#name').val(),
            club: null
        }),
        success: addSuccess,
        error: addError
    });
  });

  $('#login-submit').click(function(event){
    authenticate();

  });

})

function authenticate(){

  function loginSuccess(data){
    localStorage.setItem('username',$('#username-login').val());
    window.location.href = "index.html";
  }

  function loginError(data){

    var toast = $('.toast');
    toast.empty();
    let str =  '<div class="toast-header">'+
    '<strong class="mr-auto">Error</strong>'+
    '<button type="button" id="close-toast" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">'+
    '<span aria-hidden="true">&times;</span>'+
    '</button>'+
    '</div>'+
    '<div class="toast-body">'+
    'Invalid Credentials'+
    '</div>'+
    '</div>';
    $(str).appendTo(toast);

    $('#close-toast').click(function(){
      toast.empty();
    });

    console.log("error");
    console.log($("#username-login").val());
    console.log($("#password-login").val());
  }
  
  $.ajax({
    url: 'http://192.168.1.104:8080/go-go/user/login/',
    type: 'POST',
    async: true,
    contentType: 'application/json',
    data: JSON.stringify({
      username: $('#username-login').val(),
      password: $('#password-login').val()
    }),
    success: loginSuccess,
    error: loginError
  });
}
