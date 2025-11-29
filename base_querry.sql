Use barspring;

-- Clientes
INSERT INTO cliente (cpf, nome) VALUES (1, "Gleidson"), (2, "Ivan"), (3, "Gemesson"), (4, "Hillan");
INSERT INTO cliente (cpf, nome) VALUES (5, "Pedro");
INSERT INTO cliente (cpf, nome) VALUES (6, "Manuel");
SELECT * FROM cliente;
DELETE FROM cliente WHERE cpf < 10;

-- Cardapio
INSERT INTO item (num_item, tipo, valor, nome, disponivel) 
VALUES 
(1, 1, 10, "Ingresso", 1),
(2, 2, 5.6, "Coca-cola 1L", 1),
(3, 2, 8.5, "Coca-cola 2L", 1),
(4, 2, 10, "Caipirinha", 1),
(5, 2, 10, "Skol beats", 1),
(6, 3, 23, "Espetinho", 0),
(7, 3, 15, "Humburguer", 1),
(8, 3, 5.5, "Bolo de chocolate", 0),
(9, 3, 4, "Bolo de milho", 1),
(10, 3, 30, "Combo de salgados", 0);
SELECT * FROM item;

-- Criando algumas contas

INSERT INTO conta (cliente_cpf, aberta, pessoas) VALUES (1, 1, 5), (2, 1, 4), (3, 1, 2), (4, 1, 10);
SELECT * FROM conta;

-- Seleciona as contas e clientes
SELECT conta.id, cliente.nome, conta.gorjeta FROM Conta conta JOIN Cliente cliente ON cliente.cpf = conta.cliente_cpf;

-- Insere alguns consumos 
INSERT INTO consumo (quantidade, num_item, conta_id) 
VALUES 
(2, 3, 1), 
(10, 9, 1), 
(1, 10, 1), 
(6, 1, 1), 
(1, 2, 2), 
(2, 7, 2), 
(2, 8, 2), 
(1, 4, 3), 
(1, 6, 4);

-- Seleciona item e consumo
SELECT item.nome, item.num_item, consumo.quantidade FROM Item item JOIN Consumo consumo ON item.num_item = consumo.num_item;

-- Select composto
SELECT conta.id, cliente.cpf, cliente.nome, item.num_item, item.nome AS nomeItem, consumo.quantidade
FROM Conta conta
JOIN Cliente cliente ON cliente.cpf = conta.cliente_cpf
JOIN Consumo consumo ON consumo.conta_id = conta.id
JOIN Item item ON item.num_item = consumo.num_item;