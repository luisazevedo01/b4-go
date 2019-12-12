$(document).ready(function(){

  $('#register').hide();

  $('#register-button').click(function(){
    $('#register').show();
    $('#login').hide();
  });

  $('#cancel-button').click(function(){
    $('#login').show();
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
        console.log($('#username'.val()));
        console.log($('#password'.val()));
        console.log($('#name'.val()));
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

    $("#btnSubmit").click(function () {
      //collect userName and password entered by users
      var userName = $("#username").val();
      var password = $("#password").val();


      });

    function addSuccess(data){
      console.log(data);
      location.reload();
    }

    function addError(data){
        console.log(2);
    }
    
    $.ajax({
        url: 'http://192.168.1.105:8080/user/register/',
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

})
