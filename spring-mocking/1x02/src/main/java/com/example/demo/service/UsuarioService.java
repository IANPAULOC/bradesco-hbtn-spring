package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarUsuarioPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			throw new RuntimeException("Usuário não encontrado!");
		}
		return usuario.get();
	}

	public Usuario salvarUsuario(Usuario usuario) {
		Usuario userSave = usuarioRepository.save(usuario);
		return userSave;
	}
}
