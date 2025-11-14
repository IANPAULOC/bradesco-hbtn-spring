package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;

@Service
public class ProdutoService {
	private List<Produto> produtos = new ArrayList<>();
	private Long contadorId = 1L;

	public List<Produto> listarProdutos() {
		return this.produtos;
	}

	public Produto adicionarProduto(Produto produto) {
		if (this.produtos.isEmpty()) {
			produto.setId(contadorId);
			this.produtos.add(produto);
		} else {
			Produto ultimo = this.produtos.get(this.produtos.size() - 1);
			produto.setId(ultimo.getId() + contadorId);
			this.produtos.add(produto);
		}

		return produto;
	}

	public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
		for (int i = 0; i < this.produtos.size(); i++) {
			Produto produto = this.produtos.get(i);
			if (produto.getId().equals(id)) {
				produtoAtualizado.setId(id);
				this.produtos.set(i, produtoAtualizado);
			}
		}
		return produtoAtualizado;
	}

	public boolean deletarProduto(Long id) {
		for (Produto produto : this.produtos) {
			if (produto.getId().equals(id)) {
				this.produtos.remove(produto);
				return true;
			}
		}
		return false;
	}
}
