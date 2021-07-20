CREATE TABLE public.conta_pagar (
	id int4 NOT NULL,
	data_prevista timestamp NOT NULL,
	data_vencimento timestamp NOT NULL,
	descricao varchar NOT NULL,
	qtd_replica_meses int4,
	replica_meses bool NOT NULL,
	valor numeric NOT NULL,
	conta_id int4 NOT NULL,
	contapagarrateio_id int4,
	empresa_id int4 NOT NULL,
	fornecedor_id int4 NOT NULL,
	CONSTRAINT conta_pagar_pkey PRIMARY KEY (id),
	CONSTRAINT fk_anksto1o85kbjbjcq51ghn8nc FOREIGN KEY (contapagarrateio_id) REFERENCES public.conta_pagar_rateio(id),
	CONSTRAINT fk_emp3tg24dk28sbnxdlug9yc5v FOREIGN KEY (empresa_id) REFERENCES public.empresa(id),
	CONSTRAINT fk_nj77252pfawc4nqr7d99psug2 FOREIGN KEY (conta_id) REFERENCES public.conta(id),
	CONSTRAINT fk_on3542229mxn5cjn3yjpg5ebq FOREIGN KEY (fornecedor_id) REFERENCES public.fornecedor(id)
);

CREATE INDEX conta_pagar_pkey ON public.conta_pagar (id);