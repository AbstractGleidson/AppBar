-- Clientes
INSERT INTO cliente (CPF, nome) VALUES (1, "Gleidson"), (2, "Ivan"), (3, "Gemesson"), (4, "Hillan");
INSERT INTO cliente (CPF, nome) VALUES (5, "Pedro");
INSERT INTO cliente (CPF, nome) VALUES (6, "Manuel");
SELECT * FROM cliente;
DELETE FROM cliente WHERE CPF < 10;

-- Cardapio
INSERT INTO item (numItem, tipo, valor, nome) 
VALUES 
(1, 1, 10, "Ingresso"),
(2, 2, 5.6, "Coca-cola 1L"),
(3, 2, 8.5, "Coca-cola 2L"),
(4, 2, 10, "Caipirinha"),
(5, 2, 10, "Skol beats"),
(6, 3, 23, "Espetinho"),
(7, 3, 15, "Humburguer"),
(8, 3, 5.5, "Bolo de chocolate"),
(9, 3, 4, "Bolo de milho"),
(10, 3, 30, "Combo de salgados");
SELECT * FROM item;

-- Criando algumas contas

INSERT INTO conta (Cliente_CPF) VALUES (1), (2), (3), (4);
SELECT * FROM conta;

-- Seleciona as contas e clientes
SELECT conta.idConta, cliente.nome, conta.gorjeta FROM Conta conta JOIN Cliente cliente ON cliente.CPF = conta.Cliente_CPF;

-- Insere alguns consumos 
INSERT INTO consumo (quantidade, Item_numItem, Conta_idConta) 
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
SELECT item.nome, item.numItem, consumo.quantidade FROM Item item JOIN Consumo consumo ON item.numItem = consumo.Item_numItem;

-- Select composto
SELECT conta.idConta, cliente.CPF, cliente.nome, item.numItem, item.nome AS nomeItem, consumo.quantidade
FROM Conta conta
JOIN Cliente cliente ON cliente.CPF = conta.Cliente_CPF
JOIN Consumo consumo ON consumo.Conta_idConta = conta.idConta
JOIN Item item ON item.numItem = consumo.Item_numItem;