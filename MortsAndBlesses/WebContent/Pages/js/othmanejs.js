

$(document).ready(function () {
    var audio = new Audio('https://s3-us-west-2.amazonaws.com/s.cdpn.io/242518/click.mp3');
    var audio2 = new Audio('https://s3-us-west-2.amazonaws.com/s.cdpn.io/242518/clickUp.mp3')

    $(".button").mousedown(function () {
        audio2.load();
        audio2.play();
    });

    $(".button").mouseup(function () {
        audio.load();
        audio.play();
    });
    $("#customSwitch1").click(function(){
        if($("#customSwitch1").is(':checked')   ) {
            $('body').toggleClass("dark");
            $('div').toggleClass("dark");
            $('body').removeClass("navbar-light");
            $('body').removeClass("bg-light");
            $('nav').toggleClass("navbar-dark");
            $('nav').toggleClass(" bg-dark");
            $('nav').toggleClass("text-secondary");
        }else{
            $('body').removeClass("dark");
            $('div').removeClass("dark");
            $('body').toggleClass("navbar-light");
            $('body').toggleClass("bg-light");
            $('nav').removeClass("navbar-dark");
            $('nav').removeClass(" bg-dark");        }
    });

});
