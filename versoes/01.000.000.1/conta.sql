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
1,'IMPOSTOS S/ SERVI�OS',NULL,2);
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
9,'Taxa Fiscaliza��o ',1,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
10,'DESPESAS C/ PESSOAL',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
11,'Sal�rios e Ordenados',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
12,'Honor�rios Servi�os Terceiros',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
13,'13� Sal�rio',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
14,'F�rias',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
15,'INSS',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
16,'FGTS',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
17,'Vale Transporte',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
18,'Aux�lio Combust�vel',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
19,'Aux�lio Refei��o',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
20,'Aux�lio Educa��o',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
21,'IR S/ Folha de Pagamento',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
22,'Desp. Admiss�es / Rescis�es',10,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
23,'DEMAIS DESPESAS COM PESSOAL',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
24,'Cursos e Treinamentos',23,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
25,'Uniformes',23,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
26,'Despesas Aniver. / Comemora��es',23,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
27,'PROLABORE',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
28,'PRO LABORE',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
29,'IR S/ Prolabore',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
30,'Aux�lio Refei��o',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
31,'INSS',27,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
32,'DESPESAS ADMINISTRATIVAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
33,'Energia El�trica',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
34,'�gua',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
35,'Alugu�is',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
36,'Telefonia',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
37,'Despesas com Ve�culos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
38,'Combust�veis',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
39,'Despesas Viagens, Estadias e Refei��es',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
40,'Material de Expediente',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
41,'Carimbos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
42,'Insumos / Equipamentos Prest. Servi�os',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
43,'Manut. e Conserv. Imobilizado',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
44,'Bens n�o Imobilizados',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
45,'Assinaturas Jornais e Revistas',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
46,'Ass. de Informativos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
47,'Mensalidades de Entidades',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
48,'Mensalidades',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
49,'Manuten��o do Sistema',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
50,'Servi�os Terceiros',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
51,'Despesas com Assembleias',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
52,'Comiss�es',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
53,'Multas/Juros Erros Operacionais',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
54,'Perdas/Danos',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
55,'Supermercado / Mat. Limpeza / �gua',32,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
56,'Farm�cia',32,2);
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
65,'Patroc�nio',62,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
66,'Brindes / Doa��es',62,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
67,'DESPESAS FINANCEIRAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
68,'Desp. Banc�rias',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
69,'Tarifas Cobran�as Banc�rias',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
70,'I.O.F',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
71,'Juros Cheque Especial',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
72,'Juros T�tulos',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
73,'Descontos Concedidos',67,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
74,'RECEITAS FINANCEIRAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
75,'Juros T�tulos',74,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
76,'Rendimentos Aplica��o',74,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
77,'DESP. TRIBUT�RIAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
78,'Impostos e Taxas',77,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
79,'IPTU',77,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
80,'INVESTIMENTOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
81,'M�veis e Utens�lios',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
82,'M�quinas e Equipamentos',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
83,'Ve�culos',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
84,'Equip. Inform�tica',80,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
85,'TRANSFER�NCIAS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
86,'Impostos Pagos � Clientes',85,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
87,'Recebimentos Impostos Clientes',85,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
88,'Transfer�ncia entre C/C',85,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
89,'EMPR�STIMOS / FINANCIAMENTOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
90,'�lvaro Lorenzi',89,2);
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
96,'Parcelamento Previdenci�rio',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
97,'Parcelamento Municipal',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
98,'Parcelamento Federal',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
99,'Processo Tomasi Grafic',95,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
100,'DESPESAS S�CIOS',NULL,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
101,'Sandro Tomasi',100,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
102,'Renan Tomasi',100,2);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
130,'RECEITAS',NULL,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
103,'Receitas de Honor�rios',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
104,'Receitas 13� Honor�rios',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
105,'Receitas Extras',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
106,'Reembolso Despesas ',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
107,'Receitas IRPF',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
108,'Servi�os Homologa��es',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
109,'Receitas Inadimpl�ncia',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
110,'Receitas Altera��es Contratuais',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
111,'Receitas Abertura/Baixa de Empresas',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
112,'Receita de Assessorias',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
113,'Receita de Consultas',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
114,'Receita Tribut�ria',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
115,'Receita Previdenci�ria',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
116,'Receita C�vel',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
117,'Receita Trabalhista',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
118,'Receita Processos Poupan�a',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
119,'Receita Cobran�as Extrajudiciais',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
120,'Receita Sele��o de Pessoal',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
121,'Receita Manuten��o Est�gios',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
122,'Receita de Treinamento',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
123,'Receita Limpeza Predial',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
124,'Receita Limpeza Empresarial',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
125,'Receita Limpeza Extra',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
126,'Receita de Loca��o Im�veis',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
127,'Receitas Compra e Venda Im�veis',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
128,'Receitas Assembleias',130,1);
INSERT INTO public.conta (id,descricao,pai,tipo) VALUES (
129,'Receitas Juros S/ Saldos Devedores',130,1);