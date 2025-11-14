package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository mockreRepository;

	@InjectMocks
	private UsuarioService mockService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void deveRetornarUsuarioQuandoIdExistir() {
		Usuario usuario = new Usuario(1L, "Jhon Doe", "jhon.doe@mail.com");
		when(mockreRepository.findById(1L)).thenReturn(Optional.of(usuario));

		Usuario buscarUsuarioPorId = mockService.buscarUsuarioPorId(1L);

		assertEquals(buscarUsuarioPorId, usuario);
	}

	@Test
	void deveLancarExcecaoQuandoUsuarioNaoExistir() {
		when(mockreRepository.findById(1L)).thenThrow(new RuntimeException("Usuário não encontrado!"));

		assertThrows(RuntimeException.class, () -> {
			mockService.buscarUsuarioPorId(1L);
		});
	}

	@Test
	void deveSalvarUsuarioComSucesso() {
		Usuario usuario = new Usuario(1L, "Jhon Doe", "jhon.doe@mail.com");
		when(mockreRepository.save(usuario)).thenReturn(usuario);

		Usuario salvarUsuario = mockService.salvarUsuario(usuario);

		assertEquals(salvarUsuario, usuario);
	}

}
