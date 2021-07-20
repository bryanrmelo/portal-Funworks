CREATE TABLE public.rateio (
	id int4 NOT NULL,
	percentual float8,
	empresa_id int4,
	tipo_rateio int4,
	CONSTRAINT rateio_pkey PRIMARY KEY (id),
	CONSTRAINT fk_a4emfxrapbpvnt4vqunmmwfug FOREIGN KEY (empresa_id) REFERENCES public.empresa(id),
	CONSTRAINT fk_dakls1hw67od4mcmgu9enkpc9 FOREIGN KEY (tipo_rateio) REFERENCES public.tipo_rateio(id)
);

CREATE INDEX rateio_pkey ON public.rateio (id);

INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
1,57.0,1,1);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
2,17.0,3,1);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
3,26.0,2,1);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
4,15.0,6,2);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
5,70.0,4,2);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
6,15.0,5,2);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
7,17.0,1,3);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
8,16.5,3,3);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
9,16.5,2,3);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
10,16.5,6,3);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
11,17.0,4,3);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
12,16.5,5,3);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
13,34.0,1,4);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
14,33.0,3,4);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
15,33.0,2,4);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
16,34.0,6,5);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
17,33.0,4,5);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
18,33.0,5,5);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
19,52.0,1,6);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
20,14.0,3,6);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
21,8.0,2,6);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
22,5.0,6,6);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
23,14.0,4,6);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
24,7.0,5,6);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
25,69.0,1,7);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
26,19.0,3,7);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
27,12.0,2,7);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
28,17.0,6,8);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
29,55.0,4,8);
INSERT INTO public.rateio (id,percentual,empresa_id,tipo_rateio) VALUES (
30,28.0,5,8);
