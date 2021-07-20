CREATE TABLE public.rateio_empresa (
	id int4 NOT NULL,
	valor numeric,
	empresa_id int4,
	CONSTRAINT rateio_empresa_pkey PRIMARY KEY (id),
	CONSTRAINT fk_n03hyie8f05gnmjcctjqw0ect FOREIGN KEY (empresa_id) REFERENCES public.empresa(id)
);

CREATE INDEX rateio_empresa_pkey ON public.rateio_empresa (id);


INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
862,300.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
863,55.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
864,6.64,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
865,90.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
868,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
872,200.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
873,200.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
874,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
875,100.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
876,100.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
878,200.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
879,200.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
880,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
881,100.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
882,100.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
886,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
888,100.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
889,200.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
892,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
898,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
904,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
910,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
916,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
938,200.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
939,200.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
940,200.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
941,100.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
942,100.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
884,15.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
885,70.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
887,15.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
944,225.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
945,150.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
947,2500.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
883,400.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
877,400.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
943,400.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
949,300.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
950,300.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
954,300.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
955,100.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
957,50.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
962,500.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
963,500.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
964,500.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
965,500.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
966,500.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
967,500.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
968,500.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
969,500.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
970,500.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
971,500.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
980,1000.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
981,1000.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
982,1000.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
983,1000.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
984,1000.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
985,1000.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
986,1000.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
987,1000.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
988,1000.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
989,1000.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
990,1000.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
991,1000.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
992,1000.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
993,1000.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
994,1000.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
995,1000.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
996,1000.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
997,1000.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
998,1000.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
999,1000.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1000,1000.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1001,1000.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1002,1000.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1003,1000.00,3);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1046,1000.00,5);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1047,1000.00,4);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1048,1000.00,2);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1049,1000.00,6);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1050,1000.00,1);
INSERT INTO public.rateio_empresa (id,valor,empresa_id) VALUES (
1051,1000.00,3);
