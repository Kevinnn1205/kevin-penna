package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "medico")
public class medico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_medico", nullable = false, length = 36)
    private String id_medico;
 
    @Column(name = "documento_identidad", nullable = false, length = 11)
    private String documento_identidad;

    @Column(name = "primer_nombre", nullable = false, length = 36)
    private String primer_nombre;

    @Column(name = "segundo_nombre", nullable = false, length = 36)
    private String segundo_nombre;

    @Column(name = "primer_apellido", nullable = false, length = 36)
    private String primer_apellido;

    @Column(name = "segundo_apellido", nullable = false, length = 36)
    private String segundo_apellido;

    @Column(name = "celular", nullable = false, length = 13)
    private String celular;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;

	public medico() {
		super();
	}

	public medico(String id_medico, String documento_identidad, String primer_nombre, String segundo_nombre,
			String primer_apellido, String segundo_apellido, String celular, String correo, String estado) {
		super();
		this.id_medico = id_medico;
		this.documento_identidad = documento_identidad;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.celular = celular;
		this.correo = correo;
		this.estado = estado;
	}

	public String getId_medico() {
		return id_medico;
	}

	public void setId_medico(String id_medico) {
		this.id_medico = id_medico;
	}

	public String getDocumento_identidad() {
		return documento_identidad;
	}

	public void setDocumento_identidad(String documento_identidad) {
		this.documento_identidad = documento_identidad;
	}

	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}

	public String getSegundo_nombre() {
		return segundo_nombre;
	}

	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
