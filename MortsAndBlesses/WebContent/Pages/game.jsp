<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/css/btn.css" />
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/mycss.css" />

    <title>Game</title>

</head>

<body>
    <!-- ici navbar  -->
   <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <img src="../MortsAndBlesses/Pages/images/p.png" height="80px">


        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Profil <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Jeux</a>
                </li>

            </ul>
            <form methode="GET" action="Deconnecte" class="form-inline my-2 my-lg-0">
                <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Déconnecté</button>
               <audio id="track">
					<!-- <source src="http://basichow.com/asserts/interlude.mp3" type="audio/mpeg" /> -->
					<source src="../MortsAndBlesses/Pages/audios/win.mp3" preload="metadata" type="audio/mpeg">
				</audio>

                <div id="player-container">
                    <div id="play-pause" class="play">Play</div>
                </div>
            </form>
            <div class="custom-control custom-switch" style="margin-left:10px;">
                <input type="checkbox" class="custom-control-input" id="customSwitch1">
                <label class="custom-control-label" for="customSwitch1">Dark mode</label>
            </div>
        </div>
    </nav>
    <!-- fin navbar -->

    <!-- boody de page -->
    <br><br>
    
    <div class="container">
        <output>Time:</output>
        <h4>00:00:00</h4>
        <div class="row" style="margin-right: 10px;">
            <div class="col-sm-2"></div>
            <div class="card col-sm-4 " style="height:30rem; background-color:slategray; color: snow; overflow-y:auto;">
            	<table>
            		<tr>
            			<td style="width: 50px; height: 50px;"><img style="width: 50px; height: 50px;" alt="avatar" src="../MortsAndBlesses/Pages/images/avatars/${user_1.image}" ></td>
            			<td>
			                <h4 id="u_1" style="margin-bottom: 0px;">${user_1.prenom} ${user_1.nom}</h4>
			                <h6 style="margin-bottom: 0px;"><c:if test="${user.id_u == jouer.id_u1}">Voter nombre : <b>${jouer.nombre_u1}</b></c:if></h6>
            			</td>
            		</tr>
            	</table>
            	<hr />
                <center id="1" >
                    
           
                
                </center>
            </div>
            <div class="col-sm-1"></div>
            <div class="card col-sm-4" style="height:30rem; background-color:slategray; color: snow; overflow-y:auto;">
            	<table>
            		<tr>
            			<td style="width: 50px; height: 50px;"><img style="width: 50px; height: 50px;" alt="avatar" src="../MortsAndBlesses/Pages/images/avatars/${user_2.image}" ></td>
            			<td>
			                <h4 id="u_2" style="margin-bottom: 0px;">${user_2.prenom} ${user_2.nom}</h4>
			                <h6 style="margin-bottom: 0px;"><c:if test="${user.id_u == jouer.id_u2}">Voter nombre : <b>${jouer.nombre_u2}</b></c:if></h6>
                		</td>
            		</tr>
            	</table>
                <hr />
                <center id="2">
                    
                    
                </center>

            </div>
            <br />

        </div>
        <br />
        <div class="form-group">

                <div class="row">
                    <div class="col-sm-2"></div>
                    <input name="input1"  id="input1" class="form-control col-sm-2 " style="margin: 5px;" type="number" value="1" max="9" min="1" pattern="[0-9]{4}">
                    <input name="input2"  id="input2" class="form-control col-sm-2 " style="margin: 5px;" type="number" value="1" max="9" min="1" pattern="[0-9]{4}">
                    <input name="input3"  id="input3" class="form-control col-sm-2 " style="margin: 5px;" type="number" value="1" max="9" min="1" pattern="[0-9]{4}">
                    <input name="input4"  id="input4" class="form-control col-sm-2 " style="margin: 5px;" type="number" value="1" max="9" min="1" pattern="[0-9]{4}">
                    <button class="button" type="button" id ="btn" onclick="sendNow()"><svg class="bi bi-cursor" width="1em" height="1em" viewBox="0 0 20 20"
                            fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M16.081 4.182a.5.5 0 01.104.557l-5.657 12.727a.5.5 0 01-.917-.006L7.57 12.694l-4.766-2.042a.5.5 0 01-.006-.917L15.525 4.08a.5.5 0 01.556.103zM4.25 10.184l3.897 1.67a.5.5 0 01.262.263l1.67 3.897L14.743 5.52 4.25 10.184z"
                                clip-rule="evenodd"></path>
                        </svg> </button >
                            <input type="hidden" id="number" name="number" value="">
                </div>

        </div>
    </div>
    <!-- Fin de page -->
    <!-- script -->
    <script src="../MortsAndBlesses/Pages/js/jquery.js"></script>
    <script src="../MortsAndBlesses/Pages/js/bootstrap.js"></script>
    <!-- btn jcript -->
    <script src="../MortsAndBlesses/Pages/js/btnMusic.js"></script>
    <script src="../MortsAndBlesses/Pages/js/othmanejs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js"> </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <scrip src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.2/underscore-min.js"></scrip>

    <script>
        /*  setInterval(() => {
            insert('{"state":"end0","winner":"ana","numbers":{"user_1":1234,"user_2":5687},"users":{"user_1":{"morts":3,"blesses":4},"user_2":{"morts":2,"blesses":2}}}');
        }, 2000);
  */
    </script>
	
	
	<c:if test="${ user_2.id_u!=-1 }">
    <script>
    	var path2="Game_over";
        function insert(json) {
            var audio = new Audio('../MortsAndBlesses/Pages/audios/add.mp3');
            var data = JSON.parse(json);
            if (data.state != "end") {
                audio.play();
                $("#1").append(`
                <article style="margin-bottom:30px;">
            <h2>`+data.numbers.user_1+`</h2>
                    <article class="congrats">
                        <article class="row">
                            <span><span style="color: yellow;">`+data.users.user_1.morts+`</span>Mort! , </span>
                            <span><span style="color: yellow;">`+data.users.user_1.blesses+`</span>Blessé!</span>
                        </article>
                    </article> </article>`
                    );

                $("#2").append(`
                <article style="margin-bottom:30px;">
            <h2>`+data.numbers.user_2+`</h2>
                    <article class="congrats">
                        <article class="row">
                            <span><span style="color: yellow;">`+data.users.user_2.morts+`</span>Mort! , </span>
                            <span><span style="color: yellow;">`+data.users.user_2.blesses+`</span>Blessé!</span>
                        </article>
                    </article> </article>`);
                    reset();
        animateText();



            }
            else {

                var audio = new Audio('../MortsAndBlesses/Pages/audios/Gameover.mp3');
                audio.play();
                
                $("body").append('<div id="myModal" class="modal fade" role="dialog"><div class="modal-dialog"> <!-- Modal content--><div class="modal-content"><div class="modal-header"><h4 class="modal-title">Fin de Jeux !</h4></div><div class="modal-body"><p>Felicitation <strong>'+ document.getElementById("u_"+data.winners).textContent +'</strong></p></div><div class="modal-footer"><form method="POST" id="game_over_form" action="Game_over"><button type="submit" class="btn btn-default" data-dismiss="modal">Close</button></form></div></div> </div></div> </div>');

                $("#myModal").modal("show");
                $('.modal-backdrop').appendTo('body');

            }
        }

    </script>
	
	</c:if>







	<c:if test="${ user_2.id_u==-1 }">
    <script>
    	var path2="Game_over";
        function insert(json) {
            var audio = new Audio('../MortsAndBlesses/Pages/audios/add.mp3');
            var data = JSON.parse(json);
            if (data.state != "end") {
                audio.play();
                $("#1").append(`
                <article style="margin-bottom:30px;">
            <h2>`+data.numbers.user_1+`</h2>
                    <article class="congrats">
                        <article class="row">
                            <span><span style="color: yellow;">`+data.users.user_1.morts+`</span>Mort! , </span>
                            <span><span style="color: yellow;">`+data.users.user_1.blesses+`</span>Blessé!</span>
                        </article>
                    </article> </article>`
                    );

                $("#2").append(`
                <article style="margin-bottom:30px;">
            <h2>`+data.numbers.user_2+`</h2>
                    <article class="congrats">
                        <article class="row">
                            <span><span style="color: yellow;">`+data.users.user_2.morts+`</span>Mort! , </span>
                            <span><span style="color: yellow;">`+data.users.user_2.blesses+`</span>Blessé!</span>
                        </article>
                    </article> </article>`);
                    reset();
        animateText();



            }
            else {

                var audio = new Audio('../MortsAndBlesses/Pages/audios/Gameover.mp3');
                audio.play();
                
                $("body").append('<div id="myModal" class="modal fade" role="dialog"><div class="modal-dialog"> <!-- Modal content--><div class="modal-content"><div class="modal-header"><h4 class="modal-title">Fin de Jeux !</h4></div><div class="modal-body"><p>Felicitation <strong>'+ document.getElementById("u_"+data.winners).textContent +'</strong></p></div><div class="modal-footer"><form method="POST" id="game_over_form" action="Profile"><button type="submit" class="btn btn-default" data-dismiss="modal">Close</button></form></div></div> </div></div> </div>');

                $("#myModal").modal("show");
                $('.modal-backdrop').appendTo('body');

            }
        }

    </script>
	
	</c:if>






<script>

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

</script>
<script>
    var isSend=false;
   	function virification (){
    var input1 = document.getElementById("input1");
    var input2 = document.getElementById("input2");
    var input3 = document.getElementById("input3");
    var input4 = document.getElementById("input4");
    const re = /^[1-9\b]+$/;
    if ((input1.value.trim()!="" ) && (input2.value.trim()!="" ) && (input3.value.trim()!="") && (input4.value.trim()!="")) {
        if (input1.value === input2.value || input1.value === input3.value || input1.value === input4.value || input2.value === input3.value || input2.value === input4.value || input3.value === input4.value) {
            alert("Erreur des nembres sont égaux.");
        }
        else {
            if ((input1.value>0 && input1.value < 10) && (input2.value>0 && input2.value< 10) && (input3.value>0 && input3.value <10 )&& (input4.value>0 && input4.value<10 )) {
            	document.getElementById("number").value=""+input1.value+input2.value+input3.value+input4.value;
            	//blokihom hna 
            	isSend=true;
            }
            else {
                alert("Erreur : des nombres non compatible avec les règles de jeu.");
            }
        }
    }
    else
        alert("Erreur un ou des champs vide.");
    }


</script>


     <script>
            var h1 = document.getElementsByTagName('h4')[0],
                
                seconds = 0, minutes = 0, hours = 0,
                t;
                if(sessionStorage.getItem("timer")){
                    var time =sessionStorage.getItem("timer");
                    var data=time.split(":");
                    if(parseInt(data[0])>0)
                    hours=data[0];
                    if(parseInt(data[1])>0)
                    minutes=data[1];
                    if(parseInt(data[2])>0)
                    seconds=data[2];
                }
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
                h1.textContent = (hours ? (hours > 9 ? hours : 0 + hours) : "00") + ":" + (minutes ? (minutes > 9 ? minutes : 0 + minutes) : "00") + ":" + (seconds > 9 ? seconds : 0 + seconds);
                sessionStorage.setItem("timer", (hours ? (hours > 9 ? hours : 0 + hours) : "00") + ":" + (minutes ? (minutes > 9 ? minutes : 0 + minutes) : "00") + ":" + (seconds > 9 ? seconds : 0 + seconds));
                timer();
            }
            function timer() {
                t = setTimeout(add, 1000);
            }
            timer();


        </script>
        
        
		<c:if test="${ user_2.id_u!=-1 }">
    		<script type="text/javascript">
    			path1="Game_over";
				var url = (location.host+location.pathname).replace("Game_play", "websocketgame/${jouer.room}/${user.id_u}");

				var webSocket = new WebSocket("ws://"+url);
		
				webSocket.onerror = function(event) {
					onError(event);
				};
		
				webSocket.onopen = function(event) {
					onOpen(event);
				};
		
				webSocket.onmessage = function(event) {
					onMessage(event);
				};
		
				function onMessage(event) {
					//had event.data hiya l message json lli kayjik dak json 3layach taf9na 
					//db nta 9ad dik lfonction dyal bach t3tiha dak json o hiya t9adhom f lbloc
					//  ++ o dir les inputes o lbotona itkwansaw mlli isift l user chi ra9m 7tta tjih on message 3ad iwlliw ikhdmo
					//  ++ o dir dik lfonction hya lli trje3 dok les input o lbona ikhdmo
					
					$('#btn').removeAttr('disabled');
					$('#input1').removeAttr('disabled');
					$('#input2').removeAttr('disabled');
					$('#input3').removeAttr('disabled');
					$('#input4').removeAttr('disabled');

					insert(event.data);
					
					
				}
		
				function onOpen(event) {
					
				}
		
				function onError(event) {
					location.href=(location.protocol+"//"+location.host+location.pathname).replace("Game_play", "Profile");
				}

				function send(a) {
					webSocket.send(a);
				}
				function sendNow(){
					isSend=false;
	    			virification ();
	    			
	    			if(isSend){
	    				let jj={};
	    				jj.number=document.getElementById("number").value;
	    				webSocket.send(JSON.stringify(jj));

						$('#btn').attr('disabled','none');
						$('#input1').attr('disabled','none');
						$('#input2').attr('disabled','none');
						$('#input3').attr('disabled','none');
						$('#input4').attr('disabled','none');
	    			    document.getElementById("input1").value="";
	    			    document.getElementById("input2").value="";
	    			    document.getElementById("input3").value="";
	    			    document.getElementById("input4").value="";
	    			    
	    			}
	    		}
			</script>
    	</c:if>
    	
    	
    	
        
        
		<c:if test="${ user_2.id_u==-1 }">
    		<script type="text/javascript">
    			path1="Profile";
				var url = (location.host+location.pathname).replace("Game_play", "websocketgamepc/${jouer.room}/${user.id_u}");

				var webSocket = new WebSocket("ws://"+url);
		
				webSocket.onerror = function(event) {
					onError(event);
				};
		
				webSocket.onopen = function(event) {
					onOpen(event);
				};
		
				webSocket.onmessage = function(event) {
					onMessage(event);
				};
		
				function onMessage(event) {
					//had event.data hiya l message json lli kayjik dak json 3layach taf9na 
					//db nta 9ad dik lfonction dyal bach t3tiha dak json o hiya t9adhom f lbloc
					//  ++ o dir les inputes o lbotona itkwansaw mlli isift l user chi ra9m 7tta tjih on message 3ad iwlliw ikhdmo
					//  ++ o dir dik lfonction hya lli trje3 dok les input o lbona ikhdmo
					
					$('#btn').removeAttr('disabled');
					$('#input1').removeAttr('disabled');
					$('#input2').removeAttr('disabled');
					$('#input3').removeAttr('disabled');
					$('#input4').removeAttr('disabled');

					insert(event.data);
					
					
				}
		
				function onOpen(event) {
					
				}
		
				function onError(event) {
					location.href=(location.protocol+"//"+location.host+location.pathname).replace("Game_play", "Profile");
				}

				function send(a) {
					webSocket.send(a);
				}
				function sendNow(){
					isSend=false;
	    			virification ();
	    			
	    			if(isSend){
	    				let jj={};
	    				jj.number=document.getElementById("number").value;
	    				webSocket.send(JSON.stringify(jj));

						$('#btn').attr('disabled','none');
						$('#input1').attr('disabled','none');
						$('#input2').attr('disabled','none');
						$('#input3').attr('disabled','none');
						$('#input4').attr('disabled','none');
	    			    document.getElementById("input1").value="";
	    			    document.getElementById("input2").value="";
	    			    document.getElementById("input3").value="";
	    			    document.getElementById("input4").value="";
	    			    
	    			}
	    		}
			</script>
    	</c:if>
    	
    	
    	
    	
    	
        
        
        
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>

</html>