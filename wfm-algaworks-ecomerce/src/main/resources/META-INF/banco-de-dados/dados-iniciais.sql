insert into produto(id, nome, preco, data_criacao, descricao) values(1, "Kindle", 499.00, date_sub(sysdate(), interval 1 day), "Conheça o novo Kindle, leia a qualquer hora do dia.");
insert into produto(id, nome, preco, data_criacao, descricao) values(3, "Câmera GoPro Hero 7", 1499, date_sub(sysdate(), interval 1 day), "Desempenho 2x melhor.");

insert into cliente(id, nome, cpf) values(1, 'Fernando Medeiros', '000.000.000-01');
insert into cliente(id, nome, cpf) values(2, 'Marcos Mariano', '000.000.000-02');
insert into cliente(id, nome, cpf) values(3, 'Matheus Mariano', '000.000.000-03');

insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values(1, 'MASCULINO', date_sub(sysdate(), interval 27 year));
insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values(2, 'MASCULINO', date_sub(sysdate(), interval 30 year));
insert into cliente_detalhe(cliente_id, sexo, data_nascimento) values(3, 'MASCULINO', date_sub(sysdate(), interval 30 year));

insert into pedido(id, cliente_id, data_criacao, total, status) values(1, 1, sysdate(), 998, 'AGUARDANDO');
insert into pedido(id, cliente_id, data_criacao, total, status) values(2, 1, sysdate(), 499, 'AGUARDANDO');

insert into item_pedido(pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);
insert into item_pedido(pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);

insert into pagamento(pedido_id, status, numero_cartao, tipo_pagamento) values(2, "PROCESSANDO", "123", "cartao");

insert into categoria(id, nome) values(1, "Eletrônicos");
