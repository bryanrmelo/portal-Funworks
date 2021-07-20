CREATE TABLE public.fornecedor (
	id int4 NOT NULL,
	ativa bool NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT fornecedor_pkey PRIMARY KEY (id)
);

CREATE INDEX fornecedor_pkey ON public.fornecedor (id);

INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
1,true,'Álvaro Lorenzi');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
2,true,'Apescont');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
3,true,'Associação de Transporte Vino');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
4,true,'Associação RH Bento Gonçalves');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
5,true,'Auto Posto SP');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
6,true,'Bento Encadernações');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
7,true,'Bento Materiais para Escritório');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
8,true,'Business Editora e Publ. de Informativos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
9,true,'Caixa Econômica Federal');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
10,true,'Califórnia Comércio de Alimentos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
11,true,'Calltec Assistência Técnica Comunicações');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
12,true,'Center Luz Iluminação');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
13,true,'CIA Apolo de Supermercados');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
14,true,'CIC BG');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
15,true,'Claro S/A');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
16,true,'Cleimar Sfredo');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
17,true,'Corsan');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
18,true,'DIMED S/A Distribuidora de Medicamentos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
19,true,'Diogo Farina');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
20,true,'Embratel S/A');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
21,true,'Empresa Bras. de Correios e Telégrafos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
22,true,'Empresa Bras. de Tecn. e Adm de Convênios');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
23,true,'Fisconet Edições de Periódicos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
24,true,'Global Village Telecom S/A');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
25,true,'Grupo Serranossa');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
26,true,'Impretec Soluções em Informática');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
27,true,'Inviolável Sistemas de Segurança');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
28,true,'IST Informática');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
29,true,'J B Software');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
30,true,'Jornal do Comércio');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
31,true,'Junta Comercial RS');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
32,true,'La Cantina Del Piero');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
33,true,'Lefisc Legislação Fiscal');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
34,true,'Lex Empreendimentos Imobiliários');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
35,true,'LG Embalagens');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
36,true,'Limpbento Com. Produtos para Limpeza');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
37,true,'Locktec Tecnologia Integrada');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
38,true,'Loja de Máquinas Com. Manutenção Motos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
39,true,'Mário Schmidt');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
40,true,'Mundi Desenvolvimento Pessoal e Organizacional');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
41,true,'Município de Bento Gonçalves');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
42,true,'OAB');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
43,true,'Papelaria Dal Prá');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
44,true,'Personal Malotes');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
45,true,'PM Assessoria');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
46,true,'Pontograv Carimbos, Cortes e Gravações a Laser');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
47,true,'Prefeitura Municipal de Garibaldi');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
48,true,'Primazia Alimentos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
49,true,'Rek Parking');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
50,true,'RGE');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
51,true,'RR Acomodações');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
52,true,'Santalúcia Com. Material Escolar');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
53,true,'Scalco Express');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
54,true,'Serra Cafés Comércio de Máquinas');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
55,true,'Siescon Processamento de Dados');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
56,true,'STIMMME BG');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
57,true,'Tabelionato de Notas Damo');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
58,true,'Tabelionato de Protestos de Títulos');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
59,true,'Tabelionato Garcez');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
60,true,'Unimed Nordeste RS');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
61,true,'Valorizza Serviços de Informática');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
62,true,'Victorio Bock');
INSERT INTO public.fornecedor (id,ativa,descricao) VALUES (
63,true,'Outros');
