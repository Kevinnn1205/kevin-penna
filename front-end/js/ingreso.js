//se almacena la url de la API
var url = "http://localhost:8080/api/v1/ingreso/";
function listarIngreso() {
    //metodo para alistar los medicos
    //se crea la peticion AJAX
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            //success: funcion que se ejecuta 
            //cuando la peticion tiene exito
            console.log(result);
            //se crea un objeto que contenga
            //el cuerpo de la tabla
            var cuerpoTabla = document.getElementById("cuerpoTabla");
            //se limpia el cuerpo de la tabla
            cuerpoTabla.innerHTML = "";
            //Se hace un ciclo que recorra 
            //el arreglo con los datos
            for (var i = 0; i < result.length; i++) {
                //se crea una etiqueta tr por
                //cada registro
                var trRegistro = document.createElement("tr");
                let celdaId = document.createElement("td");

                //creamos un td por cada campo de registro

                let celdaHabitacion = document.createElement("td");
                let celdaCama = document.createElement("td");
                let celdaPaciente = document.createElement("td");
                let celdaMedico= document.createElement("td");
                let celdaFechaIngreso = document.createElement("td");
                let celdaFechaSalida = document.createElement("td");
                let celdaEstado = document.createElement("td");
                celdaId.innerText = result[i]["id_ingreso"];
               
                //se agrega la celda al registro una linea por cada campo 

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaHabitacion);
                trRegistro.appendChild(celdaCama);
                trRegistro.appendChild(celdaPaciente);
                trRegistro.appendChild(celdaMedico);
                trRegistro.appendChild(celdaFechaIngreso);
                trRegistro.appendChild(celdaFechaSalida);
                trRegistro.appendChild(celdaEstado);
                
                //se agrega el registro en la tabla 

                cuerpoTabla.appendChild(trRegistro);
                celdaHabitacion.innerText = result[i]["habitacion"];
                celdaCama.innerText = result[i]["cama"];
                celdaPaciente.innerText = result[i]["paciente"];
                celdaMedico.innerText = result[i]["medico"];
                celdaFechaIngreso.innerText = result[i]["fecha_ingreso"];
                celdaFechaSalida.innerText = result[i]["fecha_salida"];
                celdaEstado.innerText = result[i]["estado"];



            }
        },
        error: function (error) {
            //error: funcion que se ejecuta 
            //cuando la peticion tiene un error
            alert("Error en la peticion ${error}");

        }

    });
}

//se almacenan los valores
function registrarIngreso() {
    let forData = {
        "habitacion": document.getElementById("habitacion").value,
        "cama": document.getElementById("cama").value,
        "paciente": document.getElementById("paciente").value,
        "medico": document.getElementById("medico").value,
        "fecha_ingreso": document.getElementById("fecha_ingreso").value,
        "fecha_salida": document.getElementById("fecha_salida").value,
        "estado": document.getElementById("estado").value,
    };
    if (validarCampos()) {
        //se ejecuta la peticion
        $.ajax({

            url: url,
            type: "POST",
            data: forData,

            success: function (result) {
               alert("se guardo con exito". success);
            },
            error: function (error) {
                //error
                alert("Error al guardar", error);
            }
        });
    }
}

//Validar campo de documento de identidad paciente
function validarCampos() {
    var cama = document.getElementById("cama");
    return validarCama(cama);
}

function validarCama(cuadroNumero) {



    var valor = cuadroNumero.value;
    var valido = true;
    if (valor.length <= 1 || valor.length > 11) {
        valido = false;
    }

    if (valido) {
        //cuadro de texto cumple
        //se modifica la clase del cuadro de texto
        cuadroNumero.className = "form-control is-valid";
    } else {
        //cuadro de texto no cumple
        cuadroNumero.className = "form-control is-invalid"
    }
    return valido
}

function limpiar() {

    document.getElementById("habitacion").value = "";
    document.getElementById("cama").value = "";
    document.getElementById("paciente").value = "";
    document.getElementById("medico").value = "";
    document.getElementById("fecha_ingreso").value = "";
    document.getElementById("fecha_salida").value = "";
    document.getElementById("estado").value = "";

}
