INSERT INTO funcionario (nome, cpf, telefone, email, senha) VALUES
	('Bruno', '83756473891', '11985647381', 'bruno@gmail.com', '123456@'),
	('Fernando', '83756473892', '11985647382', 'fernando@gmail.com', '123456@'),
	('Giorgio', '83756473893', '11985647383', 'giorgio@gmail.com', '123456@'),
	('Guilherme', '83756473894', '11985647384', 'guilherme@gmail.com', '123456@'),
	('João', '83756473895', '11985647385', 'joao@gmail.com', '123456@'),
	('Leandro', '83756473896', '11985647386', 'leandro@gmail.com', '123456@');

INSERT INTO permissao (descricao) VALUES
	('Visualizar dashboard'),
	('Cadastrar funcionários'),
	('Visualizar histórico do estoque'),
    ('Registrar movimentação do estoque'),
	('Visualizar dados de itens do estoque'),
	('Cadastrar itens do estoque'),
	('Receber alertas de estoque');

INSERT INTO controle_acesso VALUES
	(1, 1),
	(1, 2),
	(1, 3),
    (2, 1),
	(2, 2),
	(2, 3),
    (3, 1),
	(3, 2),
	(3, 3),
    (4, 1),
	(4, 2),
	(4, 3),
    (5, 1),
	(5, 2),
	(5, 3),
	(6, 1),
	(6, 2),
	(6, 3);

INSERT INTO prateleira (codigo) VALUES
	('1R'),
	('2R'),
	('3R'),
	('1T'),
	('2T'),
	('3T');

INSERT INTO categoria (nome) VALUES
	('Tecido'),
	('Roupa');

INSERT INTO categoria (nome, id_categoria_pai) VALUES
	('Nylon', 1),
	('Poliéster', 1),
	('Algodão', 1),
	('Seda', 1),
	('Lã', 1),
	('Vestido', 2),
	('Camiseta', 2),
	('Calça', 2),
	('Shorts', 2),
	('Saia', 2);

INSERT INTO categoria (nome) VALUES
	('Azul'),
	('Vermelho'),
	('Verde'),
	('Amarelo'),
	('Cinza'),
	('Listrado'),
	('Liso'),
	('Florido'),
	('Grosso'),
	('Fino');

INSERT INTO item_estoque (id_categoria, id_prateleira, descricao, peso, qtd_minimo, qtd_armazenado, preco) VALUES
	(8, 1, 'Vestido azul florido', 1.0, 0, 0, null),
	(9, 2, 'Camiseta vermelha lisa', 1.0, 0, 0, null),
	(11, 3, 'Shorts cinza com listras vermelhas', 1.0, 0, 0, null),
	(5, 4, 'Tecido vermelho liso', 1.0, 0, 0, 100),
	(6, 5, 'Tecido azul florido', 1.0, 0, 0, 150),
	(3, 6, 'Tecido cinza liso', 1.0, 0, 0, 200);

INSERT INTO confeccao_roupa (roupa_id_item_estoque, tecido_id_item_estoque, qtd_tecido) VALUES
	(1, 5, 10.0),
    (2, 4, 10.0),
    (3, 4, 10.0),
    (3, 6, 10.0);

INSERT INTO caracteristica_item_estoque (id_categoria, id_item_estoque) VALUES
	(13, 1),
	(20, 1),
	(14, 2),
	(19, 2),
	(17, 3),
	(18, 3),
	(14, 3),
	(14, 4),
	(19, 4),
	(13, 5),
	(20, 5),
	(17, 6),
	(19, 6);

INSERT INTO parceiro (categoria, nome, telefone, email, endereco) VAlUES
	('costureira', 'Maria', '11938563748', 'maria@gmail.com', 'Rua X'),
	('costureira', 'Alice', '11938563748', 'alice@gmail.com', 'Rua Y'),
	('costureira', 'Rebeca', '11938563748', 'rebeca@gmail.com', 'Rua Z'),
	('fornecedor', 'Best Tecidos', '11918465729', 'best_tecidos@gmail.com', 'Rua 1'),
	('fornecedor', 'Fornecedor X', '11918465729', 'fornecedorx@gmail.com', 'Rua 2'),
	('fornecedor', 'Fornecedor Z', '11918465729', 'fornecedorys@gmail.com', 'Rua 3');

INSERT INTO lote (descricao, data_entrada, id_parceiro, id_funcionario) VAlUES
	('lote de roupas', now(), 1, 1),
	('lote de tecido', now(), 2, 1);

INSERT INTO lote_item_estoque (id_lote, id_item_estoque, qtd_item, preco) VALUES
    (1, 1, 5, 100.0),
    (1, 2, 3, 150.0),
    (1, 3, 10, 200.0),
    (2, 4, 3.5, 80.0),
	(2, 5, 6.5, 70.0),
	(2, 6, 12.5, 120.0);
