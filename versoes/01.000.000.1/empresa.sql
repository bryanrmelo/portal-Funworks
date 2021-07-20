CREATE TABLE public.empresa (
	id int4 NOT NULL,
	ativa bool NOT NULL,
	descricao varchar NOT NULL,
	sacado bool NOT NULL,
	CONSTRAINT empresa_pkey PRIMARY KEY (id)
);

CREATE INDEX empresa_pkey ON public.empresa (id);


INSERT INTO public.empresa (id,ativa,descricao,sacado) VALUES (
7,true,'Outros',true);
INSERT INTO public.empresa (id,ativa,descricao,sacado) VALUES (
6,true,'Tomasi Condom�nios e Im�veis',false);
INSERT INTO public.empresa (id,ativa,descricao,sacado) VALUES (
5,true,'J.P. Arioli Servi�os',false);
INSERT INTO public.empresa (id,ativa,descricao,sacado) VALUES (
4,true,'L.D.G. Tomasi Servi�os',false);
INSERT INTO public.empresa (id,ativa,descricao,sacado) VALUES (
3,true,'Tomasi RH',false);
INSERT INTO public.empresa (id,ativa,descricao,sacado) VALUES (
2,true,'Nilo Tomasi Advogado',false);
INSERT INTO public.empresa (id,ativa,descricao,sacado) VALUES (
1,true,'Tomasi Contabilidade',false);
