CREATE TABLE public.conta (
	id int4 NOT NULL,
	descricao varchar NOT NULL,
	pai int4,
	tipo int4,
	CONSTRAINT conta_pkey PRIMARY KEY (id),
	CONSTRAINT fk_aytb8vqylvteel5cd3let2nvj FOREIGN KEY (pai) REFERENCES public.conta(id),
	CONSTRAINT fk_o0d88200fx5vfff532ld2khtn FOREIGN KEY (tipo) REFERENCES public.tipo_conta(id)
);

CREATE INDEX conta_pkey ON public.conta (id);


INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
1,'IMPOSTOS S/ SERVIÇOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
2,'SIMPLES',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
3,'ISSQN',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
4,'PIS',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
5,'COFINS',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
6,'CSSL',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
7,'IRPJ',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
8,'Taxa Bombeiros',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
9,'Taxa Fiscalização ',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
10,'DESPESAS C/ PESSOAL',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
11,'Salários e Ordenados',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
12,'Honorários Serviços Terceiros',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
13,'13º Salário',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
14,'Férias',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
15,'INSS',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
16,'FGTS',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
17,'Vale Transporte',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
18,'Auxílio Combustível',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
19,'Auxílio Refeição',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
20,'Auxílio Educação',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
21,'IR S/ Folha de Pagamento',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
22,'Desp. Admissões / Rescisões',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
23,'DEMAIS DESPESAS COM PESSOAL',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
24,'Cursos e Treinamentos',23,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
25,'Uniformes',23,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
26,'Despesas Aniver. / Comemorações',23,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
27,'PROLABORE',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
28,'PRO LABORE',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
29,'IR S/ Prolabore',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
30,'Auxílio Refeição',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
31,'INSS',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
32,'DESPESAS ADMINISTRATIVAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
33,'Energia Elétrica',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
34,'Água',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
35,'Aluguéis',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
36,'Telefonia',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
37,'Despesas com Veículos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
38,'Combustíveis',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
39,'Despesas Viagens, Estadias e Refeições',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
40,'Material de Expediente',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
41,'Carimbos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
42,'Insumos / Equipamentos Prest. Serviços',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
43,'Manut. e Conserv. Imobilizado',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
44,'Bens não Imobilizados',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
45,'Assinaturas Jornais e Revistas',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
46,'Ass. de Informativos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
47,'Mensalidades de Entidades',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
48,'Mensalidades',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
49,'Manutenção do Sistema',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
50,'Serviços Terceiros',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
51,'Despesas com Assembleias',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
52,'Comissões',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
53,'Multas/Juros Erros Operacionais',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
54,'Perdas/Danos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
55,'Supermercado / Mat. Limpeza / Água',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
56,'Farmácia',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
57,'Livros Fiscais Clientes',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
58,'Taxas Pagas a Clientes',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
59,'Despesas Postais Clientes',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
60,'Correio',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
61,'Despesas Diversas',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
62,'DESPESAS COMERCIAIS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
63,'Investimentos Sociais',62,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
64,'Propaganda e Publicidade',62,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
65,'Patrocínio',62,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
66,'Brindes / Doações',62,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
67,'DESPESAS FINANCEIRAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
68,'Desp. Bancárias',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
69,'Tarifas Cobranças Bancárias',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
70,'I.O.F',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
71,'Juros Cheque Especial',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
72,'Juros Títulos',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
73,'Descontos Concedidos',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
74,'RECEITAS FINANCEIRAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
75,'Juros Títulos',74,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
76,'Rendimentos Aplicação',74,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
77,'DESP. TRIBUTÁRIAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
78,'Impostos e Taxas',77,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
79,'IPTU',77,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
80,'INVESTIMENTOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
81,'Móveis e Utensílios',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
82,'Máquinas e Equipamentos',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
83,'Veículos',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
84,'Equip. Informática',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
85,'TRANSFERÊNCIAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
86,'Impostos Pagos à Clientes',85,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
87,'Recebimentos Impostos Clientes',85,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
88,'Transferência entre C/C',85,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
89,'EMPRÉSTIMOS / FINANCIAMENTOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
90,'Álvaro Lorenzi',89,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
91,'Cleimar Sfredo',89,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
92,'Aluguel Nilo Tomasi',89,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
93,'Financiamento Habitacional',89,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
94,'Aporte',89,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
95,'PARCELAMENTOS IMPOSTOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
96,'Parcelamento Previdenciário',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
97,'Parcelamento Municipal',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
98,'Parcelamento Federal',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
99,'Processo Tomasi Grafic',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
100,'DESPESAS SÓCIOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
101,'Sandro Tomasi',100,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
102,'Renan Tomasi',100,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
130,'RECEITAS',NULL,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
103,'Receitas de Honorários',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
104,'Receitas 13º Honorários',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
105,'Receitas Extras',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
106,'Reembolso Despesas ',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
107,'Receitas IRPF',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
108,'Serviços Homologações',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
109,'Receitas Inadimplência',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
110,'Receitas Alterações Contratuais',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
111,'Receitas Abertura/Baixa de Empresas',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
112,'Receita de Assessorias',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
113,'Receita de Consultas',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
114,'Receita Tributária',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
115,'Receita Previdenciária',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
116,'Receita Cível',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
117,'Receita Trabalhista',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
118,'Receita Processos Poupança',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
119,'Receita Cobranças Extrajudiciais',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
120,'Receita Seleção de Pessoal',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
121,'Receita Manutenção Estágios',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
122,'Receita de Treinamento',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
123,'Receita Limpeza Predial',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
124,'Receita Limpeza Empresarial',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
125,'Receita Limpeza Extra',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
126,'Receita de Locação Imóveis',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
127,'Receitas Compra e Venda Imóveis',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
128,'Receitas Assembleias',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
129,'Receitas Juros S/ Saldos Devedores',130,1);