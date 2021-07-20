--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.3.0
-- Started on 2015-04-27 09:47:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2958 (class 1262 OID 33049)
-- Name: tomasi_prod; Type: DATABASE; Schema: -; Owner: tomasi_postgres
--

CREATE DATABASE tomasi_prod WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE tomasi_prod OWNER TO tomasi_postgres;

\connect tomasi_prod

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 190 (class 3079 OID 12648)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 190
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 168 (class 1259 OID 33050)
-- Name: conta; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE conta (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL,
    pai integer,
    tipo integer
);


ALTER TABLE public.conta OWNER TO tomasi_postgres;

--
-- TOC entry 169 (class 1259 OID 33053)
-- Name: conta_pagar; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE conta_pagar (
    id integer NOT NULL,
    data_prevista timestamp without time zone NOT NULL,
    data_vencimento timestamp without time zone NOT NULL,
    descricao character varying(255) NOT NULL,
    qtd_replica_meses integer,
    replica_meses boolean NOT NULL,
    valor numeric(19,2) NOT NULL,
    conta_id integer NOT NULL,
    contapagarrateio_id integer,
    empresa_id integer NOT NULL,
    fornecedor_id integer NOT NULL
);


ALTER TABLE public.conta_pagar OWNER TO tomasi_postgres;

--
-- TOC entry 170 (class 1259 OID 33056)
-- Name: conta_pagar_rateio; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE conta_pagar_rateio (
    id integer NOT NULL,
    tipo integer,
    rateio_pre_configurado boolean,
    rateio_pre_configurado_id integer
);


ALTER TABLE public.conta_pagar_rateio OWNER TO tomasi_postgres;

--
-- TOC entry 171 (class 1259 OID 33059)
-- Name: conta_pagar_rateio_empresa; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE conta_pagar_rateio_empresa (
    conta_pagar_rateio_id integer NOT NULL,
    rateio_empresa_id integer NOT NULL
);


ALTER TABLE public.conta_pagar_rateio_empresa OWNER TO tomasi_postgres;

--
-- TOC entry 172 (class 1259 OID 33062)
-- Name: conta_pagar_rateio_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE conta_pagar_rateio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conta_pagar_rateio_seq OWNER TO tomasi_postgres;

--
-- TOC entry 173 (class 1259 OID 33064)
-- Name: conta_pagar_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE conta_pagar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conta_pagar_seq OWNER TO tomasi_postgres;

--
-- TOC entry 174 (class 1259 OID 33066)
-- Name: conta_receber; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE conta_receber (
    id integer NOT NULL,
    data_recebimento timestamp without time zone NOT NULL,
    descricao character varying NOT NULL,
    valor numeric NOT NULL,
    conta_id integer NOT NULL,
    empresa_id integer NOT NULL
);


ALTER TABLE public.conta_receber OWNER TO tomasi_postgres;

--
-- TOC entry 175 (class 1259 OID 33072)
-- Name: conta_receber_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE conta_receber_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conta_receber_seq OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 33074)
-- Name: conta_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE conta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conta_seq OWNER TO tomasi_postgres;

--
-- TOC entry 177 (class 1259 OID 33076)
-- Name: empresa; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE empresa (
    id integer NOT NULL,
    ativa boolean NOT NULL,
    descricao character varying(100) NOT NULL,
    sacado boolean NOT NULL
);


ALTER TABLE public.empresa OWNER TO tomasi_postgres;

--
-- TOC entry 178 (class 1259 OID 33079)
-- Name: empresa_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE empresa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empresa_seq OWNER TO tomasi_postgres;

--
-- TOC entry 179 (class 1259 OID 33081)
-- Name: fornecedor; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE fornecedor (
    id integer NOT NULL,
    ativa boolean NOT NULL,
    descricao character varying(100) NOT NULL
);


ALTER TABLE public.fornecedor OWNER TO tomasi_postgres;

--
-- TOC entry 180 (class 1259 OID 33084)
-- Name: fornecedor_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE fornecedor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fornecedor_seq OWNER TO tomasi_postgres;

--
-- TOC entry 181 (class 1259 OID 33086)
-- Name: lancamento_rateio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lancamento_rateio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lancamento_rateio_seq OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 33088)
-- Name: rateio; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE rateio (
    id integer NOT NULL,
    percentual double precision,
    empresa_id integer,
    tipo_rateio integer
);


ALTER TABLE public.rateio OWNER TO tomasi_postgres;

--
-- TOC entry 183 (class 1259 OID 33091)
-- Name: rateio_empresa; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE rateio_empresa (
    id integer NOT NULL,
    valor numeric(19,2),
    empresa_id integer
);


ALTER TABLE public.rateio_empresa OWNER TO tomasi_postgres;

--
-- TOC entry 184 (class 1259 OID 33094)
-- Name: rateio_empresa_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE rateio_empresa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rateio_empresa_seq OWNER TO tomasi_postgres;

--
-- TOC entry 185 (class 1259 OID 33096)
-- Name: rateio_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE rateio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rateio_seq OWNER TO tomasi_postgres;

--
-- TOC entry 186 (class 1259 OID 33098)
-- Name: tipo_conta; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE tipo_conta (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL
);


ALTER TABLE public.tipo_conta OWNER TO tomasi_postgres;

--
-- TOC entry 187 (class 1259 OID 33101)
-- Name: tipo_conta_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE tipo_conta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_conta_seq OWNER TO tomasi_postgres;

--
-- TOC entry 188 (class 1259 OID 33103)
-- Name: tipo_rateio; Type: TABLE; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

CREATE TABLE tipo_rateio (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL
);


ALTER TABLE public.tipo_rateio OWNER TO tomasi_postgres;

--
-- TOC entry 189 (class 1259 OID 33106)
-- Name: tipo_rateio_seq; Type: SEQUENCE; Schema: public; Owner: tomasi_postgres
--

CREATE SEQUENCE tipo_rateio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_rateio_seq OWNER TO tomasi_postgres;

--
-- TOC entry 2932 (class 0 OID 33050)
-- Dependencies: 168
-- Data for Name: conta; Type: TABLE DATA; Schema: public; Owner: tomasi_postgres
--

--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 172
-- Name: conta_pagar_rateio_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('conta_pagar_rateio_seq', 491, true);


--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 173
-- Name: conta_pagar_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('conta_pagar_seq', 491, true);


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 175
-- Name: conta_receber_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('conta_receber_seq', 99, true);


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 176
-- Name: conta_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('conta_seq', 1, false);


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 178
-- Name: empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('empresa_seq', 1, false);



--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 180
-- Name: fornecedor_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('fornecedor_seq', 1, false);


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 181
-- Name: lancamento_rateio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lancamento_rateio_seq', 1, false);



--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 184
-- Name: rateio_empresa_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('rateio_empresa_seq', 962, true);


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 185
-- Name: rateio_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('rateio_seq', 1, false);



--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 187
-- Name: tipo_conta_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('tipo_conta_seq', 1, false);


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 189
-- Name: tipo_rateio_seq; Type: SEQUENCE SET; Schema: public; Owner: tomasi_postgres
--

SELECT pg_catalog.setval('tipo_rateio_seq', 1, false);


--
-- TOC entry 2793 (class 2606 OID 33110)
-- Name: conta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY conta_pagar
    ADD CONSTRAINT conta_pagar_pkey PRIMARY KEY (id);


--
-- TOC entry 2795 (class 2606 OID 33112)
-- Name: conta_pagar_rateio_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY conta_pagar_rateio
    ADD CONSTRAINT conta_pagar_rateio_pkey PRIMARY KEY (id);


--
-- TOC entry 2791 (class 2606 OID 33114)
-- Name: conta_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT conta_pkey PRIMARY KEY (id);


--
-- TOC entry 2799 (class 2606 OID 33116)
-- Name: conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY conta_receber
    ADD CONSTRAINT conta_receber_pkey PRIMARY KEY (id);


--
-- TOC entry 2801 (class 2606 OID 33118)
-- Name: empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id);


--
-- TOC entry 2803 (class 2606 OID 33120)
-- Name: fornecedor_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (id);


--
-- TOC entry 2807 (class 2606 OID 33122)
-- Name: rateio_empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY rateio_empresa
    ADD CONSTRAINT rateio_empresa_pkey PRIMARY KEY (id);


--
-- TOC entry 2805 (class 2606 OID 33124)
-- Name: rateio_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY rateio
    ADD CONSTRAINT rateio_pkey PRIMARY KEY (id);


--
-- TOC entry 2809 (class 2606 OID 33126)
-- Name: tipo_conta_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_conta
    ADD CONSTRAINT tipo_conta_pkey PRIMARY KEY (id);


--
-- TOC entry 2811 (class 2606 OID 33128)
-- Name: tipo_rateio_pkey; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_rateio
    ADD CONSTRAINT tipo_rateio_pkey PRIMARY KEY (id);


--
-- TOC entry 2797 (class 2606 OID 33130)
-- Name: uk_gs8gciur1gu6l6c1ixd9cpkm9; Type: CONSTRAINT; Schema: public; Owner: tomasi_postgres; Tablespace: 
--

ALTER TABLE ONLY conta_pagar_rateio_empresa
    ADD CONSTRAINT uk_gs8gciur1gu6l6c1ixd9cpkm9 UNIQUE (rateio_empresa_id);


--
-- TOC entry 2819 (class 2606 OID 33131)
-- Name: fk_3nqavc2eo68urpnjehnhdedmo; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_pagar_rateio_empresa
    ADD CONSTRAINT fk_3nqavc2eo68urpnjehnhdedmo FOREIGN KEY (conta_pagar_rateio_id) REFERENCES conta_pagar_rateio(id);


--
-- TOC entry 2821 (class 2606 OID 33136)
-- Name: fk_62rfufrmer6jsn4icl7vh4ef6; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_receber
    ADD CONSTRAINT fk_62rfufrmer6jsn4icl7vh4ef6 FOREIGN KEY (empresa_id) REFERENCES empresa(id);


--
-- TOC entry 2823 (class 2606 OID 33141)
-- Name: fk_a4emfxrapbpvnt4vqunmmwfug; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY rateio
    ADD CONSTRAINT fk_a4emfxrapbpvnt4vqunmmwfug FOREIGN KEY (empresa_id) REFERENCES empresa(id);


--
-- TOC entry 2814 (class 2606 OID 33146)
-- Name: fk_anksto1o85kbjbjcq51ghn8nc; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_pagar
    ADD CONSTRAINT fk_anksto1o85kbjbjcq51ghn8nc FOREIGN KEY (contapagarrateio_id) REFERENCES conta_pagar_rateio(id);


--
-- TOC entry 2812 (class 2606 OID 33151)
-- Name: fk_aytb8vqylvteel5cd3let2nvj; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT fk_aytb8vqylvteel5cd3let2nvj FOREIGN KEY (pai) REFERENCES conta(id);


--
-- TOC entry 2824 (class 2606 OID 33156)
-- Name: fk_dakls1hw67od4mcmgu9enkpc9; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY rateio
    ADD CONSTRAINT fk_dakls1hw67od4mcmgu9enkpc9 FOREIGN KEY (tipo_rateio) REFERENCES tipo_rateio(id);


--
-- TOC entry 2822 (class 2606 OID 33161)
-- Name: fk_e952q4wlbbq67d5r8qarpecwe; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_receber
    ADD CONSTRAINT fk_e952q4wlbbq67d5r8qarpecwe FOREIGN KEY (conta_id) REFERENCES conta(id);


--
-- TOC entry 2815 (class 2606 OID 33166)
-- Name: fk_emp3tg24dk28sbnxdlug9yc5v; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_pagar
    ADD CONSTRAINT fk_emp3tg24dk28sbnxdlug9yc5v FOREIGN KEY (empresa_id) REFERENCES empresa(id);


--
-- TOC entry 2820 (class 2606 OID 33171)
-- Name: fk_gs8gciur1gu6l6c1ixd9cpkm9; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_pagar_rateio_empresa
    ADD CONSTRAINT fk_gs8gciur1gu6l6c1ixd9cpkm9 FOREIGN KEY (rateio_empresa_id) REFERENCES rateio_empresa(id);


--
-- TOC entry 2818 (class 2606 OID 33176)
-- Name: fk_lyi3kbwc6wewbtfph0ddi24dy; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_pagar_rateio
    ADD CONSTRAINT fk_lyi3kbwc6wewbtfph0ddi24dy FOREIGN KEY (rateio_pre_configurado_id) REFERENCES tipo_rateio(id);


--
-- TOC entry 2825 (class 2606 OID 33181)
-- Name: fk_n03hyie8f05gnmjcctjqw0ect; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY rateio_empresa
    ADD CONSTRAINT fk_n03hyie8f05gnmjcctjqw0ect FOREIGN KEY (empresa_id) REFERENCES empresa(id);


--
-- TOC entry 2816 (class 2606 OID 33186)
-- Name: fk_nj77252pfawc4nqr7d99psug2; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_pagar
    ADD CONSTRAINT fk_nj77252pfawc4nqr7d99psug2 FOREIGN KEY (conta_id) REFERENCES conta(id);


--
-- TOC entry 2813 (class 2606 OID 33191)
-- Name: fk_o0d88200fx5vfff532ld2khtn; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT fk_o0d88200fx5vfff532ld2khtn FOREIGN KEY (tipo) REFERENCES tipo_conta(id);


--
-- TOC entry 2817 (class 2606 OID 33196)
-- Name: fk_on3542229mxn5cjn3yjpg5ebq; Type: FK CONSTRAINT; Schema: public; Owner: tomasi_postgres
--

ALTER TABLE ONLY conta_pagar
    ADD CONSTRAINT fk_on3542229mxn5cjn3yjpg5ebq FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id);


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-04-27 09:47:40

--
-- PostgreSQL database dump complete
--

