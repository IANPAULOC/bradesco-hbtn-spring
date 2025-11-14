package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = new ProdutoService();
	}

	@GetMapping
	public List<Produto> listarProdutos() {
		return this.produtoService.listarProdutos();
	}

	@PostMapping
	public Produto adicionarProduto(@RequestBody Produto produto) {
		return this.produtoService.adicionarProduto(produto);
	}

	@PutMapping("/{id}")
	public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
		return this.produtoService.atualizarProduto(id, produto);
	}

	@DeleteMapping("/{id}")
	public String deletarProduto(@PathVariable Long id) {
		boolean deletarProduto = this.produtoService.deletarProduto(id);
		if (deletarProduto) {
			return "Produto removido com sucesso!";
		}
		return "Produto não encontrado!";
	}
}
