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

})
