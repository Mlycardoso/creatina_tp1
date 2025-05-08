/* 

- IMPORTS PARA AMBIENTE DE DEV.
- COMENTAR TODOS DURANTE TESTES EM AMBIENTE DEV.

*/
INSERT INTO marcas (nome, paisorigem, data_cadastro) VALUES
('Integralmédica', 'Brazil', NOW()),
('Max Titanium', 'USA', NOW()),
('Growth', 'USA', NOW()),
('Universal Nutrition', 'USA', NOW()),
('Probiótica', 'Brazil', NOW());


INSERT INTO componentes (nome, descricao, quantidade, concentracao, data_cadastro) VALUES
('Proteína Isolada', 'Alta pureza', 150, 1, NOW()),
('Carboidrato Complexo', 'Fonte de energia', 100, 2, NOW()),
('BCAA', 'Aminoácidos essenciais', 120, 3, NOW()),
('Cafeína', 'Estimulante natural', 50, 1, NOW());

INSERT INTO categorias (nome, data_cadastro) VALUES
('Pré-Treino', NOW()),
('Pós-Treino', NOW()),
('Saúde Geral', NOW()),
('Emagrecimento', NOW());

INSERT INTO fornecedores (nome, telefone, cnpj, email, data_cadastro) VALUES
('Suplementos BR', '(11) 99999-0001', '12.345.678/0001-90', 'contato@suplementosbr.com', NOW()),
('NutriX Importadora', '(21) 98888-0002', '98.765.432/0001-11', 'vendas@nutrix.com.br', NOW()),
('MusclePro Distribuidora', '(31) 97777-0003', '11.222.333/0001-55', 'suporte@musclepro.com', NOW()),
('BodyPlus Suplementos', '(41) 96666-0004', '55.444.333/0001-77', 'info@bodyplus.com.br', NOW());
