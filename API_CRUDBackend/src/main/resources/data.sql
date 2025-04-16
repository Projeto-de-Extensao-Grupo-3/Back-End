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
	('Receber alertas de falta de estoque');

INSERT INTO controle_acesso VALUES
    (1, 3),
    (1, 4),
    (2, 3),
    (3, 3),
    (4, 3),
    (5, 3),
    (6, 3);

INSERT INTO item_estoque (categoria, descricao, complemento, peso, qtd_minimo, qtd_armazenado) VALUES
    ('roupa', 'vestido,azul', 'florido', 1.0, 0, 0),
    ('roupa', 'camisa,vermelho', 'liso', 1.0, 0, 0),
    ('roupa', 'bermuda,cinza', 'liso', 1.0, 0, 0),
    ('tecido', 'vermelho', 'liso', 1.0, 0, 0),
    ('tecido', 'azul', 'florido', 1.0, 0, 0),
    ('tecido', 'cinza', 'liso', 1.0, 0, 0);

INSERT INTO servico_terceiro (categoria, nome, telefone, email, endereco) VAlUES
	('costureira', 'Maria', '11938563748', 'maria@gmail.com', 'Rua X'),
	('costureira', 'Alice', '11938563748', 'alice@gmail.com', 'Rua Y'),
	('costureira', 'Rebeca', '11938563748', 'rebeca@gmail.com', 'Rua Z'),
	('fornecedor', 'Best Tecidos', '11918465729', 'best_tecidos@gmail.com', 'Rua 1'),
	('fornecedor', 'Fornecedor X', '11918465729', 'fornecedorx@gmail.com', 'Rua 2'),
	('fornecedor', 'Fornecedor Z', '11918465729', 'fornecedorys@gmail.com', 'Rua 3');