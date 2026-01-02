package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------- ID ----------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    private Long id;

    // ---------- USERNAME ----------
    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    // ---------- EMAIL ----------
    @NotNull
    @NotBlank
    @Email
    @Size(max = 120)
    @Column(name = "email", nullable = false, unique = true, length = 120)
    private String email;

    // ---------- PASSWORD ----------
    @NotNull
    @NotBlank
    @Size(min = 4, max = 100)
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    // ---------- ROL ----------
    @NotNull
    @NotBlank
    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    // ---------- FECHA DE ALTA ----------
    @NotNull
    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    // ---------- ACTIVO ----------
    @NotNull
    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    // ---------- IMAGEN DE PERFIL (ruta de archivo o URL) ----------
    @Size(max = 250)
    @Column(name = "imagen_perfil", length = 250)
    private String imagenPerfil;  // Ruta o URL de la imagen del usuario



    // ======== CONSTRUCTORES ========
    public Usuario() {
        // Constructor vacío para JPA
    }

    public Usuario(Long id, String username, String email, String password, String rol) {
    	this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.activo = Boolean.TRUE;
    }
    
    

    public Usuario(@NotNull @NotBlank @Size(max = 30) String username,
			@NotNull @NotBlank @Email @Size(max = 120) String email,
			@NotNull @NotBlank @Size(min = 4, max = 100) String password, @NotNull String rol,
			@NotNull LocalDateTime fechaAlta, @NotNull Boolean activo, @Size(max = 250) String imagenPerfil) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
		this.imagenPerfil = imagenPerfil;
	}

	// Constructor completo (sin id)
    public Usuario(String username, String email, String password, String rol,
                   Boolean activo, String imagenPerfil) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
        this.imagenPerfil = imagenPerfil;
    }


    // ======== GETTERS Y SETTERS ========
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public String getImagenPerfil() { return imagenPerfil; }
    public void setImagenPerfil(String imagenPerfil) { this.imagenPerfil = imagenPerfil; }


    // ======== PRE-PERSIST ========
    @PrePersist
    private void prePersist() {
        if (fechaAlta == null) {
            fechaAlta = LocalDateTime.now();
        }
        if (activo == null) {
            activo = Boolean.TRUE;
        }
    }

    // ======== EQUALS & HASHCODE ========
    @Override
    public int hashCode() {
        return (username == null) ? 0 : username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Usuario other = (Usuario) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        return username != null && username.equals(other.username);
    }

    // ======== TO STRING ========
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fecha = (fechaAlta == null) ? "null" : fechaAlta.format(fmt);

        return "Usuario [id=" + id
                + ", username=" + username
                + ", email=" + email
                + ", rol=" + rol
                + ", activo=" + activo
                + ", fechaAlta=" + fecha
                + "]";
    }
}