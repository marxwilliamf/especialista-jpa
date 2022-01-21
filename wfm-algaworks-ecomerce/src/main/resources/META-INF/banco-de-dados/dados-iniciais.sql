insert into produto(id, nome, preco, descricao) values(1, "Kindle", 499.00, "Conheça o novo Kindle, leia a qualquer hora do dia.");
insert into produto(id, nome, preco, descricao) values(3, "Câmera GoPro Hero 7", 1499, "Desempenho 2x melhor.");

insert into cliente(id, nome) values(1, 'Fernando Medeiros');
insert into cliente(id, nome) values(2, 'Marcos Mariano');

insert into pedido(id, cliente_id, data_criacao, total, status) values(1, 1, sysdate(), 998, 'AGUARDANDO');

insert into item_pedido(id, pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 1, 499, 2);

insert into categoria(id, nome) values(1, "Eletrônicos");