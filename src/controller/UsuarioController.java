/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import dao.UsuarioDAO;
import jdbcFactory.ConnectionFactory;
import modelo.Usuario;
/**
 *
 * @author CDM-51
 */
public class UsuarioController {
    
     private final UsuarioDAO usuarioDAO;

    /**
     * Creando conexion para operaciones con MySQL con la tabla de usuarios.
     */
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().realizarConexion());
    }

    /**
     * Obteniendo el listado del DAO de usuarios.
     *
     */
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    /**
     * Permite consultar los usuarios acorde al nombre de usuario.
     *
     */
    public List<Usuario> listar(String categoriaUsuario) {
        return usuarioDAO.listar(categoriaUsuario);
    }

    /**
     * Obtiene los valores nombre y contraseña en el modelo view, para ser
     * validados con la Base de Datos.
     *
     */
    public List<Usuario> listar(String nombreUsuario, String password) {
        return usuarioDAO.listar(nombreUsuario, password);
    }

    /**
     * Almacenando los atributos del Usuario obtenidos en el modelo view.
     *
     */
    public void guardar(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }

    /**
     * Recopila los datos del usuario obtenidos en el modelo View.
     *
     */
    public int actualizar(Integer idUsuario, String nombreUsuario,
            String categoriaUsuario, String password) {
        return usuarioDAO.actualizar(idUsuario, nombreUsuario, categoriaUsuario, password);
    }

    /**
     * Obtiene el id del usuario como referencia para la eliminación de
     * registro.
     *
     */
    public int eliminar(Integer IdUsuario) {
        return usuarioDAO.eliminar(IdUsuario);
    }
}
