package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private ProdutoRepository produtoRepository;

	@InjectMocks
	private ProdutoService produtoService;

	@Test
	void deveRetornarProdutoQuandoIdExistir() {
		Produto mock = new Produto(1L, "TV", 1500);
		when(produtoRepository.findById(1L)).thenReturn(Optional.of(mock));
		Produto buscarPorId = produtoService.buscarPorId(1L);
		assertEquals(mock, buscarPorId);
	}

	@Test
	void deveLancarExcecaoQuandoProdutoNaoExistir() {
		when(produtoRepository.findById(1L)).thenThrow(new RuntimeException("Produto não encontrado"));
		
		assertThrows(RuntimeException.class, () -> {
			produtoService.buscarPorId(null);
		});
	}
}
