function guardar(){
var correo = document.querySelector("#mail").value;
var usuario = document.querySelector("#usuario").value;
var clave = document.querySelector("#clave").value;
var selected = document.querySelector("#tipo");
var tipo = selected.options[selected.selectedIndex].text;
   
 // var usu = btoa(usuario);
 // var cla = btoa(clave);
 var data = {
     "usuario":usuario,
     "clave":clave,
     "tipo":tipo,
     "correo":correo
 } ;
 
     $.ajax({
                data:  data,
                url:   'Manejador',
                type:  'post',
                beforeSend: function () {
                        $("#resultado").html("Procesando, espere por favor...");
                },
                success:  function (response) {      
                    $("#resultado").html(response);
                }
        });
}

    function registrarse(){
    
    var usuario = document.querySelector("#usuario").value;
var clave = document.querySelector("#clave").value;

//var usu = btoa(usuario);
// var cla = btoa(clave);
 var data = {
     "usuario":usuario,
     "clave":clave
 };
// var data = JSON.stringify(data1);
 
     $.ajax({
                data:  data,
                url:"Manejador",
                type:  'get',
                beforeSend: function () {
                        $("#resultado").html("Procesando, espere por favor...");
                },
                success:  function (response) {
                  var jwt = require('jwtjs');
                 var secret = "1234";
                    
                 var decoded = jwt.decode(response, secret, false, 'HS256');
                 console.log(decoded);
                        $("#resultado").html(response);
                }
        });
}


