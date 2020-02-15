 function insert(json) {
            var audio = new Audio('../MortsAndBlesses/Pages/add.mp3');
            var data = JSON.parse(json);
            if (data.state != "end") {
                audio.play();
                $("#1").append(`
                <article style="margin-bottom:30px;">
            <h2>${data.numbers.user_1}</h2>
                    <article class="congrats">
                        <article class="row">
                            <span><span style="color: yellow;">${data.users.user_1.morts}</span>Mort! , </span>
                            <span><span style="color: yellow;">${data.users.user_1.blesses}</span>Blessé!</span>
                        </article>
                    </article> </article>`
                    );

                $("#2").append(`
                <article style="margin-bottom:30px;">
            <h2>${data.numbers.user_2}</h2>
                    <article class="congrats">
                        <article class="row">
                            <span><span style="color: yellow;">${data.users.user_2.morts}</span>Mort! , </span>
                            <span><span style="color: yellow;">${data.users.user_2.blesses}</span>Blessé!</span>
                        </article>
                    </article> </article>`);
                    reset();
        animateText();



            }
            else {

                var audio = new Audio('../MortsAndBlesses/Pages/Gameover.mp3');
                audio.play();
                
                $("body").append(`<div id="myModal" class="modal fade" role="dialog">
      <div class="modal-dialog">
 
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Fin de Jeux !</h4>
          </div>
          <div class="modal-body">
            <p>Felicitation <strong>${data.winner}</strong></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" onclick="redirect()" data-dismiss="modal">Close</button>
          </div>
        </div>
 
      </div>
    </div>
 </div>`);

                $("#myModal").modal("show");
                $('.modal-backdrop').appendTo('body');

            }
        }
            function redirect() {
                window.location.href = "Profile";            }
            
            
            
            $(function () {
                animateText();
            });

            $('span').load(function () {
                reset();
                animateText();
            });

            function reset() {
                $.each($('.blob'), function (i) {
                    TweenMax.set($(this), { x: 0, y: 0, opacity: 1 });
                });

                TweenMax.set($('span'), { scale: 1, opacity: 1, rotation: 0 });
            }

            function animateText() {
                TweenMax.from($('span'), 0.8, {
                    scale: 0.4,
                    opacity: 0,
                    rotation: 15,
                    ease: Back.easeOut.config(4),
                });
            }
            
            var h1 = document.getElementsByTagName('h4')[0],
            seconds = 0, minutes = 0, hours = 0,
            t;

        function add() {
            seconds++;
            if (seconds >= 60) {
                seconds = 0;
                minutes++;
                if (minutes >= 60) {
                    minutes = 0;
                    hours++;
                }
            }

            h1.textContent = (hours ? (hours > 9 ? hours : "0" + hours) : "00") + ":" + (minutes ? (minutes > 9 ? minutes : "0" + minutes) : "00") + ":" + (seconds > 9 ? seconds : "0" + seconds);

            timer();
        }
        function timer() {
            t = setTimeout(add, 1000);
        }
        timer();


