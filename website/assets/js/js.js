//SELECT GET CURRENT OPTION
$('#select').on('change', function(e) {
    console.log(this.options[e.target.selectedIndex].text);
    let clubs = $('#clubs');

    clubs.empty();
    
    let str= '<div class="col">isto é um teste</div>';

    $(str).appendTo(clubs);

});